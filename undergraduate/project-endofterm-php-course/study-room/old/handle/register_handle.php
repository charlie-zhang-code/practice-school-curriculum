<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $username = $_POST['username'];
    $gender = $_POST['gender'];
    $email = $_POST['email'];
    $password = $_POST['password'];
    $phone = $_POST['phone'];
    $repassword = $_POST['repassword'];

    // echo "用户名: " . $username . "<br>";
    // echo "性别: " . $gender . "<br>";
    // echo "邮箱: " . $email . "<br>";
    // echo "密码: " . $password . "<br>";
    // echo "电话: " . $phone . "<br>";

    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        die("无效的邮箱地址。");
    }

    if ($password != $repassword) {
        die("两次输入的密码不一致。");
    }

    // 哈希密码
    $hashedPassword = password_hash($password, PASSWORD_DEFAULT);

    $default_avatar = 'default';
    if (isset($_POST['default_avatar'])) {
        $default_avatar = $_POST['default_avatar'];
    }

    if (isset($_FILES['avatar']) && is_array($_FILES['avatar'])) {
        $avatar = $_FILES['avatar'];
        if ($avatar['error'] == 0) {
            $uploadDir = "../uploads/"; // 设置上传目录
            if (!is_dir($uploadDir)) {
                if (!mkdir($uploadDir, 0755, true)) {
                    echo "无法创建目录：" . htmlspecialchars($uploadDir) . "<br>";
                    exit;
                }
            }
            $uploadFile = $uploadDir . basename($avatar['name']);
            if (move_uploaded_file($avatar['tmp_name'], $uploadFile)) {
                echo "文件 " . htmlspecialchars(basename($avatar['name'])) . " 已上传<br>";
            } else {
                echo "文件上传失败。<br>";
            }
        }
    } else {
        echo "没有文件被上传，或者上传出错。<br>";
    }

    // 连接数据库，保存注册用户（密码使用哈希），主机localhost，用户root，密码空，数据库名study_room
    $conn = new mysqli("localhost", "root", "", "study_room");

    if ($conn->connect_error) {
        die("连接失败: " . $conn->connect_error);
    }

    // 连接数据库
    $conn = new mysqli("localhost", "root", "", "study_room");
    if ($conn->connect_error) {
        die("数据库连接失败: " . $conn->connect_error);
    }

    // 预处理语句
    $stmt = $conn->prepare("INSERT INTO users (username, gender, email, password, phone, avatar) VALUES (?, ?, ?, ?, ?, ?)");
    $stmt->bind_param("ssssss", $username, $gender, $email, $hashedPassword, $phone, $default_avatar);

    // 执行预处理语句
    if ($stmt->execute()) {
        // echo "用户注册成功。";
        header("Location: ../login.php");
        // 关闭数据库连接
        $stmt->close();
        $conn->close();
        exit;
    } else {
        die("用户注册失败: " . $conn->error);
    }
    
    // 关闭数据库连接
    $stmt->close();
    $conn->close();
} else {
    echo "无效的请求方法。";
}
