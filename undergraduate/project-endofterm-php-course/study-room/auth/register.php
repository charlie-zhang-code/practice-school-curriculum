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

        <form method="post" action="../mid/register_mid.php"
            style="display: flex; flex-direction: column; justify-content: center; align-items: center; height: 100%;">
            <div class="form-group">
                <label for="username" class="required">账号</label>
                <input type="text" class="form-control" name="username" id="username" placeholder="用户名">
            </div>
            <div class="form-group">
                <label for="email" class="required">邮箱</label>
                <input type="text" class="form-control" name="email" id="email" placeholder="电子邮件">
            </div>
            <div class="form-group">
                <label for="password" class="required">密码</label>
                <input type="password" class="form-control" name="password" id="password" placeholder="密码">
            </div>
            <div class="form-group checkbox">
                <label>
                    <input name="agreement" value="1" type="checkbox"> 我已阅读并接受
                </label>
                <a data-type="iframe" data-url="../html/agreement.html" data-toggle="modal">用户协议</a>
            </div>
            <button type="submit" class="btn btn-primary">提交</button>
        </form>

    </div>
    <script>
        $(document).ready(function () {
            $('form').on('submit', function (e) {
                if (!($('input[name="agreement"]').is(':checked'))) {
                    alert('请接受用户协议');
                    e.preventDefault(); // 阻止表单提交
                }
            });
        });
    </script>
</body>

</html>