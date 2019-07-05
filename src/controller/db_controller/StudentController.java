package controller.db_controller;

import db.DBConnection;
import model.StudentDTO;
import model.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    public StudentDTO getStudentUsername(String uid) {
        StudentDTO studentDTO = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select student_name from user u,student s where u.uid=s.uid && u.uid=?");
            preparedStatement.setObject(1, uid);
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                studentDTO = new StudentDTO();
                studentDTO.setStudentName(rst.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return studentDTO;
    }

    public List<StudentDTO> getAllStudents(int degid, int batchid, int year, int pageNo) {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select uid,student_name,national_id from student s,batch b,degree d where b.batchid=s.batchid && d.degid=s.degid && d.degid=? && b.batchid=? && year(b.intake)=? limit ?,50 ");
            preparedStatement.setObject(1, degid);
            preparedStatement.setObject(2, batchid);
            preparedStatement.setObject(3, year);
            preparedStatement.setObject(4, pageNo);
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setUid(rst.getString(1));
                studentDTO.setStudentName(rst.getString(2));
                studentDTO.setNationalId(rst.getString(3));
                studentDTOS.add(studentDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return studentDTOS;
    }

    public boolean addStudent(StudentDTO studentDTO) {
        Connection connection = null;
        try {
            connection = DBConnection.getDBConnection().getConnection();
            connection.setAutoCommit(false);
            UserDTO userDTO = new UserDTO();
            userDTO.setUid(studentDTO.getUid());
            userDTO.setPassword(studentDTO.getNationalId());
            boolean addUser = new UserController().addUser(userDTO);
            if (addUser) {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into student (uid,degid,batchid,student_name,national_id) values (?,?,?,?,?)");
                preparedStatement.setObject(1, studentDTO.getUid());
                preparedStatement.setObject(2, studentDTO.getDegId());
                preparedStatement.setObject(3, studentDTO.getBatchId());
                preparedStatement.setObject(4, studentDTO.getStudentName());
                preparedStatement.setObject(5, studentDTO.getNationalId());
                if (preparedStatement.executeUpdate() > 0) {
                    return true;
                } else {
                    connection.rollback();
                }
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
