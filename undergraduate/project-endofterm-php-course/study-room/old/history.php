<?php
session_start();


// 连接数据库
$conn = new mysqli("localhost", "root", "", "study_room");
if ($conn->connect_error) {
  die("数据库连接失败: " . $conn->connect_error);
}
// 设置字符集
$conn->set_charset("utf8"); 

// 查询自习室详细信息
$uid = $_SESSION['id'];

// 从order_record表、study_room中查询历史记录（根据uid，rid）
$sql = "
SELECT
  study_rooms.id,
  study_rooms.name,
  study_rooms.image_url
FROM
  order_record
INNER JOIN
  study_rooms
ON
  order_record.rid = study_rooms.id
WHERE
  order_record.uid = '$uid'
";

$result = $conn->query($sql);

// 统计预约次数
$sql2 = "SELECT COUNT(*) as count FROM order_record WHERE uid = '$uid'";
$result2 = $conn->query($sql2);
$row2 = $result2->fetch_assoc();
$count = $row2['count'];

// if ($result->num_rows > 0) {
//   // 输出每行数据
//   while($row = $result->fetch_assoc()) {
//     echo "自习室: " . $row["name"] . "<br>";
//   }
// } else {
//   echo "没有历史记录";
// }
// $conn->close();

?>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>历史</title>
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
                <h2 class="h5 text-uppercase mb-4">预约历史</h2>
                <div class="row">
                    <div class="col-lg-8 mb-4 mb-lg-0">
                        <!-- CART TABLE-->
                        <div class="table-responsive mb-4">
                            <table class="table text-nowrap">
                                <thead class="bg-light">
                                    <tr>
                                        <th class="border-0 p-3" scope="col">
                                            <strong class="text-sm text-uppercase">自习室</strong>
                                        </th>
                                        <th class="border-0 p-3" scope="col">
                                            <strong class="text-sm text-uppercase">时间</strong>
                                        </th>
                                    </tr>
                                </thead>
                                <tbody class="border-0">
                                    <?php
                                    if ($result && $result->num_rows > 0) {
                                        // 输出每行数据
                                        while($row = $result->fetch_assoc()) {
                                        echo
                                            '
                                            <tr>
                                                <th class="ps-0 py-3 border-light" scope="row">
                                                    <div class="d-flex align-items-center">
                                                        <a class="reset-anchor d-block animsition-link" href="detail.php?id=' . $row["id"] . '">
                                                            <img src="' . $row["image_url"] . '" alt="..." width="70" />
                                                        </a>
                                                        <div class="ms-3">
                                                            <strong class="h6">
                                                                <a class="reset-anchor animsition-link" href="detail.php?id=' . $row["id"] . '">
                                                                    ' . $row["name"] . '
                                                                </a>
                                                            </strong>
                                                        </div>
                                                    </div>
                                                </th>
                                                <td class="p-3 align-middle border-light">
                                                    <p class="mb-0 small">250</p>
                                                </td>
                                            </tr>
                                        ';
                                    }
                                }
                                    ?>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- ORDER TOTAL-->
                    <div class="col-lg-4">
                        <div class="card border-0 rounded-0 p-lg-4 bg-light">
                            <div class="card-body">
                                <h5 class="text-uppercase mb-4">统计</h5>
                                <ul class="list-unstyled mb-0">
                                    <li class="d-flex align-items-center justify-content-between">
                                        <strong class="text-uppercase small font-weight-bold">预约次数</strong>
                                        <span class="text-muted small">
                                            <?php echo $count; ?>
                                        </span>
                                    </li>
                                </ul>
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