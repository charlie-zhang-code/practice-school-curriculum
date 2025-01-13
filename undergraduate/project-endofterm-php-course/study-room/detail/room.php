<?php

// 获取查询参数
$id = isset($_GET['id']) ? $_GET['id'] : '';

require_once $_SERVER['DOCUMENT_ROOT'] . '/data/RoomData.php';
$data = new RoomData();
$studyRoom = $data->getStudyRoomById($id);
?>

<!doctype html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ZUI</title>
    <link rel="stylesheet" href="../zui/css/zui.min.css">
    <script src="../zui/lib/jquery/jquery.js"></script>
    <script src="../zui/js/zui.min.js"></script>
    <script src="../zui/lib/datagrid/zui.datagrid.min.js"></script>
    <link rel="stylesheet" href="../zui/lib/datagrid/zui.datagrid.min.css">

</head>

<body>
    <div class="container p-3">
        <?php include '../com/header.php'; ?>


        <article class="article">
            <header>
                <h1>
                    <?php echo $studyRoom['name']; ?>
                </h1>
                <dl class="dl-inline">
                    <dt>地址</dt>
                    <dd><?php echo $studyRoom['address']; ?></dd>
                </dl>
                <dl class="dl-inline">
                    <dt>位置</dt>
                    <dd><?php echo $studyRoom['location']; ?></dd>
                </dl>
                <dl class="dl-inline">
                    <dt>开放时间</dt>
                    <dd><?php echo $studyRoom['open_time'] . ' - ' . $studyRoom['close_time']; ?></dd>
                </dl>
                <dl class="dl-inline">
                    <dt>容量</dt>
                    <dd><?php echo $studyRoom['capacity']; ?></dd>
                </dl>
                <dl class="dl-inline">
                    <dt>状态</dt>
                    <dd>
                        <!-- <?php echo $studyRoom['status']; ?> -->
                        <?php echo $studyRoom['status'] == '1' ? '正常' : '关闭'; ?>
                    </dd>
                </dl>
                <div class="abstract">
                    <p><?php echo $studyRoom['description']; ?></p>
                </div>
            </header>
            <section class="content">
                <img src="../uploads/<?php echo $studyRoom['cover']; ?>" style="object-fit: cover; width: 100%; height: 250px;"
                    class="img-responsive">
            </section>
        </article>


        <?php include '../com/footer.php'; ?>
    </div>

    <script>

    </script>
</body>

</html>