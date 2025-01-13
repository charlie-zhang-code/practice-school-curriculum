<?php
require_once $_SERVER['DOCUMENT_ROOT'] . '/config/config.php';
require_once $_SERVER['DOCUMENT_ROOT'] . '/data/RoomData.php';
require_once $_SERVER['DOCUMENT_ROOT'] . '/data/ReservationData.php';

// 获取查询参数
$reservation_id = isset($_GET['reservation_id']) ? $_GET['reservation_id'] : '';

$reservationData = new ReservationData();
$reservation = $reservationData->getReservationById($reservation_id);

// echo '<pre>';
// print_r($reservation);
// echo '</pre>';

$data = new RoomData();
$studyRoom = $data->getStudyRoomById($reservation['study_room_id']);
// $detail = $data->getStudyRoomAndSeatsById($id);
// echo '<pre>';
// print_r($studyRoom);
// echo '</pre>';

$seatDetail = $data->getSeatById($reservation['seat_id']);
// echo '<pre>';
// print_r($seatDetail);
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


    <link href="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/lib/datatable/zui.datatable.min.css" rel="stylesheet">
    <script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/lib/datatable/zui.datatable.min.js"></script>


    <link href="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/lib/dashboard/zui.dashboard.min.css" rel="stylesheet">
    <script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/lib/dashboard/zui.dashboard.min.js"></script>

    <style>

    </style>
</head>

<body>
    <div class="container p-3">
        <?php include '../com/header.php'; ?>

        <div class="list">
            <header>
                <h1>预约确认</h1>
            </header>
            <form method="get" action="../mid/reservation.php">
                <section class="items">
                    <div class="item">
                        <!-- <div class="item-heading">
                        <h4>预约号 <?php echo $reservation_id; ?></h4>
                    </div> -->
                        <input name="reservation_id" type="hidden" value="<?php echo $reservation_id; ?>">
                        <div class="item-content">
                            <table class="table datatable">
                                <thead>
                                    <tr>
                                        <th>预约ID</th>
                                        <th class="flex-col" data-width="100">自习室</th>
                                        <th class="flex-col" data-width="100">座位</th>
                                        <th class="flex-col" data-width="100">状态</th>
                                        <th class="flex-col" data-width="200">预约开始时间</th>
                                        <th class="flex-col" data-width="200">预约结束时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><?php echo $reservation_id; ?></td>
                                        <td><?php echo $studyRoom['name']; ?></td>
                                        <td><?php echo $seatDetail['seat_number']; ?></td>
                                        <td>
                                            <?php echo $reservation['status'] == 0 ? '未开始' : ($reservation['status'] == 1 ? '进行中' : ($reservation['status'] == 2 ? '已完成' : '已取消')); ?>
                                        </td>
                                        <td><?php echo $reservation['start_time']; ?></td>
                                        <td><?php echo $reservation['end_time']; ?></td>
                                        <td>
                                            <button type="submit">确认</button>
                                            <button>取消</button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </section>
            </form>
        </div>

        <?php include '../com/footer.php'; ?>
    </div>

    <script>
        $('table.datatable').datatable();
    </script>

</body>

</html>