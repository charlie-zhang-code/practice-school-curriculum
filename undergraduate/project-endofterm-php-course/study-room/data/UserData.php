<?php

require_once $_SERVER['DOCUMENT_ROOT'] . '/utils/DatabaseUtil.php';

class UserData
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

    // 用户注册
    public function register($username, $password, $email)
    {
        // 密码哈希
        $password = password_hash($password, PASSWORD_DEFAULT);

        // 检查用户名是否已存在
        $sql = "SELECT * FROM users WHERE username = '$username'";
        $result = $this->dbTool->query($sql);

        if ($this->dbTool->num_rows($result) > 0) {
            return false;
        }

        // 检查邮箱是否已存在
        $sql = "SELECT * FROM users WHERE email = '$email'";
        $result = $this->dbTool->query($sql);

        if ($this->dbTool->num_rows($result) > 0) {
            return false;
        }

        // 插入新用户
        $sql = "INSERT INTO users (username, password, email) VALUES ('$username', '$password', '$email')";
        $result = $this->dbTool->query($sql);

        return $result ? true : false;
    }

    // 用户登录
    public function login($username, $password)
    {
        // 检查用户名和密码是否匹配
        $sql = "SELECT * FROM users WHERE username = '$username'";
        $result = $this->dbTool->query($sql);

        if ($this->dbTool->num_rows($result) == 0) {
            return false;
        }

        $row = $this->dbTool->fetch_array($result);

        return password_verify($password, $row['password']) ? true : false;
    }


    // 获取用户信息
    public function getUserInfo($username)
    {
        $sql = "SELECT id, name, username, avatar, email, gender,created_at FROM users WHERE username = '$username'";
        $result = $this->dbTool->query($sql);

        if ($this->dbTool->num_rows($result) == 0) {
            $this->dbTool->close();
            return false;
        }

        $row = $this->dbTool->fetch_assoc($result);
        return $row;
    }

    // 获取用户列表
    public function getUserList()
    {
        $sql = "SELECT id, name, username, avatar, email, gender, status FROM users";
        $result = $this->dbTool->query($sql);

        if ($this->dbTool->num_rows($result) == 0) {
            $this->dbTool->close();
        }

        $userList = array();
        while ($row = $this->dbTool->fetch_assoc($result)) {
            $userList[] = $row;
        }

        return $userList;
    }

    // 判断用户是否是管理员，从user_permissions表中查询
    public function isAdmin($user_id)
    {
        $sql = "SELECT * FROM user_permissions WHERE user_id = '$user_id' AND permission_id = '1'";
        $result = $this->dbTool->query($sql);

        if ($this->dbTool->num_rows($result) == 0) {
            return false;
        }

        return true;
    }

    // 更新密码
    public function updatePassword($user_id, $new_password)
    {
        $new_password = password_hash($new_password, PASSWORD_DEFAULT);
        $sql = "UPDATE users SET password = '$new_password' WHERE id = '$user_id'";
        $result = $this->dbTool->query($sql);

        return $result ? true : false;
    }
}