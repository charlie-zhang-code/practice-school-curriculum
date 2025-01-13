<!-- navbar-->
<header class="header bg-white">
    <div class="container px-lg-3">
        <nav class="navbar navbar-expand-lg navbar-light py-3 px-lg-0">
            <a class="navbar-brand" href="/">
                <span class="fw-bold text-uppercase text-dark">
                    简自习室
                </span>
            </a>
            <button class="navbar-toggler navbar-toggler-end" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <!-- Link--><a class="nav-link" href="/">首页</a>
                    </li>
                </ul>

                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <?php if (isset($_SESSION['username'])): ?>
                            <a class="nav-link" href="history.php">
                                <i class="fas fa-history me-1 text-gray"></i>
                                历史
                            </a>
                        <?php endif; ?>
                    </li>
                    <li class="nav-item">
                        <?php if (isset($_SESSION['username']) && ($_SESSION['is_admin'] != '0')): ?>
                            <a class="nav-link" href="admin.php">
                                <i class="fas fa-user me-1 text-gray"></i>
                                管理员
                            </a>
                        <?php endif; ?>
                    </li>
                    <li class="nav-item">
                        <?php if (isset($_SESSION['username'])): ?>
                            <a class="nav-link" href="logout.php">
                                <i class="fas fa-sign-out me-1 text-gray fw-normal"></i>
                                退出登录
                            </a>
                        <?php else: ?>
                            <a class="nav-link" href="login.php">
                                <i class="fas fa-user me-1 text-gray fw-normal"></i>
                                登录/注册
                            </a>
                        <?php endif; ?>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</header>