/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hung.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author ADMIN
 */
public class Dao {

    private Connection conn;
    String url = "D:\\XMLFile\\";

    public Dao() {
        try {
            //Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/products", "sa", "sa");
        } catch (SQLException e) {
            System.out.println("loi roi");
        }
    }

    public ResultSet getData() {
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from product");
        } catch (Exception e) {
        }
        return rs;
    }

    public void writeXML() throws ParserConfigurationException, SQLException, TransformerConfigurationException, TransformerException, IOException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.newDocument();
        Element element = document.createElement("products");
        document.appendChild(element);
        ResultSet rs = this.getData();
        while (rs.next()) {
            Element child = document.createElement("product");
            element.appendChild(child);
            Element ProductID = document.createElement("ProductID");
            ProductID.setTextContent(rs.getString("ProductID"));

            child.appendChild(ProductID);
            System.out.println("ProductID: " + rs.getString("ProductID"));

            Element ProductName = document.createElement("ProductName");
            ProductName.setTextContent(rs.getString("ProductName"));
            child.appendChild(ProductName);
            System.out.println("ProductName: " + rs.getString("ProductName"));

            Element SupplierID = document.createElement("SupplierID");
            SupplierID.setTextContent(rs.getString("SupplierID"));
            child.appendChild(SupplierID);
            System.out.println("SupplierID: " + rs.getString("SupplierID"));

            Element CategoryID = document.createElement("CategoryID");
            CategoryID.setTextContent(rs.getString("CategoryID"));
            child.appendChild(CategoryID);
            System.out.println("CategoryID: " + rs.getString("CategoryID"));

            Element QuantityPerUnit = document.createElement("QuantityPerUnit");
            QuantityPerUnit.setTextContent(rs.getString("QuantityPerUnit"));
            child.appendChild(QuantityPerUnit);
            System.out.println("QuantityPerUnit: " + rs.getString("QuantityPerUnit"));

            Element UnitPrice = document.createElement("UnitPrice");
            UnitPrice.setTextContent(rs.getString("UnitPrice"));
            child.appendChild(UnitPrice);
            System.out.println("UnitPrice: " + rs.getString("UnitPrice"));

            Element UnitsInStock = document.createElement("UnitsInStock");
            UnitsInStock.setTextContent(rs.getString("UnitsInStock"));
            child.appendChild(UnitsInStock);
            System.out.println("unitsstock: " + rs.getString("UnitsInStock"));

            Element UnitsOnOrder = document.createElement("UnitsOnOrder");
            UnitsOnOrder.setTextContent(rs.getString("UnitsOnOrder"));
            child.appendChild(UnitsOnOrder);
            System.out.println("UnitsOnOrder: " + rs.getString("UnitsOnOrder"));

            Element ReorderLevel = document.createElement("ReorderLevel");
            ReorderLevel.setTextContent(rs.getString("ReorderLevel"));
            child.appendChild(ReorderLevel);
            System.out.println("ReorderLevel: " + rs.getString("ReorderLevel"));
        }
        System.out.println("start write...");
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "iso-8859-1");
        System.out.println("continue write...");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        DOMSource soure = new DOMSource(document);
        transformer.transform(soure, result);
        FileWriter fw = new FileWriter(new File(url + "Products.xml"));
        System.out.println("create file success");
        fw.write(writer.toString());
        fw.close();

    }

    public static void main(String[] args) throws SQLException {
        Dao da = new Dao();
        ResultSet rs = da.getData();
        if (rs != null) {
            System.out.println("success");
        } else {
            System.out.println("fail!!!");
        }
    }

}
