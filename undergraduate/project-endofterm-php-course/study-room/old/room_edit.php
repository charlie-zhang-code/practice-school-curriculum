<?php
session_start();


// 连接数据库
$conn = new mysqli("localhost", "root", "", "study_room");
if ($conn->connect_error) {
    die("数据库连接失败: " . $conn->connect_error);
}

// 设置字符集
$conn->set_charset("utf8");

// 获取查询参数
$id = isset($_GET['id']) ? $_GET['id'] : '';
// 查询自习室详细信息
$sql = "SELECT * FROM study_rooms WHERE id = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $id);
$stmt->execute();
$result = $stmt->get_result();

if ($result && $result->num_rows > 0) {
    $row = $result->fetch_assoc();
} else {
    echo "自习室信息不存在";
    $conn->close();
    exit;
}

$conn->close();
?>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>编辑</title>
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
    <div class="page-holder bg-light">

        <?php include './common/header.php'; ?>

        <section class="py-5">
            <div class="container">
                <div class="row mb-5">
                    <div class="col-lg-12">


                        <!-- <h1><?php echo $row['name']; ?></h1>
            <p class="text-sm mb-4">
              <?php echo $row['description']; ?>
            </p> -->










                        <form method="post" action="#">
                            <div class="row gy-3">
                                <div class="col-lg-4">
                                    <label class="form-label text-sm text-uppercase" for="room_name">自习室名称</label>
                                    <input class="form-control form-control-lg" type="text" name="room_name"
                                        id="room_name" value="<?php echo $row['name']; ?>">
                                </div>
                                <div class="col-lg-4">
                                    <label class="form-label text-sm text-uppercase" for="capacity">容量</label>
                                    <input class="form-control form-control-lg" type="text" name="capacity"
                                        id="capacity" value="<?php echo $row['capacity']; ?>">
                                </div>
                                <div class="col-lg-4">
                                    <label class="form-label text-sm text-uppercase" for="room_type">类型</label>
                                    <input class="form-control form-control-lg" type="text" name="room_type"
                                        id="room_type" value="<?php echo $row['room_type']; ?>">
                                </div>
                                <div class="col-lg-12">
                                    <label class="form-label text-sm text-uppercase" for="description">描述</label>
                                    <input class="form-control form-control-lg" type="text" name="description"
                                        id="description" value="<?php echo $row['description']; ?>">
                                </div>
                                <div class="col-lg-12">
                                    <label class="form-label text-sm text-uppercase" for="introduction">介绍</label>
                                    <input class="form-control form-control-lg" type="text" name="introduction"
                                        id="introduction" value="<?php echo $row['introduction']; ?>">
                                </div>
                                <div class="col-lg-12 form-group">
                                    <div class="col-xl-3 col-lg-4 col-sm-6 mb-2">
                                        <a class="d-block">
                                            <img class="img-fluid w-100" src="<?php echo $row['image_url']; ?>"
                                                alt="...">
                                        </a>
                                        <label class="form-label text-sm text-uppercase" for="image_url">图片</label>
                                        <input class="form-control form-control-lg" type="file" name="image_url"
                                            id="image_url">
                                    </div>
                                </div>

                                <div class="col-lg-12 form-group d-flex justify-content-between">
                                    <div>
                                        <button class="btn btn-dark" type="submit">保存</button>
                                        <button class="btn" type="submit">取消</button>
                                    </div>
                                    <button class="btn btn-danger" type="submit">删除</button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>

            </div>
        </section>

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
                    div.className = ' d-none'; div.innerHTML = ajax.responseText; document.body.insertBefore(div,
                        document.body.childNodes[0]);
                }
            } // this is set to BootstrapTemple website as you cannot // inject
                  local SVG sprite(using only 'icons/orion-svg-sprite.svg' path) // while using file:// protocol // pls
                  don't forget to change to your domain :) injectSvgSprite('icons / orion - svg - sprite.svg'); </script>
        <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css"
            integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    </div>
</body>

</html>