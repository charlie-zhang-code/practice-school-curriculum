<?php

require_once $_SERVER['DOCUMENT_ROOT'] . '/utils/DatabaseUtil.php';

class RoomData
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

    /**
     * 获取所有自习室信息
     * @return array 所有自习室信息
     */
    public function getStudyRooms()
    {
        $sql = "SELECT * FROM study_rooms";
        $result = $this->dbTool->query($sql);

        $studyRooms = [];
        while ($row = $this->dbTool->fetch_assoc($result)) {
            $studyRooms[] = $row;
        }

        return $studyRooms;
    }

    // 获取所有自习室信息（含每个座位空余情况）
    public function getStudyRoomsAndSeats()
    {
        $sql = "SELECT * FROM study_rooms";
        $result = $this->dbTool->query($sql);

        $studyRooms = [];
        while ($row = $this->dbTool->fetch_assoc($result)) {
            $studyRoomId = $row['id'];

            $sql2 = "SELECT * FROM seats WHERE study_room_id = $studyRoomId";
            $result2 = $this->dbTool->query($sql2);

            $seats = [];
            while ($row2 = $this->dbTool->fetch_assoc($result2)) {
                $seats[] = $row2;
            }

            $row['seats'] = $seats;
            $studyRooms[] = $row;
        }

        return $studyRooms;
    }

    // 获取指定自习室信息（含每个座位空余情况）
    public function getStudyRoomAndSeatsById($id)
    {
        $sql = "SELECT * FROM study_rooms WHERE id = $id";
        $result = $this->dbTool->query($sql);

        $studyRoom = $this->dbTool->fetch_assoc($result);

        $studyRoomId = $studyRoom['id'];

        $sql2 = "SELECT * FROM seats WHERE study_room_id = $studyRoomId";
        $result2 = $this->dbTool->query($sql2);

        $seats = [];
        while ($row2 = $this->dbTool->fetch_assoc($result2)) {
            $seats[] = $row2;
        }

        $studyRoom['seats'] = $seats;

        return $studyRoom;
    }

    // 获取所有自习室信息（含座位空余情况,计算出空余作为和以占用座位的数量）
    public function getStudyRoomsAndSeatsCount()
    {
        $sql = "SELECT * FROM study_rooms";
        $result = $this->dbTool->query($sql);

        $studyRooms = [];
        while ($row = $this->dbTool->fetch_assoc($result)) {
            $studyRoomId = $row['id'];

            $sql2 = "SELECT * FROM seats WHERE study_room_id = $studyRoomId";
            $result2 = $this->dbTool->query($sql2);

            $seats = [];
            $emptySeatsCount = 0;
            $occupiedSeatsCount = 0;
            while ($row2 = $this->dbTool->fetch_assoc($result2)) {
                $seats[] = $row2;
                if ($row2['status'] == 0) {
                    $emptySeatsCount++;
                } else {
                    $occupiedSeatsCount++;
                }
            }

            // $row['seats'] = $seats;
            $row['empty'] = $emptySeatsCount;
            $row['occupied'] = $occupiedSeatsCount;
            $studyRooms[] = $row;
        }

        // 关闭数据库连接
        return $studyRooms;
    }

    /**
     * 获取指定自习室的信息（根据ID）
     */
    public function getStudyRoomById($id)
    {
        $sql = "SELECT * FROM study_rooms WHERE id = $id";
        $result = $this->dbTool->query($sql);

        $studyRoom = $this->dbTool->fetch_assoc($result);

        return $studyRoom;
    }

    // 根据ID获取座位信息
    public function getSeatById($id)
    {
        $sql = "SELECT * FROM seats WHERE id = $id";
        $result = $this->dbTool->query($sql);

        $seat = $this->dbTool->fetch_assoc($result);

        return $seat;
    }
}