<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>注册</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- gLightbox gallery-->
    <link rel="stylesheet" href="vendor/glightbox/css/glightbox.min.css">
    <!-- Range slider-->
    <link rel="stylesheet" href="vendor/nouislider/nouislider.min.css">
    <!-- Choices CSS-->
    <link rel="stylesheet" href="vendor/choices.js/public/assets/styles/choices.min.css">
    <!-- Swiper slider-->
    <link rel="stylesheet" href="vendor/swiper/swiper-bundle.min.css">
    <!-- Google fonts-->
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Libre+Franklin:wght@300;400;700&amp;display=swap">
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Martel+Sans:wght@300;400;800&amp;display=swap">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="img/favicon.png">
</head>

<body>
    <div class="page-holder">

        <?php include './common/header.php'; ?>

        <div class="container">
            <?php include './common/breadcrumb.php'; ?>

            <section class="py-5">
                <h2 class="h5 text-uppercase mb-4">注册</h2>
                <div class="row">
                    <div class="col-lg-8 mb-4">
                        <form  method="post" action="handle/register_handle.php" enctype="multipart/form-data">
                            <div class="row gy-3">
                                <div class="col-lg-6">
                                    <label class="form-label text-sm text-uppercase" for="username">用户名</label>
                                    <input class="form-control form-control-lg" type="text" name="username" id="username" value="user"
                                        placeholder="请输入您的用户名">
                                </div>
                                <div class="col-lg-6">
                                    <label class="form-label text-sm text-uppercase" for="email">邮箱</label>
                                    <input class="form-control form-control-lg" type="email" name="email" id="email" value="user@163.com"
                                        placeholder="请输入您的邮箱">
                                </div>
                                <div class="col-lg-6">
                                    <label class="form-label text-sm text-uppercase" for="password">密码</label>
                                    <input class="form-control form-control-lg" type="password" name="password" id="password" value="123456"
                                        placeholder="请输入您的密码">
                                </div>
                                <div class="col-lg-6">
                                    <label class="form-label text-sm text-uppercase" for="repassword">确认密码</label>
                                    <input class="form-control form-control-lg" type="password" name="repassword" id="repassword" value="123456"
                                        placeholder="请再次输入您的密码">
                                </div>
                                <div class="col-lg-6">
                                    <label class="form-label text-sm text-uppercase" for="phone">手机号</label>
                                    <input class="form-control form-control-lg" type="tel" name="phone" id="phone" value="18798563574"
                                        placeholder="请输入您的手机号">
                                </div>
                                <div class="col-lg-6 form-group">
                                    <label class="form-label text-sm text-uppercase" for="gender">性别</label>
                                    <select id="gender" class="form-control form-control-lg" name="gender" id="gender">
                                        <option value="1">男</option>
                                        <option value="0">女</option>
                                    </select>
                                </div>
                                <div class="col-lg-12 form-group">
                                    <label class="form-label text-sm text-uppercase" for="avatar">上传头像</label>
                                    <input class="form-control form-control-lg" type="file" name="avatar" id="avatar">
                                </div>


                                <div class="col-lg-12 form-group">
                                    <button class="btn btn-dark" type="submit">注册</button>
                                </div>

                                <div class="col-lg-12 form-group ">
                                    <a href="login.php" class="text-sm text-muted">
                                        <strong>已有账号？</strong>点击登录
                                    </a>
                                </div>
                            </div>
                        </form>
                    </div>

                    <div class="col-lg-4">
                        <div class="border-0 rounded-0 p-lg-4 bg-light">
                            <div class="card-body">
                                <h5 class="text-uppercase mb-4">用户权益</h5>
                                <ul class="list-unstyled mb-0">
                                    <li class="d-flex align-items-center justify-content-between">
                                        <strong class="small fw-bold">预约自习室</strong>
                                    </li>
                                    <li class="border-bottom my-2"></li>
                                    <li class="d-flex align-items-center justify-content-between">
                                        <strong class="small fw-bold">查看历史预约</strong>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>

    <?php include './common/footer.php'; ?>

    <!-- JavaScript files-->
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="vendor/glightbox/js/glightbox.min.js"></script>
    <script src="vendor/nouislider/nouislider.min.js"></script>
    <script src="vendor/swiper/swiper-bundle.min.js"></script>
    <script src="vendor/choices.js/public/assets/scripts/choices.min.js"></script>
    <script src="js/front.js"></script>
    <script>
        // ------------------------------------------------------- //
        //   Inject SVG Sprite - 
        //   see more here 
        //   https://css-tricks.com/ajaxing-svg-sprite/
        // ------------------------------------------------------ //
        function injectSvgSprite(path) {

            var ajax = new XMLHttpRequest();
            ajax.open("GET", path, true);
            ajax.send();
            ajax.onload = function (e) {
                var div = document.createElement("div");
                div.className = 'd-none';
                div.innerHTML = ajax.responseText;
                document.body.insertBefore(div, document.body.childNodes[0]);
            }
        }
        // this is set to BootstrapTemple website as you cannot 
        // inject local SVG sprite (using only 'icons/orion-svg-sprite.svg' path)
        // while using file:// protocol
        // pls don't forget to change to your domain :)
        injectSvgSprite('icons/orion-svg-sprite.svg');
    </script>
    <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css"
        integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    </div>
</body>

</html>