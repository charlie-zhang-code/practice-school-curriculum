package cn.zshey.controller;



import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 说明:
 * 作者: 睡到自然醒——yyl
 * 创建日期: 2024/11/4
 */

@WebServlet("/api/admin/admin/*")
public class AdminController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contextPath = request.getContextPath();
        String requestURI = request.getRequestURI();
        String op = requestURI.replace(contextPath + "/api/admin/admin/", "");
        if (op.equals("allAdmins")) {
            testAdmin(request, response);
        }

    }

    private void testAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //        请求参数是json形式
        ServletInputStream inputStream = request.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int readCount = 0;
        byte[] bytes = new byte[1024];
        while ((readCount = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, readCount);
        }
        inputStream.close();
//        获取到请求体
        String strBody = outputStream.toString("utf-8");
//        把json转为对象


        response.getWriter().println("aaaa");

    }
}
