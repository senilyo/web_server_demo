package com.senilyo.websever.servlet;

import com.senilyo.websever.core.Request;
import com.senilyo.websever.core.Response;
import com.senilyo.websever.core.Servlet;

public class TestServlet implements Servlet {

    @Override
    public void service(Request request, Response response) {
        response.print("<html>");
        response.print("<head>");
        response.print("<meta charset=\"utf-8\">");
        response.print("<title>");
        response.print("服务器响应成功");
        response.print("</title>");
        response.print("</head>");
        response.print("<body>");
        response.print("测试成功");
        response.print("</body>");
        response.print("</html>");
    }
}
