package com.example.helloworldapi.repository;

import com.example.helloworldapi.model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentSimpleRepo {
    private DBStudentsConnection singletonConnection;

    public StudentSimpleRepo() {
        this.singletonConnection = DBStudentsConnection.getInstanceConnection();
    }

    public List<Student> findAll(){
        try {
            List<Student> listStudents = new ArrayList<Student>();
            ResultSet resultSet = singletonConnection.getConn().createStatement().executeQuery("select * from students");
            return getStudents(listStudents, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Student save(Student student){
        try {
            PreparedStatement preparedStatement = singletonConnection.getConn().prepareStatement("insert into students(name,email,birthday) values(?,?,?)");
            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getEmail());
            preparedStatement.setString(3,student.getBirthday());
            preparedStatement.executeUpdate();
            return student;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> findById(Long id) {
        try {
            List<Student> listStudents = new ArrayList<>();
            ResultSet resultSet = singletonConnection.getConn().createStatement().executeQuery("select * from students where id="+id);
            return getStudents(listStudents, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Long id){
        try {
            PreparedStatement preparedStatement = singletonConnection.getConn().prepareStatement("delete from students where id=(?)");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> findWhereNameLike(String query){
        try {
            List<Student> listStudents = new ArrayList<>();
            ResultSet resultSet = singletonConnection.getConn().createStatement().executeQuery("select * from students where name like '%"+query+"%'");
            return getStudents(listStudents, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Student> getStudents(List<Student> listStudents, ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            Long id = resultSet.getLong(1);
            String name = resultSet.getString(4);
            String email = resultSet.getString(3);
            String birthday = resultSet.getString(2);
            Student student = new Student(id,name,email,birthday);
            listStudents.add(student);
        }
        return listStudents;
    }
}
