<?php
session_start();

require_once $_SERVER['DOCUMENT_ROOT'] . '/config/config.php';
require_once $_SERVER['DOCUMENT_ROOT'] . '/data/UserData.php';

$data = new UserData();
// $passwordData = $data->updatePassword($_SESSION['id'], );
?>

<!doctype html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ZUI</title>

    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/css/zui.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/lib/jquery/jquery.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/js/zui.min.js"></script>
    <link href="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/lib/uploader/zui.uploader.min.css" rel="stylesheet">
    <script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/lib/uploader/zui.uploader.min.js"></script>
</head>

<body>
    <div class="container p-3">

        <?php include '../com/header.php'; ?>
        <div class="col-md-12">
            <div class="col-md-3">
                <?php include '../com/user_side.php'; ?>
            </div>
            <div class="col-md-9">
                <article class="article">
                    <header>
                        <h1 class="text-center">

                        </h1>
                    </header>
                    <section class="content">
                        <form method="post" action="#" enctype="multipart/form-data">

                            <div class="form-group">
                                <label for="password">新密码</label>
                                <input type="text" class="form-control" id="password" placeholder="密码">
                            </div>

                            <button type="submit" class="btn btn-primary">提交</button>
                        </form>
                    </section>
                </article>
            </div>
        </div>

        <?php include '../com/footer.php'; ?>
    </div>

    <script>
    </script>
</body>

</html>