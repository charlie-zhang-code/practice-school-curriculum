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
                            <img src="'<?php echo $userInfo['avatar'] ?>'" width="100px" height="100px"
                                class="img-circle">
                        </h1>
                    </header>
                    <section class="content">
                        <form method="post" action="#" enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="avatar">头像</label>
                                <input type="file" class="form-control" id="avatar" value="">
                            </div>
                            <div class="form-group">
                                <label for="nikename">昵称</label>
                                <input type="text" class="form-control" id="nikename" placeholder="昵称"
                                    value="<?php echo $userInfo['name'] ?>">
                            </div>
                            <div class="form-group">
                                <label for="email">邮箱</label>
                                <input type="email" class="form-control" id="email" placeholder="邮箱"
                                    value="<?php echo $userInfo['email'] ?>">
                            </div>
                            <div class="form-group">
                                <label for="gender">性别</label>
                                <select class="form-control" name="gender" id="gender"
                                    value="<?php echo $userInfo['gender'] ?>">
                                    <option value="0">未知</option>
                                    <option value="1">男</option>
                                    <option value="2">女</option>
                                </select>
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
        $('#uploaderExample').uploader({
            autoUpload: true,            // 当选择文件后立即自动进行上传操作
            url: 'your/file/upload/url'  // 文件上传提交地址
        });
    </script>
</body>

</html>