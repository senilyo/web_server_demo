package com.senilyo.websever.test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DoXmlWithDOM {
    /*test*/
    public static void main(String[] args) {
        File file = new File("WebSever/src/com/senilyo/websever/Person.xml");
        List<Client> clients =new ArrayList<>();
        clients = readXML(file);
        for(Client x:clients){
            System.out.println(x.getName()+":"+x.getSex());
        }

    }

    /**
     *读取XML文档中信息并返回Client ArrayList
     *
     */
    public static List<Client> readXML(File file){
        /*1.创建DocumentBuilderFactory工厂对象*/
        DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
        try {
            /*2.通过工厂对象创建DocumentBuilder类*/
            DocumentBuilder dbuilder = dbfactory.newDocumentBuilder();
            /*3.通过DocumentBuilder解析xml文件*/
            Document doc = dbuilder.parse(file);
            /*4.通过Document的getDocumentElement方法获得xml元素*/
            Element element = doc.getDocumentElement();
            /*5.获取主类*/
            NodeList nl = element.getElementsByTagName("client");
            /*创建返回容器*/
            List<Client> msg = new ArrayList<>();
            /*将数据读入List容器*/
            for(int i=0;i<nl.getLength();++i){
                Element el = (Element) nl.item(i);
                String name =el.getElementsByTagName("name").item(0).getTextContent();
                String sex =el.getElementsByTagName("sex").item(0).getTextContent();
                String number =el.getElementsByTagName("number").item(0).getTextContent();
                String days =el.getElementsByTagName("days").item(0).getTextContent();
                String room =el.getElementsByTagName("room").item(0).getTextContent();
                String dateBegin =el.getElementsByTagName("dateBegin").item(0).getTextContent();
                String dateEnd =el.getElementsByTagName("dateEnd").item(0).getTextContent();
                String pay =el.getElementsByTagName("pay").item(0).getTextContent();
                Client temp = new Client(name,sex,Long.parseLong(number),Integer.parseInt(days),Integer.parseInt(room),dateBegin,dateEnd,pay);
                msg.add(temp);
            }
            /*返回容器*/
            return msg;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
