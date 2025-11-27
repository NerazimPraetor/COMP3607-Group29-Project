package comp3607.groupproject;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XMLHandler implements InputHandler{
    @Override
    public List<GameContent> parse(File f){
        List<GameContent> questions = new ArrayList<>();

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(f);

            NodeList nodes = doc.getElementsByTagName("QuestionItem");

            for(int i = 0; i < nodes.getLength(); i++){
                Element e = (Element) nodes.item(i);

                GameContent question = new GameContent(e.getElementsByTagName("Category").item(0).getTextContent(), Integer.parseInt(e.getElementsByTagName("Value").item(0).getTextContent()), e.getElementsByTagName("QuestionText").item(0).getTextContent(), e.getElementsByTagName("Options").item(0).getElementsByTagName("OptionA").item(0).getTextContent(), e.getElementsByTagName("Options").item(0).getElementsByTagName("OptionB").item(0).getTextContent(), e.getElementsByTagName("Options").item(0).getElementsByTagName("OptionC").item(0).getTextContent(), e.getElementsByTagName("Options").item(0).getElementsByTagName("OptionD").item(0).getTextContent(), e.getElementsByTagName("CorrectAnswer").item(0).getTextContent().charAt(0));

                questions.add(question);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return questions;
    }
}