<?php
session_start();

require_once $_SERVER['DOCUMENT_ROOT'] . '/data/UserData.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $username = $_POST['username'];
    $email = $_POST['email'];
    $password = $_POST['password'];

    $data = new UserData();
    $result = $data->register($username, $password, $email);

    if ($result) {
        header("Location: ../index.php");
    } else {
        header("Location: ../error.php?message=Registration failed");
    }
}