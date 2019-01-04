/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.model;

import com.fpt.da.StudentManager;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author badan
 */
public class RegisterModel extends ActionSupport {

    private int rollNumber;
    private String name;
    private String classs;
    private String email;

    @Override
    public void validate() {
//        DO VALIDATE

    }

    @Override
    public String execute() throws Exception {
//        DO ACTION
        if (new StudentManager().add(rollNumber, name, classs, email)) {
            return SUCCESS;
        }
        return ERROR;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

   

   

}
