<?php
session_start();
?>

<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>首页</title>
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
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Martel+Sans:wght@300;400;800&amp;display=swap">
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

      <?php include './common/banner.php'; ?>

      <section class="py-5">
        <header>
          <p class="small text-muted small text-uppercase mb-1">
            舒适的自习环境
          </p>
          <h2 class="h5 text-uppercase mb-4">
            空闲自习室
          </h2>
        </header>

        <div class="row">

          <?php
          // 连接数据库
          $conn = new mysqli("localhost", "root", "", "study_room");
          if ($conn->connect_error) {
            die("数据库连接失败: " . $conn->connect_error);
          }

          // 查询数据表显示出来
          $sql = "SELECT id, name, capacity, available_seats FROM study_rooms";
          $result = $conn->query($sql);

          if ($result && $result->num_rows > 0) {
            // 输出数据
            while ($row = $result->fetch_assoc()) {
              // 检查用户是否登录
              if (isset($_SESSION['username'])) {
                // 用户已登录，显示预约按钮
                echo '
          <div class="col-xl-3 col-lg-4 col-sm-6">
            <div class="product text-center">
              <div class="position-relative mb-3">
                <div class="badge text-white bg-info">New</div>
                <div class="badge text-white bg-primary">Sale</div>
                <div class="badge text-white bg-danger">Sold</div>
                <a class="d-block" href="detail.php?id=' . $row["id"] . '">
                  <img class="img-fluid w-100" src="./img/hero-banner-alt.jpg" alt="...">
                </a>
                <div class="product-overlay">
                  <ul class="mb-0 list-inline">
                    <li class="list-inline-item m-0 p-0">
                      <a class="btn btn-sm btn-dark" href="order.php?id=' . $row["id"] . '">
                        预约
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
              } else {
                // 用户未登录，显示登录提示
                echo '
          <div class="col-xl-3 col-lg-4 col-sm-6">
            <div class="product text-center">
              <div class="position-relative mb-3">
                <div class="badge text-white bg-info">New</div>
                <div class="badge text-white bg-primary">Sale</div>
                <div class="badge text-white bg-danger">Sold</div>
                <a class="d-block" href="detail.php?id=' . $row["id"] . '">
                  <img class="img-fluid w-100" src="./img/hero-banner-alt.jpg" alt="...">
                </a>
                <div class="product-overlay">
                  <ul class="mb-0 list-inline">
                    <li class="list-inline-item m-0 p-0">
                      <a class="btn btn-sm btn-dark" href="login.php">
                        请登录
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
      </section>


      <section class="py-5 bg-light">
        <div class="container">
          <div class="row text-center gy-3">
            <div class="col-lg-4">
              <div class="d-inline-block">
                <div class="d-flex align-items-end">
                  <svg class="svg-icon svg-icon-big svg-icon-light">
                    <use xlink:href="#delivery-time-1"> </use>
                  </svg>
                  <div class="text-start ms-3">
                    <h6 class="text-uppercase mb-1">空闲自习室</h6>
                    <p class="text-sm mb-0 text-muted">5/10</p>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-4">
              <div class="d-inline-block">
                <div class="d-flex align-items-end">
                  <svg class="svg-icon svg-icon-big svg-icon-light">
                    <use xlink:href="#helpline-24h-1"> </use>
                  </svg>
                  <div class="text-start ms-3">
                    <h6 class="text-uppercase mb-1">自习人数</h6>
                    <p class="text-sm mb-0 text-muted">500</p>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-4">
              <div class="d-inline-block">
                <div class="d-flex align-items-end">
                  <svg class="svg-icon svg-icon-big svg-icon-light">
                    <use xlink:href="#label-tag-1"> </use>
                  </svg>
                  <div class="text-start ms-3">
                    <h6 class="text-uppercase mb-1">预约人数</h6>
                    <p class="text-sm mb-0 text-muted">400</p>
                  </div>
                </div>
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