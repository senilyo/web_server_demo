package com.senilyo.websever.core;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class Response {
    private BufferedWriter bw;
    private StringBuilder content;
    private StringBuilder headInfo;
    private int len =  0;
    private final String BLANK = " ";
    private final String CRLF = "\r\n";
    public Response(Socket client){
        this();
        try {
          bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Response(OutputStream os){
        this();
        bw = new BufferedWriter(new OutputStreamWriter(os));
    }
    private Response(){
        content = new StringBuilder();
        headInfo = new StringBuilder();
    }


    public Response print(String info){
        content.append(info);
        len+=info.getBytes().length;
        return this;
    }

    public Response println(String info){
        content.append(info).append(CRLF);
        len+=(info+CRLF).getBytes().length;
        return this;
    }

    public void pushToBrowser(int code){
        creatHeadInfo(code);
        try {
            bw.append(headInfo);
            bw.append(content);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void creatHeadInfo(int code){
        headInfo.append("HTTP/1.1").append(BLANK);
        headInfo.append(code).append(BLANK);
        switch (code){
            case 200:
                headInfo.append("OK").append(CRLF);
                break;
            case 404:
                headInfo.append("Not Found").append(CRLF);
                break;
            case 500:
                headInfo.append("Server Error").append(CRLF);
                break;
        }
        headInfo.append("Date:").append(new Date()).append(CRLF);
        headInfo.append("Server:").append("Senilyo Server/0.0.1;charset=UTF-8").append(CRLF);
        headInfo.append("Content-type:text/html").append(CRLF);
        headInfo.append("Content-length:").append(len).append(CRLF);
        headInfo.append(CRLF);
    }
}
