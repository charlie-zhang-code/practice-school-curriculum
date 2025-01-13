<?php
session_start();
require_once $_SERVER['DOCUMENT_ROOT'] . '/data/ReservationData.php';

$uid = $_SESSION['id'];
$ReservationData = new ReservationData();
$reservations = $ReservationData->getUserReservations($uid);

// echo '<pre>';
// print_r($reservations);
// echo '</pre>';
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
    <script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/lib/datagrid/zui.datagrid.min.js"></script>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/lib/datagrid/zui.datagrid.min.css">
    <link href="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
    <script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/lib/datetimepicker/datetimepicker.min.js"></script>
    <link href="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/lib/datatable/zui.datatable.min.css" rel="stylesheet">
    <script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/lib/datatable/zui.datatable.min.js"></script>

</head>

<body>
    <div class="container p-3">
        <?php include '../com/header.php'; ?>


        <table class="table datatable">
            <thead>
                <tr>
                    <th>ID</th>
                    <th class="flex-col" data-width="100">自习室</th>
                    <th class="flex-col" data-width="100">自习室名称</th>
                    <th class="flex-col" data-width="100">座位</th>
                    <th class="flex-col" data-width="200">开始时间</th>
                    <th class="flex-col" data-width="200">结束时间</th>
                    <th class="flex-col" data-width="100">状态</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <?php foreach ($reservations as $reservation) { ?>
                    <tr>
                        <td><?php echo $reservation['reservation_id']; ?></td>
                        <td>
                            <img src="../uploads/<?php echo $reservation['study_room_cover']; ?>" height="50px"
                                class="img-rounded">
                        </td>
                        <td><?php echo $reservation['study_room_name']; ?></td>
                        <td><?php echo $reservation['seat_number']; ?></td>
                        <td><?php echo $reservation['start_time']; ?></td>
                        <td><?php echo $reservation['end_time']; ?></td>
                        <td>
                            <?php echo $reservation['status'] == 0 ? '未开始' : ($reservation['status'] == 1 ? '进行中' : ($reservation['status'] == 2 ? '已完成' : '已取消')); ?>
                        </td>
                        <td>
                            <button class="btn btn-danger">删除</button>
                        </td>
                    </tr>
                <?php } ?>
            </tbody>
        </table>


        <?php include '../com/footer.php'; ?>
    </div>

    <script>
        $('table.datatable').datatable();
    </script>
</body>

</html>