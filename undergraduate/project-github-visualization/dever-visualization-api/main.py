from fastapi import FastAPI, HTTPException, Body
from fastapi.middleware.cors import CORSMiddleware
import httpx
import aiosqlite
from pydantic import BaseModel
import time
from datetime import datetime

# 初始化FastAPI应用
app = FastAPI()

# 配置CORS中间件
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # 允许所有来源
    allow_credentials=True,
    allow_methods=["*"],  # 允许所有方法
    allow_headers=["*"],  # 允许所有头部信息
)

# 定义数据库文件名
DATABASE_NAME = "github_users.db"


# 定义用户配置模型
class UserConfig(BaseModel):
    username: str
    token: str


# 初始化数据库
async def init_db():
    async with aiosqlite.connect(DATABASE_NAME) as db:
        await db.execute("CREATE TABLE IF NOT EXISTS users (username TEXT PRIMARY KEY NOT NULL, token TEXT NOT NULL)")
        await db.commit()


# 启用后异步初始化数据库
@app.on_event("startup")
async def startup_event():
    await init_db()


async def get_response_data(url: str):
    access_token = await get_configured_token()

    if access_token:
        async with httpx.AsyncClient() as client:
            # 设置请求头，模拟浏览器请求
            headers = {
                "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3",
                "Authorization": f"Bearer {access_token}",
                "Accept": "application/json"
            }
            response = await client.get(url, headers=headers)
            return response.json()
    else:
        async with httpx.AsyncClient() as client:
            headers = {
                "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3",
                "Accept": "application/json"
            }
            response = await client.get(url, headers=headers)
            return response.json()


# 异步函数获取GitHub用户数据
async def fetch_github_user(username: str):
    return await get_response_data(f"https://api.github.com/users/{username}")


# 异步获取用户的仓库
async def fetch_github_user_repos(username: str):
    return await get_response_data(f"https://api.github.com/users/{username}/repos")


async def fetch_github_user_events(username: str):
    return await get_response_data(f"https://api.github.com/users/{username}/events")


# 获取当前配置的用户名
async def get_configured_username():
    async with aiosqlite.connect(DATABASE_NAME) as db:
        cursor = await db.execute("SELECT username FROM users LIMIT 1")
        row = await cursor.fetchone()
        return row[0] if row else None


async def get_configured_token():
    async with aiosqlite.connect(DATABASE_NAME) as db:
        cursor = await db.execute("SELECT token FROM users LIMIT 1")
        row = await cursor.fetchone()
        return row[0] if row else None


# POST 通过API配置系统用户名
@app.post("/config/user/")
async def config_user(user_config: UserConfig):
    async with aiosqlite.connect(DATABASE_NAME) as db:
        # 删除旧的用户名记录
        await db.execute("DELETE FROM users")
        # 插入新的用户名记录
        await db.execute("INSERT INTO users (username, token) VALUES (?, ?)", (user_config.username, user_config.token))
        await db.commit()
        return {
            "success": True,
            "code": 200,
            "message": f"User {user_config.username} configured successfully"
        }


@app.get("/config/user/api")
async def get_github_user():
    username = await get_configured_username()
    user_data = await fetch_github_user(username)

    return user_data


# GET 异步获取GitHub用户数据
@app.get("/config/user/")
async def get_github_user():
    username = await get_configured_username()
    user_data = await fetch_github_user(username)

    if "error" in user_data:
        return user_data

    # 筛选字段
    filtered_data = {
        "id": user_data.get("id"),  # 用户ID
        "login": user_data.get("login"),  # 用户名
        "avatar_url": user_data.get("avatar_url"),  # 用户头像URL
        "url": user_data.get("url"),  # 用户URL
        "type": user_data.get("type"),  # 用户类型
        "name": user_data.get("name"),  # 用户名
        "public_repos": user_data.get("public_repos"),  # 公开仓库数量
        "followers": user_data.get("followers"),  # 关注者数量
        "following": user_data.get("following"),  # 正在关注的人数
        "created_at": user_data.get("created_at"),  # 创建时间
    }

    return filtered_data


# 获取各个仓库的语言情况进行聚合
@app.get("/user/languages")
async def get_github_user_repos():
    username = await get_configured_username()
    repos_data = await fetch_github_user_repos(username)

    # 获取仓库的语言数据
    languages = {}
    for repo in repos_data:
        repos_language_url = repo.get("languages_url")
        if repos_language_url:
            languages_data = await get_response_data(repos_language_url)

            for language, size in languages_data.items():
                if language in languages:
                    languages[language] += size
                else:
                    languages[language] = size

    # 对语言按大小降序排序，并取前6个
    top_languages = dict(sorted(languages.items(), key=lambda item: item[1], reverse=True)[:6])

    # 构建echarts数据的indicator
    indicator = [{"name": lang, "max": max(top_languages.values())} for lang in top_languages.keys()]

    # 构建echarts数据的data
    data = [
        {"value": list(top_languages.values()), "name": "size", "itemStyle": {"color": '#c23531'}},
    ]

    # 构建返回数据
    return {
        "indicator": indicator,
        "data": data,
    }


@app.get("/user/action")
async def configure_github_user():
    username = await get_configured_username()
    events = await fetch_github_user_events(username)

    event_data = {}

    for event in events:
        event_type = event.get("type")
        repo_name = event.get("repo").get("name")
        created_at = event.get("created_at")
        commits_length = len(event.get("payload", {}).get("commits", []))

        if event_type not in event_data:
            event_data[event_type] = {}

        if repo_name not in event_data[event_type]:
            event_data[event_type][repo_name] = {
                "count": 0,
                "commits": 0,
            }

        # 增加每个仓库的活动计数和提交数量
        event_data[event_type][repo_name]["count"] += 1
        event_data[event_type][repo_name]["commits"] += commits_length

        # 更新事件类型的总活动计数
        if "total_count" not in event_data[event_type]:
            event_data[event_type]["total_count"] = 0
        event_data[event_type]["total_count"] += 1

    # 准备返回的数据
    response_data = {
        "event": [],
        "event_count": [],
        "repo_details": []
    }

    for event_type, repos in event_data.items():
        response_data["event"].append(event_type)
        response_data["event_count"].append(repos.pop("total_count", 0))
        response_data["repo_details"].append(repos)

    return response_data


# 获取用户仓库和对应的size
@app.get("/user/repos")
async def get_user_repos():
    username = await get_configured_username()
    repos = await fetch_github_user_repos(username)

    # 获取仓库和对应的size
    resps = [{"name": repo["name"], "value": repo["size"]} for repo in repos]

    # 按照仓库大小降序排序，并取前6个仓库
    top_repos = sorted(resps, key=lambda x: x['value'], reverse=True)[:6]

    return top_repos


@app.get("/user/repos/data")
async def get_user_repos_data():
    username = await get_configured_username()
    repos = await fetch_github_user_repos(username)

    current_time = time.time()  # 获取当前时间戳，用于计算时间衰减

    repo_stats = []
    for repo in repos:
        commits_url = repo.get("commits_url").replace("{/sha}", "")
        commits = await get_response_data(commits_url)

        tags_url = repo.get("tags_url")
        tags = await get_response_data(tags_url)

        issues_url = repo.get("issues_url").replace("{/number}", "")
        issues = await get_response_data(issues_url)

        pulls_url = repo.get("pulls_url").replace("{/number}", "")
        pulls = await get_response_data(pulls_url)

        # 假设releases_url存在并且需要处理
        releases_url = repo.get("releases_url").replace("{/id}", "")
        releases = await get_response_data(releases_url)

        # 计算各个统计数据
        stats = {
            "repo_name": repo["name"],
            "commits": len(commits),
            "tags": len(tags),
            "issues": len([issue for issue in issues if 'pull_request' not in issue]),
            "pull_requests": len([pull for pull in pulls]),
            "releases": len(releases),
            "stargazers_count": repo.get("stargazers_count", 0),
            "watchers_count": repo.get("watchers_count", 0),
            "last_commit_time": max(commit['commit']['author']['date'] for commit in commits) if commits else None,
        }

        # 定义权重
        weights = {
            "commits": 1.5,
            "tags": 0.8,
            "issues": 1.2,
            "pull_requests": 1.3,
            "releases": 1.4,
            "stargazers_count": 2.0,
            "watchers_count": 1.8,
        }

        # 时间衰减因子
        decay_factor = 0.9 ** (
                (current_time - time.mktime(time.strptime(stats["last_commit_time"], "%Y-%m-%dT%H:%M:%SZ"))) / (
                30 * 24 * 60 * 60)) if stats["last_commit_time"] else 1

        # 综合评分
        score = sum(weights[key] * stats[key] for key in weights) * decay_factor

        stats["score"] = score
        repo_stats.append(stats)

    # 按照综合评分降序排序，并取前6个仓库
    top_repos = sorted(repo_stats, key=lambda x: x['score'], reverse=True)[:4]

    # 整理成ECharts可用的数据结构
    echarts_data = {
        "categories": [item["repo_name"] for item in top_repos],
        "series": [
            {"name": "Commits", "data": [item["commits"] for item in top_repos], "type": 'line', "yAxisIndex": 0},
            {"name": "Tags", "data": [item["tags"] for item in top_repos], "type": 'line', "yAxisIndex": 1},
            {"name": "Issues", "data": [item["issues"] for item in top_repos], "type": 'bar', "yAxisIndex": 2},
            {"name": "PR", "data": [item["pull_requests"] for item in top_repos], "type": 'bar', "yAxisIndex": 3},
            {"name": "Releases", "data": [item["releases"] for item in top_repos], "type": 'bar', "yAxisIndex": 4},
            {"name": "Stars", "data": [item["stargazers_count"] for item in top_repos], "type": 'line',
             "yAxisIndex": 5},
            {"name": "Watchers", "data": [item["watchers_count"] for item in top_repos], "type": 'bar', "yAxisIndex": 6}
        ]
    }

    return echarts_data


@app.get("/user/commits")
async def get_user_commits():
    username = await get_configured_username()
    repos = await fetch_github_user_repos(username)

    all_commits = []

    for repo in repos:
        # 获取每个仓库的commits
        commits_url = repo.get("commits_url").replace("{/sha}", "")
        commits = await get_response_data(commits_url)

        # 将每个commit加入到all_commits列表中
        for commit in commits:
            commit_time_str = commit.get("commit", {}).get("author", {}).get("date")
            try:
                # 解析commit_time为datetime对象
                commit_dt = datetime.strptime(commit_time_str, "%Y-%m-%dT%H:%M:%SZ") if commit_time_str else None

                # 如果解析成功，格式化时间为只包含时间部分，并保留原始时间用于排序
                if commit_dt:
                    commit_info = {
                        "repo_name": repo["name"],
                        "commit_message": commit.get("commit", {}).get("message"),
                        "commit_time": commit_dt.strftime("%H:%M:%S"),  # 只取小时、分钟和秒
                        "html_url": commit.get("html_url"),
                        "sort_time": commit_dt  # 保留完整的datetime对象用于排序
                    }
                    all_commits.append(commit_info)
            except ValueError:
                # 如果解析失败，可以选择跳过这条记录或者设置默认值
                print(f"Failed to parse date: {commit_time_str}")
                continue  # 跳过解析失败的提交记录

    # 按原始commit_time降序排序（使用sort_time字段），并限制结果数量为6
    sorted_commits = sorted(all_commits, key=lambda x: x.get('sort_time'), reverse=True)[:6]

    # 返回时不包括sort_time字段
    for commit in sorted_commits:
        del commit['sort_time']

    return sorted_commits
