/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hung.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author ADMIN
 */
public class CheckXMLDocumentError {

    public static String url = "D:\\XMLFile\\";

    public static void main(String[] args) throws TransformerException, SAXException, IOException, ParserConfigurationException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the file name: ");
        String fileName = input.next();
        File newFile = new File(url + fileName);
        System.out.println("File path: " + newFile.exists());
        if (newFile.exists()) {
            System.out.println("ok file exitss");
            System.out.println("start check error of file " + fileName);
            DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = parser.parse(new File(url + fileName));
            Schema schema = SchemaFactory.
                    newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).
                    newSchema(new StreamSource(new File("D:\\NetbeanProjects\\INTXML-Practical\\src\\java\\com\\hung\\controller\\product.xsd")));
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(doc));
            System.out.println("valid");

        } else {
            Dao dao = new Dao();
            try {
                System.out.println("Create XML Document");
                dao.writeXML();
                System.out.println("Create Success");
            } catch (IOException | SQLException | TransformerConfigurationException | ParserConfigurationException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
