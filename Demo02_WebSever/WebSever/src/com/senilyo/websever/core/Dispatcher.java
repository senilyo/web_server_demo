package com.senilyo.websever.core;

import java.io.IOException;
import java.net.Socket;

public class Dispatcher implements Runnable {
    private Socket client;
    private Request request;
    private  Response response;
    private XMLParse parse;
    public Dispatcher(Socket client,XMLParse parse){
        this.client=client;
        this.parse=parse;
        try {
            request = new Request(client);
            response = new Response(client);
        } catch (IOException e) {
            e.printStackTrace();
            this.release();
        }
    }

    private void release(){
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String className = parse.getClz("/"+request.getUrl());
//            System.out.println(className);
        if(className==null){
            return;
        }
        try {
            Class clz = null;
            clz = Class.forName(className);
            Servlet servlet = (Servlet) clz.getConstructor().newInstance();
            servlet.service(request,response);
            if(servlet!=null){
                response.pushToBrowser(200);
            }else{
                response.pushToBrowser(404);
            }

        } catch (Exception e) {
            System.out.println("server error!");
            response.pushToBrowser(500);
        }
        release();
    }
}
