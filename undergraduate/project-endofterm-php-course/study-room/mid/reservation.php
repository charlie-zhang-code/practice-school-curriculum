<?php
session_start();

require_once $_SERVER['DOCUMENT_ROOT'] . '/data/ReservationData.php';
require_once $_SERVER['DOCUMENT_ROOT'] . '/config/config.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $study_room_id = $_POST['study_room_id'];
    $start_time = $_POST['start_time'];
    $end_time = $_POST['end_time'];
    $seat_id = $_POST['seat_id'];

    $user_id = $_SESSION['id'];

    // echo $study_room_id . " " . $start_time . " " . $end_time . " " . $seat_id;

    $data = new ReservationData();
    $result = $data->addReservation($user_id, $start_time, $end_time, $study_room_id, $seat_id);

    if ($result) {
        // header("Location: ../index.php");
        // 跳转到预约确认页面
        header("Location: ../service/reservation_confirm.php?reservation_id=" . $result);
    } else {
        header("Location: ../error.php?message=Reservation failed");
    }
}

if ($_SERVER["REQUEST_METHOD"] == "GET") {
    $reservation_id = $_GET['reservation_id'];
    $data = new ReservationData();
    $data->confirmReservation($reservation_id);
    header("Location: ../detail/reservations.php");
}