package com.senilyo.websever.core;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class XMLParse {
    public static void main(String[] args) {
        File f = new File("WebSever/src/com/senilyo/websever/web.xml");
        XMLParse xp = new XMLParse();
        xp.readXML(f);
        List<Entity> e =xp.getEntities();
        for(Entity x:e){
            System.out.println(x.getName()+":"+x.getClz());
        }
        List<Mapping> m =xp.getMappings();
        for(Mapping x:m){
            System.out.println(x.getName()+":");
            for(String y:x.getPatterns()){
                System.out.print(y+" ");
            }
            System.out.println();
        }
        System.out.println(xp.getClz("/g"));
        System.out.println(xp.getClz("/login"));
        System.out.println(xp.getClz("/reg"));
    }

    private List<Entity> entities;
    private List<Mapping> mappings;
    private Map<String,String> entityMap;
    private Map<String,String> mappingMap;

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }

    public  void readXML(File file){
        DocumentBuilderFactory documentBuilderFactory =DocumentBuilderFactory.newInstance();//创建documentBuilder工厂
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();//获得DocumentBuilder对象
            Document document = documentBuilder.parse(file);//解析文件并获得document对象
            Element element = document.getDocumentElement();//获取具体元素
            NodeList nl = element.getElementsByTagName("servlet");
            entities = new ArrayList<>();
            for(int i=0;i<nl.getLength();++i){
                Element el = (Element) nl.item(i);
                String name = el.getElementsByTagName("servlet-name").item(0).getTextContent();
                String clz = el.getElementsByTagName("servlet-class").item(0).getTextContent();
                Entity temp = new Entity(name,clz);
                entities.add(temp);
            }
            nl = element.getElementsByTagName("servlet-mapping");
            mappings = new ArrayList<>();
            for(int i=0;i<nl.getLength();++i){
                Element el = (Element) nl.item(i);
                String name = el.getElementsByTagName("servlet-name").item(0).getTextContent();
//                System.out.println(name);
                Set<String> patterns = new HashSet<>();
                for(int j=0;j<el.getElementsByTagName("url-pattern").getLength();++j){
//                    System.out.println(el.getElementsByTagName("url-pattern").item(j).getTextContent());

                    patterns.add(el.getElementsByTagName("url-pattern").item(j).getTextContent());
//                    System.out.println(flag);
                }
//                System.out.println(patterns.size());
                Mapping temp = new Mapping(name,patterns);
                mappings.add(temp);

                entityMap = new HashMap<>();
                for(Entity e:entities){
                    entityMap.put(e.getName(),e.getClz());
                }
                mappingMap = new HashMap<>();
                for(Mapping m:mappings){
                    for(String s :m.getPatterns()){
                        mappingMap.put(s,m.getName());
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getClz(String pattern){
//        System.out.println(pattern);
        String name = mappingMap.get(pattern);
//        System.out.println(name);
        return entityMap.get(name);
    }

}
