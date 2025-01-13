<?php
session_start();

require_once $_SERVER['DOCUMENT_ROOT'] . '/config/config.php';
require_once $_SERVER['DOCUMENT_ROOT'] . '/data/UserData.php';

$data = new UserData();
$userInfo = $data->getUserInfo($_SESSION['username']);
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
                            <img src="'<?php echo $userInfo['avatar'] ?>'" width="100px" height="100px"
                                class="img-circle">
                        </h1>
                        <h2 class="text-center">
                            <?php echo $userInfo['name'] ?>
                        </h2>
                        <dl class="dl-inline">
                            <dt>性别</dt>
                            <dd>
                                <!-- 0表示未知，1表示男，2表示女 -->
                                <?php echo $userInfo['gender'] == 0 ? '未知' : ($userInfo['gender'] == 1 ? '男' : '女') ?>
                            </dd>
                        </dl>
                        <dl class="dl-inline">
                            <dt>邮箱</dt>
                            <dd>
                                <?php echo $userInfo['email'] ?>
                            </dd>
                        </dl>
                        <dl class="dl-inline">
                            <dt>创建时间</dt>
                            <dd>
                                <?php echo $userInfo['created_at'] ?>
                            </dd>
                        </dl>
                    </header>
                </article>
            </div>
        </div>

        <?php include '../com/footer.php'; ?>
    </div>
</body>

</html>