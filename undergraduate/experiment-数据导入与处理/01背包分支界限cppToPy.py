# https://blog.csdn.net/weixin_45591044/article/details/110876073

# 预定义数据
dimension = 5
limit = 70
w = [30, 20, 20, 50, 40]
v = [80, 30, 60, 100, 70]

# 初始化变量
x = [False] * (dimension + 1)  # 存储当前方案
best_x = [False] * (dimension + 1)  # 存储最优方案
value = 0  # 当前价值
remain_value = 0  # 剩余价值
weight = 0  # 当前容量
best = 0  # 最优价值


def bound(select):
    global remain_value
    remain_value = 0
    while select < dimension:
        remain_value += v[select]
        select += 1
    return remain_value + value


def backtrack(current):
    global value, weight, best, best_x
    if current > dimension:
        for i in range(1, dimension + 1):
            best_x[i] = x[i]
        best = max(best, value)
        return

    if weight + w[current - 1] <= limit:  # 约束条件，是否放入
        x[current] = True
        weight += w[current - 1]
        value += v[current - 1]
        backtrack(current + 1)
        weight -= w[current - 1]
        value -= v[current - 1]

    if bound(current) > best:  # 限界条件，是否剪枝
        x[current] = False
        backtrack(current + 1)


def knapsack():
    global best, best_x
    best = 0
    backtrack(1)



    print("放入购物车的物品最大价值为：", best)
    print("放入购物车的物品序号为：")
    for i in range(1, dimension + 1):
        if best_x[i]:
            print(i, end=" ")


# 执行函数
knapsack()
