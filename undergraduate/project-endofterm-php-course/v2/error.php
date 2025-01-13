<?php
// 检查是否有错误消息传递
if (isset($_GET['message'])) {
    $message = $_GET['message'];
} else {
    $message = 'An unknown error occurred.';
}

// 显示错误信息
?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding-top: 50px;
        }

        .error-message {
            color: red;
            font-size: 20px;
        }
    </style>
</head>

<body>
    <h1>Error Occurred</h1>
    <p class="error-message"><?php echo htmlspecialchars($message); ?></p>
    <p>Please try again later or contact the administrator.</p>
</body>

</html>