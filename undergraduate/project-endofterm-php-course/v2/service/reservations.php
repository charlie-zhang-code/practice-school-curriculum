<?php

// 获取查询参数
$id = isset($_GET['id']) ? $_GET['id'] : '';

require_once $_SERVER['DOCUMENT_ROOT'] . '/data/RoomData.php';
$data = new RoomData();
$studyRoom = $data->getStudyRoomById($id);
$detail = $data->getStudyRoomAndSeatsById($id);

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

    <link href="../zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
    <script src="../zui/lib/datetimepicker/datetimepicker.min.js"></script>

    <link href="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/lib/datatable/zui.datatable.min.css" rel="stylesheet">
    <script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/lib/datatable/zui.datatable.min.js"></script>

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
            </header>

            <section class="content">
                <form class="form-horizontal" method="post" action="../mid/reservation.php">
                    <input type="hidden" name="study_room_id" value="<?php echo $id; ?>">

                    <div class="form-group">
                        <table class="table datatable">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>座位</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- <tr>
                                    <td>张三</td>
                                    <td>28</td>
                                    <td>软件工程师</td>
                                    <td>软件工程师</td>
                                </tr> -->
                                <?php foreach ($detail['seats'] as $seat) { ?>
                                    <tr>
                                        <td><?php echo $seat['id']; ?></td>
                                        <td><?php echo $seat['seat_number']; ?></td>
                                        <td><?php echo $seat['status'] == '0' ? '可用' : '不可用'; ?></td>
                                        <td>
                                            <input type="radio" name="seat_id" value="<?php echo $seat['id']; ?>" <?php echo $seat['status'] == '0' ? '' : 'disabled'; ?>>
                                        </td>
                                    </tr>
                                <?php } ?>
                            </tbody>
                        </table>
                    </div>

                    <div class="form-group">
                        <label for="start_time" class="col-sm-2">预约开始时间</label>
                        <div class="col-md-6 col-sm-10">
                            <input type="text" class="form-control form-datetime" name="start_time" id="start_time">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="end_time" class="col-sm-2">预约结束时间</label>
                        <div class="col-md-6 col-sm-10">
                            <input type="text" class="form-control form-datetime" name="end_time" id="end_time">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">预约</button>
                        </div>
                    </div>
                </form>
            </section>
        </article>




        <?php include '../com/footer.php'; ?>
    </div>

    <script>
        // 选择时间和日期
        $(".form-datetime").datetimepicker(
            {
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                forceParse: 0,
                showMeridian: 1,
                format: "yyyy-mm-dd hh:ii"
            });

        $('table.datatable').datatable();
    </script>
</body>

</html>