<?php
session_start();
$id = isset($_GET['id']) ? $_GET['id'] : '';

// 连接数据库
$conn = new mysqli("localhost", "root", "", "study_room");
if ($conn->connect_error) {
  die("数据库连接失败: " . $conn->connect_error);
}

// 查询数据表显示出来
$sql = "SELECT id, name, capacity, available_seats FROM study_rooms WHERE id = $id";
$result = $conn->query($sql);

// 判断available_seats是否等于0
if ($result->num_rows > 0) {
  $row = $result->fetch_assoc();
  if ($row['available_seats'] == 0) {
    echo "<script>alert('该自习室已满，请选择其他自习室')</script>";
    echo "<script>window.location.href='../index.php'</script>";
    $conn->close();
    exit();
  }
}

// 插入预约记录
$uid = $_SESSION['id'];
$rid = $id;
$sql = "INSERT INTO order_record (uid, rid) VALUES ($uid, $rid)";
if ($conn->query($sql) === TRUE) {
  echo "<script>alert('预约成功')</script>";
  echo "<script>window.location.href='../index.php'</script>";
  $conn->close();
} else {
  echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>