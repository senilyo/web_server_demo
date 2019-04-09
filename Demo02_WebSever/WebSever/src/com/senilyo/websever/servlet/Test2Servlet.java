package com.senilyo.websever.servlet;

import com.senilyo.websever.core.Request;
import com.senilyo.websever.core.Response;
import com.senilyo.websever.core.Servlet;

import java.util.Date;

public class Test2Servlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        response.print("<html>");
        response.print("<head>");
        response.print("<meta charset=\"utf-8\">");
        response.print("<title>");
        response.print("Test2响应成功");
        response.print("</title>");
        response.print("</head>");
        response.print("<body>");
        Date date = new Date();
        response.print(date.toString());
        response.print("<br/>");
        response.print("<h1 style=\"color:red\">test2测试成功！！</h1>");
        response.print("</body>");
        response.print("</html>");
    }
}
