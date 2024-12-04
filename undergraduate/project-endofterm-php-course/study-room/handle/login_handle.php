<?php
session_start(); // 开启会话，以便在登录后设置用户会话

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $username = $_POST['username'];
    $password = $_POST['password'];

    // 连接数据库
    $conn = new mysqli("localhost", "root", "", "study_room");
    if ($conn->connect_error) {
        die("数据库连接失败: " . $conn->connect_error);
    }

    // 预处理语句以防止SQL注入
    $stmt = $conn->prepare("SELECT password FROM users WHERE username = ?");
    $stmt->bind_param("s", $username);

    // 执行预处理语句
    $stmt->execute();
    $stmt->store_result();

    // 检查用户名是否存在
    if ($stmt->num_rows > 0) {
        $stmt->bind_result($hashed_password);
        $stmt->fetch();

        // 验证密码
        if (password_verify($password, $hashed_password)) {
            // 登录成功，设置会话变量
            $_SESSION['username'] = $username;
            // 跳转到首页
            header("Location: ../index.php");
            exit;
        } else {
            // 密码错误
            $error = "无效的用户名或密码";
        }
    } else {
        // 用户名不存在
        $error = "无效的用户名或密码";
    }

    // 关闭数据库连接
    $stmt->close();
    $conn->close();
}

// 如果登录失败，显示错误信息并重定向回登录页面
if (isset($error)) {
    echo $error;
    header("Location: ../login.php?error=" . urlencode($error));
    exit;
}
?>