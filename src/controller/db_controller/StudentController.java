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

    //--------------------------------Get student landing page data via student's user id-------------------------------
    public Student getStudentLandingPageData(Student student) {
        Student studentObj = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("select f.name,d.name,d.degid,sem.name,sem.semid,student_name,b.name from student s,degree d,faculty f,semester sem,batch b where s.degid=d.degid && f.facid=d.facid && s.semid=sem.semid && b.batchid=s.batchid && s.uid=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, student.getUid());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            if (rst.next()) {//---Navigate pointer to results
                studentObj = new Student();
                studentObj.setFacultyName(rst.getString(1));//---Set table row data to student model object
                studentObj.setDegreeName(rst.getString(2));//---Set table row data to student model object
                studentObj.setDegId(rst.getInt(3));//---Set table row data to student model object
                studentObj.setSemesterName(rst.getString(4));//---Set table row data to student model object
                studentObj.setSemesterId(rst.getInt(5));//---Set table row data to student model object
                studentObj.setStudentName(rst.getString(6));//---Set table row data to student model object
                studentObj.setBatchName(rst.getString(7));//---Set table row data to student model object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return studentObj;//---Return student object if user exist, if not return null
    }

    //-------------------------------Get student national id number via student's user id-------------------------------
    public Student getStudentNic(Student student) {
        Student studentObj = null;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("select national_id from student where uid=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, student.getUid());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            if (rst.next()) {//---Navigate pointer to results
                studentObj = new Student();//--Creates student object if nic exist
                studentObj.setNationalId(rst.getString(1));//---Set table row data to student model object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return studentObj;//---Return student object if nic exist, if not return null
    }

    //-------------------------------Get all students via degree id, batch id, semester id------------------------------
    public List<Student> getAllStudents(Student student) {
        List<Student> students = new ArrayList<>();//---Creates an array object (Arraylist) to store multiple objects
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("select u.uid,student_name,national_id,emailAddress from student s,batch b,degree d,user u where b.batchid=s.batchid && d.degid=s.degid && u.uid=s.uid && d.degid=? && b.batchid=? && s.semid=? order by stid desc");//---Prepare sql as a java object
            preparedStatement.setObject(1, student.getDegId());//---Set values to sql object
            preparedStatement.setObject(2, student.getBatchId());//---Set values to sql object
            preparedStatement.setObject(3, student.getSemesterId());//---Set values to sql object
            ResultSet rst = preparedStatement.executeQuery();//---Execute sql and store result
            while (rst.next()) {//---Navigate pointer to result rows until it ends
                Student studentObj = new Student();//---Creates a student object
                studentObj.setUid(rst.getString(1));//---Set table row data to student model object
                studentObj.setStudentName(rst.getString(2));//---Set table row data to student model object
                studentObj.setNationalId(rst.getString(3));//---Set table row data to student model object
                studentObj.setEmailAddress(rst.getString(4));//---Set table row data to student model object
                students.add(studentObj);//---Add student object to array object
            }
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        }
        return students;//---Return students array object with a length > 0 if students exists, if not array object returns with a length = 0
    }

    //--------------------Change the semester of all students for respective faculty and batch--------------------------
    public boolean changeSemester(Student student) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            PreparedStatement preparedStatement = connection.prepareStatement("update student s,degree d set semid=? where semid=? && d.degid=s.degid && facid=? && batchid=?");//---Prepare sql as a java object
            preparedStatement.setObject(1, student.getSemesterId());//---Set values to sql object
            preparedStatement.setObject(2, student.getSemesterId() - 1);//---Set values to sql object
            preparedStatement.setObject(3, student.getFacultyId());//---Set values to sql object
            preparedStatement.setObject(4, student.getBatchId());//---Set values to sql object
            if (preparedStatement.executeUpdate() > 0) {//---Execute sql and returns whether it was executed or not
                return true;
            }
        } catch (SQLException e) {//---Catch if any sql exception occurred
            e.printStackTrace();
        }
        return false;//---Returns if update fails
    }

    //---------------------------Add student (This process add user and student simultaneously)-------------------------
    public boolean addStudent(Student student) {
        Connection connection = null;
        try {
            connection = DBConnection.getDBConnection().getConnection();//---Get database connection
            connection.setAutoCommit(false);//---Disable automatically commit(write) data on database

            //--------------------------------------Set data to model object--------------------------------------------
            User user = new User();
            user.setUid(student.getUid());
            user.setPassword(student.getNationalId());
            user.setEmailAddress(student.getEmailAddress());

            if (new UserController().addUser(user)) {//---Call the db server (UserController(db_controller)) to add user
                PreparedStatement preparedStatement = connection.prepareStatement("insert into student (uid,degid,batchid,semId,student_name,national_id) values (?,?,?,?,?,?)");//---Prepare sql as a java object
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

    //-------------------------------------------------Update student---------------------------------------------------
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

    //-------------------------------------------------Delete student---------------------------------------------------
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
