## 使用更小的 JRE 基础镜像
FROM eclipse-temurin:21-jre

# 创建应用目录
WORKDIR /app

# 复制 JAR 文件 (确保先执行了构建)
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# 使用 JSON 格式的 CMD 以避免信号处理问题
CMD ["java", \
    "-Xms128m", \
    "-Xmx128m", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "-jar", "app.jar"]

# 暴露端口 (文档用途，实际运行时用 -p 参数)
EXPOSE 8071

# docker build -t registry.cn-beijing.aliyuncs.com/charlie_images/niapp-demo:latest .

# docker push registry.cn-beijing.aliyuncs.com/charlie_images/niapp-demo:latest

# docker run -d --name niapp-demo -p 8071:8071 registry.cn-beijing.aliyuncs.com/charlie_images/niapp-demo:latest
