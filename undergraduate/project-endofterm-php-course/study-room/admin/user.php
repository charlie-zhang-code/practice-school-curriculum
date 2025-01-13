<?php
session_start();
require_once $_SERVER['DOCUMENT_ROOT'] . '/config/config.php';

require_once $_SERVER['DOCUMENT_ROOT'] . '/data/UserData.php';
$data = new UserData();
$userList = $data->getUserList();
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
                            <th>UID</th>
                            <th class="flex-col" data-width="100">头像</th>
                            <th class="flex-col" data-width="200">昵称</th>
                            <th class="flex-col" data-width="200">用户名</th>
                            <th class="flex-col" data-width="200">邮箱</th>
                            <th class="flex-col" data-width="100">状态</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>

                        <?php foreach ($userList as $user) { ?>
                            <tr>
                                <td><?php echo $user['id']; ?></td>
                                <td>
                                    <img src="'<?php echo $user['avatar'] ?>'" width="25px" height="25px"
                                        class="img-circle">
                                </td>
                                <td><?php echo $user['name']; ?></td>
                                <td><?php echo $user['username']; ?></td>
                                <td><?php echo $user['email']; ?></td>
                                <td>
                                    11
                                </td>
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