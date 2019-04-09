package com.senilyo.websever.core;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private  ServerSocket serverSocket;
    private File file;
    private XMLParse parse;
    private boolean isRunning;
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start(){
        try {
            serverSocket = new ServerSocket(9399);
            file = new File("WebSever/src/com/senilyo/websever/servlet/web.xml");
            parse = new XMLParse();
            parse.readXML(file);
            isRunning = true;
            receive();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败");
        }
    }

    public void receive() {
        while(true){
            try {
                Socket client = serverSocket.accept();
                new Thread(new Dispatcher(client,parse)).start();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("连接失败");
            }
        }
    }

    public void stop(){
        isRunning = false;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
