<!doctype html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ZUI</title>

    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/css/zui.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/lib/jquery/jquery.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/js/zui.min.js"></script>
</head>

<body>
    <div class="container p-3" style="height: 100vh;">

        <form method="post" action="../mid/auth.php" style="display: flex; flex-direction: column; justify-content: center; align-items: center; height: 100%;">
            <div class="form-group">
                <label for="username" class="required">账号</label>
                <input type="text" class="form-control" name="username" id="username" placeholder="电子邮件/手机号/用户名">
            </div>
            <div class="form-group">
                <label for="username" class="required">密码</label>
                <input type="password" class="form-control" name="password" id="username" placeholder="密码">
            </div>
            <button type="submit" class="btn btn-primary">提交</button>
        </form>

    </div>

</body>

</html>