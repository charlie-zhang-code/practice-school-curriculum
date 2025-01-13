<?php
session_start();

require_once $_SERVER['DOCUMENT_ROOT'] . './data/UserData.php';
require_once $_SERVER['DOCUMENT_ROOT'] . './config/config.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $username = $_POST['username'];
    $password = $_POST['password'];

    echo $username . " " . $password;

    $data = new UserData();
    $loginStatus = $data->login($username, $password);

    $userInfo = $data->getUserInfo($username);
    if ($loginStatus == 1) {
        // $isAdmin = $data->isAdmin($userInfo['id']);
    //     $isAdmin = 1;
        $_SESSION['id'] = $userInfo['id'];
        $_SESSION['username'] = $userInfo['username'];
        $_SESSION['isAdmin'] = 1;
        header("Location: ../index.php");
    } else {
        $error = "Invalid username or password";
        // header("Location: ../auth/login.php?error=" . urlencode($error));
        header("Location: ../error.php?message=Invalid username or password");
    }
}