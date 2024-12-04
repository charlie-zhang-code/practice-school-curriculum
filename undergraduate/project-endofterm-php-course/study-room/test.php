<?php

$conn = mysqli_connect("localhost", "root", "", "study_room");
if (!$conn) {
    die("连接失败: " . mysqli_connect_error());
}

?>