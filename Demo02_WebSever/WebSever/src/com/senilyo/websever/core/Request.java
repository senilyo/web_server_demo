package com.senilyo.websever.core;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.*;

public class Request {
    private String requestInfo;
    private String method;
    private String url;
    private String queryStr;
    private Map<String, List<String>> parameterMap;
    private final String CRLF = "\r\n";
    public Request(InputStream is){
        parameterMap = new HashMap<String,List<String>>();
        try {
//            byte[] datas = new byte[1024*1024];
//            System.out.println("data yes");
//            int len = 0;
//            len = is.read(datas);
//            System.out.println("len yes");
//            System.out.println(len);
            /*强调！！！！！！inputstream用于网络编程时，先用available获取长度，在用read方法进行读取*/
            int count = 0;
            while(count == 0){
                count = is.available();
            }
            byte[] datas = new byte[count];
            int len = is.read(datas);
            this.requestInfo = new String (datas,0,len);
//            System.out.println(requestInfo);
            if(requestInfo.length()==0){
                return;
            }
//            System.out.println(requestInfo);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        parseRequestInfo();

    }
    public Request(Socket client) throws IOException {
        this(client.getInputStream());
    }

    private void parseRequestInfo(){
        this.method = this.requestInfo.substring(0,this.requestInfo.indexOf("/")).toUpperCase();
        int tempIdx1 = this.requestInfo.indexOf("/")+1;
        int tempIdx2 = this.requestInfo.indexOf("HTTP/");
        this.url = this.requestInfo.substring(tempIdx1,tempIdx2).trim();
//        System.out.println(url+"!!!!!!");
        int tempIdx3 = this.url.indexOf("?");
        if(tempIdx3>=0){
            String[] urlArray=this.url.split("\\?");
            this.url=urlArray[0];
            this.queryStr=urlArray[1];
        }
        if(queryStr!=null){
            this.queryStr=this.queryStr.trim();
        }
        this.method=this.method.trim();
        if(method.equals("POST")){
            String qstr = this.requestInfo.substring(this.requestInfo.lastIndexOf(CRLF)).trim();
            if(queryStr==null){
                queryStr = qstr;
            }else{
                queryStr +="&"+qstr;
            }
        }
        queryStr = ((queryStr == null)?"":queryStr);
        convertMap();
    }

    private  void convertMap(){
        String[] keyValues = this.queryStr.split("&");
        for(String query:keyValues){
            String[] kv = queryStr.split("=");
            kv = Arrays.copyOf(kv,2);
            String key = kv[0];
            String value = kv[1];
            if(!(value==null)){
                value = decode(kv[1]);
            }
            if(!parameterMap.containsKey(key)){
                parameterMap.put(key,new ArrayList<String>());
            }
            parameterMap.get(key).add(value);
        }
    }

    private  String decode(String value){
        try {
            return URLDecoder.decode(value,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getParameterValues(String key){
        List<String> values = this.parameterMap.get(key);
        if(values==null||values.size()<1){
            return null;
        }
        return values.toArray(new String[0]);
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getQueryStr() {
        return queryStr;
    }

    public String getParameterValue(String key){
        String[] values = getParameterValues(key);
        return ((values==null)?null:values[0]);
    }
}
