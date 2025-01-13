<?php
session_start();

require_once $_SERVER['DOCUMENT_ROOT'] . '/data/UserData.php';
require_once $_SERVER['DOCUMENT_ROOT'] . '/config/config.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $username = $_POST['username'];
    $password = $_POST['password'];

    $data = new UserData();
    $loginStatus = $data->login($username, $password);

    $userInfo = $data->getUserInfo($username);
    $isAdmin =  $data->isAdmin($userInfo['id']);
    if ($loginStatus == 1) {
        $_SESSION['id'] = $userInfo['id'];
        $_SESSION['username'] = $userInfo['username'];
        $_SESSION['isAdmin'] = $isAdmin;
        header("Location: ../index.php");
    } else {
        $error = "Invalid username or password";
        // header("Location: ../auth/login.php?error=" . urlencode($error));
        header("Location: ../error.php?message=Invalid username or password");
    }
}