<?php
session_start();


// 连接数据库
$conn = new mysqli("localhost", "root", "", "study_room");
if ($conn->connect_error) {
  die("数据库连接失败: " . $conn->connect_error);
}

// 获取查询参数
$id = isset($_GET['id']) ? $_GET['id'] : '';
// 查询自习室详细信息
$sql = "SELECT id, name, capacity, available_seats FROM study_rooms WHERE id = ?";
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
  <title>详情</title>
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
  <div class="page-holder bg-light">

    <?php include './common/header.php'; ?>

    <section class="py-5">
      <div class="container">
        <div class="row mb-5">
          <div class="col-lg-12">
            <h1><?php echo $row['name']; ?></h1>
            <p class="text-sm mb-4">自习室简介</p>
            <ul class="list-unstyled small d-inline-block">
              <li class="px-3 py-2 mb-1 bg-white">
                <strong class="text-uppercase">
                  容量
                </strong>
                <span class="ms-2 text-muted"><?php echo $row['capacity']; ?></span>
              </li>
              <li class="px-3 py-2 mb-1 bg-white">
                <strong class="text-uppercase">
                  空闲
                </strong>
                <span class="ms-2 text-muted"><?php echo $row['available_seats']; ?></span>
              </li>
              <li class="px-3 py-2 mb-1 bg-white text-muted">
                <strong class="text-uppercase text-dark">类型</strong>
                <a class="reset-anchor ms-2" href="#!">
                  Demo Products
                </a>
              </li>
            </ul>
          </div>
        </div>
        <!-- DETAILS TABS-->
        <ul class="nav nav-tabs border-0" id="myTab" role="tablist">
          <li class="nav-item"><a class="nav-link text-uppercase active" id="description-tab" data-bs-toggle="tab"
              href="#description" role="tab" aria-controls="description" aria-selected="true">描述</a></li>
          <li class="nav-item"><a class="nav-link text-uppercase" id="reviews-tab" data-bs-toggle="tab" href="#reviews"
              role="tab" aria-controls="reviews" aria-selected="false">评论</a></li>
        </ul>
        <div class="tab-content mb-5" id="myTabContent">
          <div class="tab-pane fade show active" id="description" role="tabpanel" aria-labelledby="description-tab">
            <div class="p-4 p-lg-5 bg-white">
              <h6 class="text-uppercase">自习室介绍</h6>
              <p class="text-muted text-sm mb-0">
                自习室介绍
              </p>
            </div>
          </div>
          <div class="tab-pane fade" id="reviews" role="tabpanel" aria-labelledby="reviews-tab">
            <div class="p-4 p-lg-5 bg-white">
              <div class="row">
                <div class="col-lg-8">

                  <div class="d-flex mb-3">
                    <div class="flex-shrink-0"><img class="rounded-circle" src="img/customer-1.png" alt="" width="50" />
                    </div>
                    <div class="ms-3 flex-shrink-1">
                      <h6 class="mb-0 text-uppercase">同学A</h6>
                      <p class="small text-muted mb-0 text-uppercase">日期</p>
                      <p class="text-sm mb-0 text-muted">评论内容</p>
                    </div>
                  </div>

                </div>
              </div>
            </div>
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