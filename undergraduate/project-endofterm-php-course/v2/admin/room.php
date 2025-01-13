<?php
session_start();
require_once $_SERVER['DOCUMENT_ROOT'] . '/config/config.php';

require_once $_SERVER['DOCUMENT_ROOT'] . '/data/RoomData.php';
$data = new RoomData();
$studyRoom = $data->getStudyRooms();
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
        <div class="col-md-12">
            <div class="col-md-3">
                <?php include '../com/admin_side.php'; ?>
            </div>
            <div class="col-md-9">
                <table class="table datatable">
                    <thead>
                        <tr>
                            <th>RID</th>
                            <th class="flex-col" data-width="100">自习室</th>
                            <th class="flex-col" data-width="200">自习室名称</th>
                            <th class="flex-col" data-width="200">地址</th>
                            <th class="flex-col" data-width="100">状态</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <?php foreach ($studyRoom as $room) { ?>
                            <tr>
                                <td><?php echo $room['id']; ?></td>
                                <td>
                                    <img src="../uploads/<?php echo $room['cover']; ?>"  height="50px" class="img-rounded">
                                </td>
                                <td><?php echo $room['name']; ?></td>
                                <td><?php echo $room['address']; ?></td>
                                <td>11</td>
                                <td>
                                    <button class="btn btn-primary">编辑</button>
                                    <button class="btn btn-danger">删除</button>
                                </td>
                            </tr>
                        <?php } ?>
                    </tbody>
                </table>
            </div>
        </div>

        <?php include '../com/footer.php'; ?>
    </div>

    <script>
        $('table.datatable').datatable();
    </script>
</body>

</html>