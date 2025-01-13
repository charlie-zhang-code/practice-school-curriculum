<?php
if (session_status() === PHP_SESSION_NONE) {
  session_start();
}
?>

<nav class="navbar navbar-inverse" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse-example">
        <span class="sr-only">切换导航</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand">StudyRoom</a>
    </div>
    <div class="collapse navbar-collapse navbar-collapse-example">
      <ul class="nav navbar-nav">
        <li><a href="/">首页</a></li>
      </ul>

      <ul class="nav navbar-nav navbar-right">
        <?php if (isset($_SESSION['username'])) { ?>
          <li><a href="../detail/history.php">历史</a></li>
          <li><a href="../detail/profile.php">个人中心</a></li>
          <?php if (isset($_SESSION['isAdmin'])) { ?>
            <li><a href="../admin/index.php">管理</a></li>
          <?php } ?>
          <li><a href="../auth/logout.php">退出</a></li>
        <?php } else { ?>
          <li><a href="/auth/login.php">登录</a></li>
          <li><a href="/auth/register.php">注册</a></li>
        <?php } ?>
      </ul>
    </div><!-- END .navbar-collapse -->
  </div>
</nav>