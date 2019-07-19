package controller.db_controller;

import db.DBConnection;
import model.Student;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    public Student getStudentUsername(Student student) {
        Student studentObj = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select student_name from user u,student s where u.uid=s.uid && u.uid=?");
            preparedStatement.setObject(1, student.getUid());
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                studentObj = new Student();
                studentObj.setStudentName(rst.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return studentObj;
    }

    public Student getStudentNic(Student student) {
        Student studentObj = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select national_id from student where uid=?");
            preparedStatement.setObject(1, student.getUid());
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                studentObj = new Student();
                studentObj.setNationalId(rst.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return studentObj;
    }

    public List<Student> getAllStudents(int degid, int batchid, int year) {
        List<Student> students = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select u.uid,student_name,national_id,emailAddress from student s,batch b,degree d,user u where b.batchid=s.batchid && d.degid=s.degid && d.degid=? && b.batchid=? && u.uid=s.uid && year(b.intake)=? order by stid desc");
            preparedStatement.setObject(1, degid);
            preparedStatement.setObject(2, batchid);
            preparedStatement.setObject(3, year);
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                Student student = new Student();
                student.setUid(rst.getString(1));
                student.setStudentName(rst.getString(2));
                student.setNationalId(rst.getString(3));
                student.setEmailAddress(rst.getString(4));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return students;
    }

    public boolean addStudent(Student student) {
        Connection connection = null;
        try {
            connection = DBConnection.getDBConnection().getConnection();
            connection.setAutoCommit(false);
            User user = new User();
            user.setUid(student.getUid());
            user.setPassword(student.getNationalId());
            user.setEmailAddress(student.getEmailAddress());
            boolean addUser = new UserController().addUser(user);
            if (addUser) {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into student (uid,degid,batchid,student_name,national_id) values (?,?,?,?,?)");
                preparedStatement.setObject(1, student.getUid());
                preparedStatement.setObject(2, student.getDegId());
                preparedStatement.setObject(3, student.getBatchId());
                preparedStatement.setObject(4, student.getStudentName());
                preparedStatement.setObject(5, student.getNationalId());
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

    public boolean updateStudent(Student student) {
        Connection connection = null;
        try {
            connection = DBConnection.getDBConnection().getConnection();
            connection.setAutoCommit(false);
            User user = new User();
            user.setUid(student.getUid());
            user.setEmailAddress(student.getEmailAddress());
            boolean updateEmail = new UserController().updateEmail(user);
            if (updateEmail) {
                PreparedStatement preparedStatement = connection.prepareStatement("update student set degid=?,batchid=?,student_name=?,national_id=? where uid=?");
                preparedStatement.setObject(1, student.getDegId());
                preparedStatement.setObject(2, student.getBatchId());
                preparedStatement.setObject(3, student.getStudentName());
                preparedStatement.setObject(4, student.getNationalId());
                preparedStatement.setObject(5, student.getUid());
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

    public boolean deleteStudent(Student student) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from user where uid=?");
            preparedStatement.setObject(1, student.getUid());
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
