/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.da;

import com.fpt.entity.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author badan
 */
public class StudentManager {
    private PreparedStatement pstm;
    private String sql;
    private List<Student> students;
    
    public List<Student> getStudents(){
        students = new ArrayList<>();
        try {
            sql = "select * from Student";
            pstm = new DBConnection().getConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            Student student = null;
            while(rs.next()){
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setRollNumber(rs.getInt("rollNumber"));
                student.setName(rs.getString("name"));
                student.setClass(rs.getString("class"));
                student.setEmail(rs.getString("email"));
                students.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }
    
    public boolean add(int rollNumber, String name, String _class, String email){
        try {
            sql = "insert into Student(rollNumber, name, class, email) values(?, ?, ?, ?)";
            pstm = new DBConnection().getConnection().prepareStatement(sql);
            pstm.setInt(1, rollNumber);
            pstm.setString(2, name);
            pstm.setString(3, _class);
            pstm.setString(4, email);
            if(pstm.executeUpdate() > 0) return true;
        } catch (SQLException ex) {
            Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
}
