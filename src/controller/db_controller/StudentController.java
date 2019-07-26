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
    public Student getStudentLandingPageData(Student student) {
        Student studentObj = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select f.name,d.name,d.degid,sem.name,sem.semid,student_name,b.name from student s,degree d,faculty f,semester sem,batch b where s.degid=d.degid && f.facid=d.facid && s.semid=sem.semid && b.batchid=s.batchid && s.uid=?");
            preparedStatement.setObject(1, student.getUid());
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                studentObj = new Student();
                studentObj.setFacultyName(rst.getString(1));
                studentObj.setDegreeName(rst.getString(2));
                studentObj.setDegId(rst.getInt(3));
                studentObj.setSemesterName(rst.getString(4));
                studentObj.setSemesterId(rst.getInt(5));
                studentObj.setStudentName(rst.getString(6));
                studentObj.setBatchName(rst.getString(7));
            }
        } catch (SQLException e) {
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
        }
        return studentObj;
    }

    public List<Student> getAllStudents(Student student) {
        List<Student> students = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select u.uid,student_name,national_id,emailAddress from student s,batch b,degree d,user u where b.batchid=s.batchid && d.degid=s.degid && u.uid=s.uid && d.degid=? && b.batchid=? && s.semid=? order by stid desc");
            preparedStatement.setObject(1, student.getDegId());
            preparedStatement.setObject(2, student.getBatchId());
            preparedStatement.setObject(3, student.getSemesterId());
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                Student studentObj = new Student();
                studentObj.setUid(rst.getString(1));
                studentObj.setStudentName(rst.getString(2));
                studentObj.setNationalId(rst.getString(3));
                studentObj.setEmailAddress(rst.getString(4));
                students.add(studentObj);
            }
        } catch (SQLException e) {
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
                PreparedStatement preparedStatement = connection.prepareStatement("insert into student (uid,degid,batchid,semId,student_name,national_id) values (?,?,?,?,?,?)");
                preparedStatement.setObject(1, student.getUid());
                preparedStatement.setObject(2, student.getDegId());
                preparedStatement.setObject(3, student.getBatchId());
                preparedStatement.setObject(4, student.getSemesterId());
                preparedStatement.setObject(5, student.getStudentName());
                preparedStatement.setObject(6, student.getNationalId());
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
                PreparedStatement preparedStatement = connection.prepareStatement("update student set degid=?,batchid=?,semid=?,student_name=?,national_id=? where uid=?");
                preparedStatement.setObject(1, student.getDegId());
                preparedStatement.setObject(2, student.getBatchId());
                preparedStatement.setObject(3, student.getSemesterId());
                preparedStatement.setObject(4, student.getStudentName());
                preparedStatement.setObject(5, student.getNationalId());
                preparedStatement.setObject(6, student.getUid());
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
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean changeSemester(Student student) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update student set semid=? where semid=?");
            preparedStatement.setObject(1, student.getSemesterId());
            preparedStatement.setObject(2, student.getSemesterId() - 1);
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        }
        return false;
    }
}
