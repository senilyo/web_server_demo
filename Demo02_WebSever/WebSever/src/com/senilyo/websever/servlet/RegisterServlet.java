package com.senilyo.websever.servlet;

import com.senilyo.websever.core.Request;
import com.senilyo.websever.core.Response;
import com.senilyo.websever.core.Servlet;

public class RegisterServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        System.out.println("register");
    }
}
