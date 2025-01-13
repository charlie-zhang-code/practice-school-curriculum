<?php
require_once $_SERVER['DOCUMENT_ROOT'] . '/data/RoomData.php';

$data = new RoomData();
$studyRooms = $data->getStudyRoomsAndSeatsCount();

echo '<div class="cards">';
foreach ($studyRooms as $room) {
    $roomId = $room["id"];
    echo '
            <div class="col-md-4 col-sm-6 col-lg-3">
                <div class="card">
                    <img src="../uploads/' . $room["cover"] . '" alt="" onclick="location.href=\'detail/room.php?id=' . $roomId . '\'">
                    <div class="card-heading" onclick="location.href=\'detail/room.php?id=' . $roomId . '\'">
                        <strong>' . $room["name"] . '</strong>
                        <span class="label label-warning">牡丹亭</span>
                    </div>
                    
                    <div class="card-content text-muted" onclick="location.href=\'detail/room.php?id=' . $roomId . '\'">
                        ' . $room["description"] . '
                    </div>

                    <div class="card-actions">
                        <button type="button" class="btn btn-danger" onclick="location.href=\'service/reservations.php?id=' . $roomId . '\'">
                            <i class="icon icon-check-plus"></i> 
                            预约
                        </button>

                        
                        <div class="pull-right text-danger">
                            <i class="icon icon-rocket"></i> 
                            占用 ' . $room["occupied"] . '  / 总 ' . $room["capacity"] . '
                        </div>
                    </div>
                </div>
            </div>
        ';
}
echo '</div>';