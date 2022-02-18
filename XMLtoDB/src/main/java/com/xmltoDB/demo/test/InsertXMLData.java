package com.xmltoDB.demo.test;


import java.io.*;
import java.sql.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
public class InsertXMLData{
public static void main(String[] args) { 
try {
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "root");
Statement st=con.createStatement();
DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
System.out.println("working");
Document doc = docBuilder.parse (new File("C:/temp/persons.xml"));
System.out.println("working");
doc.getDocumentElement().normalize();
System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());
NodeList listOfPersons = doc.getElementsByTagName("person");
for(int s=0; s<listOfPersons.getLength(); s++){
Node firstPersonNode = listOfPersons.item(s);
if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){
Element firstPersonElement = (Element)firstPersonNode;

NodeList nameList = firstPersonElement.getElementsByTagName("first_name");
Element nameElement =(Element)nameList.item(0);

NodeList textFNList = nameElement.getChildNodes();
String first_name=((Node)textFNList.item(0)).getNodeValue().trim();

NodeList lastnameList = firstPersonElement.getElementsByTagName("last_name");
Element lastnameElement =(Element)lastnameList.item(0);

NodeList lasttextFNList = lastnameElement.getChildNodes();
String last_name=((Node)lasttextFNList.item(0)).getNodeValue().trim();

NodeList emailList = firstPersonElement.getElementsByTagName("email");
Element emailElement =(Element)emailList.item(0);

NodeList textLNList = emailElement.getChildNodes();
String email= ((Node)textLNList.item(0)).getNodeValue().trim();

NodeList ageList = firstPersonElement.getElementsByTagName("email");
Element ageElement =(Element)ageList.item(0);

NodeList ageLNList = ageElement.getChildNodes();
String age= ((Node)ageLNList.item(0)).getNodeValue().trim();

int i=st.executeUpdate("insert into person8(first_name,last_name,email,age) values('"+first_name+"','"+last_name+"','"+email+"','"+age+"')");
}
}
System.out.println("Data is successfully inserted!");
}catch (Exception err) {
System.out.println(" " + err.getMessage ());
}
}
}