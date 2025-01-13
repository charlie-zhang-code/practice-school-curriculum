<?php
session_start();
?>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>管理</title>
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
                <div class="container p-0">
                    <div class="row">
                        <!-- SHOP SIDEBAR-->
                        <div class="col-lg-3 order-2 order-lg-1">
                            <ul class="list-unstyled small text-muted ps-lg-4 font-weight-normal">
                                <li class="mb-2"><a class="reset-anchor" href="user_admin.php">用户管理</a></li>
                                <li class="mb-2"><a class="reset-anchor" href="room_admin.php">自习室管理</a></li>
                            </ul>
                        </div>
                        <!-- SHOP LISTING-->
                        <div class="col-lg-9 order-1 order-lg-2 mb-5 mb-lg-0">
                            <div class="row mb-3 align-items-center">
                                <div class="col-lg-6">
                                    <ul class="list-inline d-flex align-items-center justify-content-lg-end mb-0">
                                        <li class="list-inline-item">
                                            <!-- 搜索 -->
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="row">
                                
          <?php
          // 连接数据库
          $conn = new mysqli("localhost", "root", "", "study_room");
          if ($conn->connect_error) {
            die("数据库连接失败: " . $conn->connect_error);
          }

          // 设置字符集
          $conn->set_charset("utf8");

          // 查询数据表显示出来
          $sql = "SELECT id, name, capacity, available_seats, image_url FROM study_rooms";
          $result = $conn->query($sql);

          if ($result && $result->num_rows > 0) {
            // 输出数据
            while ($row = $result->fetch_assoc()) {
              $badge = '';
                if ($row["available_seats"] > $row["capacity"] * 0.5) {
                  $badge = '<div class="badge text-white bg-info">空余</div>';
                }
                if ($row["available_seats"] == $row["capacity"]) {
                  $badge = '<div class="badge text-white bg-primary">无人</div>';
                }
                if ($row["available_seats"] == 0) {
                  $badge = '<div class="badge text-white bg-danger">爆满</div>';
                }

                // 检查用户是否登录
              if (isset($_SESSION['username'])) {
                // 用户已登录，显示预约按钮
                echo '
          <div class="col-xl-3 col-lg-4 col-sm-6">
            <div class="product text-center">
              <div class="position-relative mb-3">
                ' . $badge . '
                <a class="d-block" href="detail.php?id=' . $row["id"] . '">
                  <img class="img-fluid w-100" src="'. $row["image_url"] . '" alt="...">
                </a>
                <div class="product-overlay">
                  <ul class="mb-0 list-inline">
                    <li class="list-inline-item m-0 p-0">
                      <a class="btn btn-sm btn-dark" href="room_edit.php?id=' . $row["id"] . '">
                        管理
                      </a>
                    </li>
                  </ul>
                </div>
              </div>
              <h6>
                <a class="reset-anchor" href="detail.php?id=' . $row["id"] . '">
                  ' . $row["name"] . '
                </a>
              </h6>
              <p class="small text-muted">
                ' . $row["available_seats"] . '/' . $row["capacity"] . '
              </p>
            </div>
          </div>
        ';
              }
            }
            $result->free();
          } else {
            echo "0 结果";
          }
          $conn->close();
          ?>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <?php include './common/footer.php'; ?>
        <!-- JavaScript files-->
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vendor/glightbox/js/glightbox.min.js"></script>
        <script src="vendor/nouislider/nouislider.min.js"></script>
        <script src="vendor/swiper/swiper-bundle.min.js"></script>
        <script src="vendor/choices.js/public/assets/scripts/choices.min.js"></script>
        <script src="js/front.js"></script>
        <!-- Nouislider Config-->
        <script>
            var range = document.getElementById('range');
            noUiSlider.create(range, {
                range: {
                    'min': 0,
                    'max': 2000
                },
                step: 5,
                start: [100, 1000],
                margin: 300,
                connect: true,
                direction: 'ltr',
                orientation: 'horizontal',
                behaviour: 'tap-drag',
                tooltips: true,
                format: {
                    to: function (value) {
                        return '$' + value;
                    },
                    from: function (value) {
                        return value.replace('', '');
                    }
                }
            });

        </script>
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