<?php

class DatabaseUtil
{
    private $host = "localhost";
    private $username = "root";
    private $password = "";
    private $database = "study_room_management";
    private $conn;

    public function __construct()
    {
        $this->connect();
    }

    private function connect()
    {
        $this->conn = new mysqli($this->host, $this->username, $this->password, $this->database);
        if ($this->conn->connect_error) {
            // 可以记录错误日志或抛出异常
            error_log("数据库连接失败: " . $this->conn->connect_error);
            throw new Exception("数据库连接失败: " . $this->conn->connect_error);
        }
        $this->conn->set_charset("utf8");
    }

    public function query($sql)
    {
        if ($this->conn === null) {
            throw new Exception("数据库连接已关闭或未初始化");
        }
        return $this->conn->query($sql);
    }

    public function fetch_assoc($result)
    {
        if ($result === false) {
            return false;
        }
        return $result->fetch_assoc();
    }

    public function fetch_array($result)
    {
        if ($result === false) {
            return false;
        }
        return $result->fetch_array();
    }

    public function num_rows($result)
    {
        if ($result === false) {
            return 0;
        }
        return $result->num_rows;
    }

    public function close()
    {
        if ($this->conn !== null) {
            $this->conn->close();
            $this->conn = null; // 明确地将连接设置为null
        }
    }

    public function insert_id()
    {
        return $this->conn->insert_id;
    }

    public function fetch_all($result)
    {
        if ($result === false) {
            return false;
        }
        return $result->fetch_all();
    }
}