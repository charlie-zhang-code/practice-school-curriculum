<?php

require_once $_SERVER['DOCUMENT_ROOT'] . '/utils/DatabaseUtil.php';

class ReservationData
{
    private $dbTool;

    public function __construct()
    {
        $this->dbTool = new DatabaseUtil();
    }

    public function __destruct()
    {
        $this->dbTool->close();
    }

    // 增加预约（状态为待确认）
    public function addReservation($user_id, $start_time, $end_time, $study_room_id, $seat_id)
    {
        $sql = "INSERT INTO reservations (user_id, start_time, end_time, study_room_id, seat_id) VALUES ('$user_id', '$start_time', '$end_time', '$study_room_id', '$seat_id')";
        $result = $this->dbTool->query($sql);

        // 返回插入的预约ID
        return $result ? $this->dbTool->insert_id() : false;
    }

    // 确认预约（状态为已确认）
    public function confirmReservation($reservation_id)
    {
        $sql = "UPDATE reservations SET `status` = 1 WHERE id = '$reservation_id'";
        $result = $this->dbTool->query($sql);

        // 设置对应座位的状态为已预约
        $sql = "UPDATE seats SET `status` = 1 WHERE id = (SELECT seat_id FROM reservations WHERE id = '$reservation_id')";
        $result = $this->dbTool->query($sql);

        return $result ? true : false;
    }

    // 根据预约ID获取预约信息
    public function getReservationById($reservation_id)
    {
        $sql = "SELECT * FROM reservations WHERE id = '$reservation_id'";
        $result = $this->dbTool->query($sql);

        return $this->dbTool->fetch_assoc($result);
    }

    // 获取用户的所有预约
    public function getUserReservations($user_id)
    {
        $sql = "
SELECT 
    t1.id AS reservation_id,
    t3.cover AS study_room_cover,
    t3.name AS study_room_name,
    t2.seat_number,
    t1.start_time,
    t1.end_time,
    t1.status
FROM
    reservations t1
 LEFT JOIN seats t2 ON t1.seat_id = t2.id
    LEFT JOIN study_rooms t3 ON t1.study_room_id = t3.id
    LEFT JOIN users t4 ON t1.user_id = t4.id
WHERE
    user_id = '$user_id'"; // 获取用户的所有预约

    $result = $this->dbTool->query($sql);
    $reservations = array();
    while ($row = $this->dbTool->fetch_assoc($result)) {
        $reservations[] = $row;
    }
    return $reservations;
    }
}