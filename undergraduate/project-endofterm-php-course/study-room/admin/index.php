<?php
require_once $_SERVER['DOCUMENT_ROOT'] . '/config/config.php';
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

    <script src="//cdnjs.cloudflare.com/ajax/libs/zui/1.10.0/lib/chart/zui.chart.min.js"></script>
</head>

<body>
    <div class="container p-3">

        <?php include '../com/header.php'; ?>
        <div class="col-md-12">
            <div class="col-md-3">
                <?php include '../com/admin_side.php'; ?>
            </div>
            <div class="col-md-9">
                <canvas id="myChart" width="100%" height="30"></canvas>
            </div>
        </div>

        <?php include '../com/footer.php'; ?>
    </div>

    <script>
        // 使用jquery方法获取 2d context 对象
        var ctx = $("#myChart").get(0).getContext("2d");
        // var ctx = document.getElementById("myChart").getContext("2d");

        // 使用$.zui.Chart构造Chart实例
        var myNewChart = new $.zui.Chart(ctx);
        // // 创建指定Canvas的Chart实例
        // var myChart = $("#myChart").chart();


        var data = {
            labels: ["一月", "二月", "三月", "四月", "五月", "六月", "七月"],
            datasets: [
                {
                    label: "蓝队",
                    color: 'blue',
                    data: [65, 59, 80, 81, 56, 55, 40]
                }, {
                    label: "绿队",
                    color: 'green',
                    data: [28, 48, 40, 19, 86, 27, 90]
                }
            ]
        };

        var options = { responsive: true }; // 图表配置项，可以留空来使用默认的配置
        var myBarChart = $('#myChart').barChart(data, options);
    </script>
</body>

</html>