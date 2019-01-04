<%-- 
    Document   : index
    Created on : Jan 4, 2019, 10:09:16 AM
    Author     : badan
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register halloween event</title>
    </head>
    <body>
        <h1>Register form</h1>
        <form action="registerEvent">
            Roll Number: <input name="rollNumber"/><br/>
            Name: <input name="name"/><br/>
            Class: <input name="classs"/><br/>
            Email: <input name="email"/><br/>
            <input type="submit" value="Register"/>
        </form>
    </body>
</html>
