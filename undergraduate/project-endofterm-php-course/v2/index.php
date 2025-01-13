<?php
define('ROOT_PATH', __DIR__);
require_once ROOT_PATH . '/config/config.php';
?>

<!doctype html>
<html lang="zh-CN">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>ZUI</title>

  <!-- <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/css/zui.min.css">
  <script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/lib/jquery/jquery.js"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/js/zui.min.js"></script> -->

  <script src="./zui/lib/jquery/jquery.js"></script>
  <script src="./zui/js/zui.min.js"></script>
  <link href="../zui/lib/datatable/zui.datatable.min.css" rel="stylesheet">
  <script src="./zui/lib/datatable/zui.datatable.min.js"></script>
  <link rel="stylesheet" href="./zui/css/zui.min.css">
</head>

<body>
  <div class="container p-3">
    <?php include './com/header.php'; ?>

    <?php include './list/rooms.php'; ?>

    <?php include './com/footer.php'; ?>
  </div>

</body>

</html>