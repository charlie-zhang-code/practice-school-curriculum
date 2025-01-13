<?php

// if (isset($_FILES['avatar']) && is_array($_FILES['avatar'])) {
//     $avatar = $_FILES['avatar'];
//     if ($avatar['error'] == 0) {
//         $uploadDir = "../uploads/"; // 设置上传目录
//         if (!is_dir($uploadDir)) {
//             if (!mkdir($uploadDir, 0755, true)) {
//                 echo "无法创建目录：" . htmlspecialchars($uploadDir) . "<br>";
//                 exit;
//             }
//         }
//         $uploadFile = $uploadDir . basename($avatar['name']);
//         if (move_uploaded_file($avatar['tmp_name'], $uploadFile)) {
//             echo "文件 " . htmlspecialchars(basename($avatar['name'])) . " 已上传<br>";
//         } else {
//             echo "文件上传失败。<br>";
//         }
//     }
// } else {
//     echo "没有文件被上传，或者上传出错。<br>";
// }