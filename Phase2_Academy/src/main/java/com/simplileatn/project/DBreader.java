package com.simplileatn.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


import com.simplilearn.jdbc.util.JDBCUtil;




public class DBreader {
	
	private DataSource dataSource;

	public DBreader(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	//public static final String Qstudent="SELECT * FROM students";
	Connection con = null;
	Statement pStmt = null;
	ResultSet rs=null;
	
	//For Student
	public List<Student> getStudents() {

		List<Student> students = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {

			// get a connection
			myConn = dataSource.getConnection();

			// create sql stmt
			String sql = "SELECT * FROM students";
			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("fname");
				String lastName = myRs.getString("lname");
				int age = myRs.getInt("age");
				int aclass = myRs.getInt("class");

				// create new student object
				Student tempStudent = new Student(id, firstName, lastName, age, aclass);

				// add it to the list of students
				students.add(tempStudent);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		return students;

	}

	
//	public List<Student> getStudents() {
//
//		List<Student> students = new ArrayList<>();
//		
//		try {
//			//ResultSet rs= pstmt.executeQuery();
//			// get a connection
//				con=JDBCUtil.getMySqlConnection();
//			// create sql stmt
//			//String sql = "SELECT * FROM students";
//			//con = con.createStatement();
//				PreparedStatement pstmt=con.prepareStatement("SELECT * FROM students"); /*PLACE HOLDER*/
//			// execute query
//				rs= pstmt.executeQuery();
//			//rs = pstmt.executeQuery();
//
//			// process result
//				while (rs.next()) {
//
//				// retrieve data from result set row
//					int id = rs.getInt("id");
//					String firstName = rs.getString("fname");
//					String lastName = rs.getString("lname");
//					int age = rs.getInt("age");
//					int aclass = rs.getInt("class");
//
//				// create new student object
//					Student tempStudent = new Student(id, firstName, lastName, age, aclass);
//
//				// add it to the list of students
//					students.add(tempStudent);
//
//				}
//
//			} catch (Exception e) {
//			// TODO: handle exception
//			} finally {
//			// close JDBC objects
//			//cleanup(rs ,pStmt, con);
//			JDBCUtil.cleanup(rs, pStmt, con);
//			}
//			return students;
//
//		}
	
	//For teacher
	public List<Teacher> getTeachers() {
		
		List<Teacher> teachers = new ArrayList<>();
		
		try {
			//ResultSet rs= pstmt.executeQuery();
			// get a connection
				con=JDBCUtil.getMySqlConnection();
			// create sql stmt
			//String sql = "SELECT * FROM students";
			//con = con.createStatement();
				PreparedStatement pstmt=con.prepareStatement("SELECT * FROM teachers"); /*PLACE HOLDER*/
			// execute query
				rs= pstmt.executeQuery();
			//rs = pstmt.executeQuery();

			// process result
				while (rs.next()) {

				// retrieve data from result set row
					int id = rs.getInt("id");
					String firstName = rs.getString("fname");
					String lastName = rs.getString("lname");
					int age = rs.getInt("age");

					// create new student object
					Teacher temp = new Teacher(id, firstName, lastName, age);

					// add it to the list of students
					teachers.add(temp);
				}

			} catch (Exception e) {
			// TODO: handle exception
			} finally {
			// close JDBC objects
			//cleanup(rs ,pStmt, con);
			JDBCUtil.cleanup(rs, pStmt, con);
			}
			return teachers;
		
	}
	
	
	//For Subject
	
	public List<Subject> getSubjects() {
		
		List<Subject> subjects = new ArrayList<>();
		try {
			//ResultSet rs= pstmt.executeQuery();
			// get a connection
				con=JDBCUtil.getMySqlConnection();
			// create sql stmt
			//String sql = "SELECT * FROM students";
			//con = con.createStatement();
				PreparedStatement pstmt=con.prepareStatement("SELECT * FROM subjects"); /*PLACE HOLDER*/
			// execute query
				rs= pstmt.executeQuery();
			//rs = pstmt.executeQuery();

			// process result
				while (rs.next()) {

				// retrieve data from result set row
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String shortcut = rs.getString("shortcut");

					// create new student object
					Subject temp = new Subject(id, name,shortcut);

					// add it to the list of students
					subjects.add(temp);
				}

			} catch (Exception e) {
			// TODO: handle exception
			} finally {
			// close JDBC objects
			//cleanup(rs ,pStmt, con);
			JDBCUtil.cleanup(rs, pStmt, con);
			}
			return subjects;
		
	}
	
	//for Class
	
	public List<Class> getClasses(){
		
		List<Class> classes = new ArrayList<>();
		
		try {
			//ResultSet rs= pstmt.executeQuery();
			// get a connection
				con=JDBCUtil.getMySqlConnection();
			// create sql stmt
			//String sql = "SELECT * FROM students";
			//con = con.createStatement();
				PreparedStatement pstmt=con.prepareStatement("SELECT * FROM classes"); /*PLACE HOLDER*/
			// execute query
				rs= pstmt.executeQuery();
			//rs = pstmt.executeQuery();

			// process result
				while (rs.next()) {

				// retrieve data from result set row
					int id = rs.getInt("id");
					int section = rs.getInt("section");
					int subject = rs.getInt("subject");
					int teacher = rs.getInt("teacher");
					String time = rs.getString("time");

					Teacher tempTeacher = loadTeacher(teacher);
					Subject tempSubject = loadSubject(subject);

					String teacher_name = tempTeacher.getFname() + " " + tempTeacher.getLname();

					// create new student object
					Class temp = new Class(id, section, teacher_name, tempSubject.getName(), time);

					// add it to the list of students
					classes.add(temp);
				}

			} catch (Exception e) {
			// TODO: handle exception
			} finally {
			// close JDBC objects
			//cleanup(rs ,pStmt, con);
			JDBCUtil.cleanup(rs, pStmt, con);
			}
			return classes;
		
	}
	
	//LoadTeacher
	
	public Teacher loadTeacher(int teacherId) {
		
		Teacher theTeacher = null;
		try {
			//ResultSet rs= pstmt.executeQuery();
			// get a connection
				con=JDBCUtil.getMySqlConnection();
			// create sql stmt
			//String sql = "SELECT * FROM students";
			//con = con.createStatement();
				PreparedStatement pstmt=con.prepareStatement("SELECT * FROM teachers WHERE id = " + teacherId); /*PLACE HOLDER*/
			// execute query
				rs= pstmt.executeQuery();
			//rs = pstmt.executeQuery();

			// process result
				while (rs.next()) {

				// retrieve data from result set row
					int id = rs.getInt("id");
					String fname = rs.getString("fname");
					String lname = rs.getString("lname");
					int age = rs.getInt("age");
					theTeacher = new Teacher(id, fname, lname, age);

				}

			} catch (Exception e) {
			// TODO: handle exception
			} finally {
			// close JDBC objects
			//cleanup(rs ,pStmt, con);
			JDBCUtil.cleanup(rs, pStmt, con);
			}
			return theTeacher;
		
	}
	
	//For LoadSubject
	public Subject loadSubject(int subjectId) {
		Subject theSubject = null;
		
		try {
			//ResultSet rs= pstmt.executeQuery();
			// get a connection
				con=JDBCUtil.getMySqlConnection();
			// create sql stmt
			//String sql = "SELECT * FROM students";
			//con = con.createStatement();
				PreparedStatement pstmt=con.prepareStatement("SELECT * FROM subjects WHERE id = " + subjectId); /*PLACE HOLDER*/
			// execute query
				rs= pstmt.executeQuery();
			//rs = pstmt.executeQuery();

			// process result
				while (rs.next()) {

				// retrieve data from result set row
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String shortcut = rs.getString("shortcut");

					theSubject = new Subject(id, name,shortcut);

				}

			} catch (Exception e) {
			// TODO: handle exception
			} finally {
			// close JDBC objects
			//cleanup(rs ,pStmt, con);
			JDBCUtil.cleanup(rs, pStmt, con);
			}
			return theSubject;
		
	}
	
	//For loadClass
	public Class loadClass(int classId) {
		
		Class theClass = null;
		
		try {
			//ResultSet rs= pstmt.executeQuery();
			// get a connection
				con=JDBCUtil.getMySqlConnection();
			// create sql stmt
			//String sql = "SELECT * FROM students";
			//con = con.createStatement();
				PreparedStatement pstmt=con.prepareStatement("SELECT * FROM clasess WHERE id = " + classId); /*PLACE HOLDER*/
			// execute query
				rs= pstmt.executeQuery();
			//rs = pstmt.executeQuery();

			// process result
				while (rs.next()) {

				// retrieve data from result set row
					int section = rs.getInt("section");
					int subject = rs.getInt("subject");
					int teacher = rs.getInt("teacher");
					String time = rs.getString("time");

					Teacher tempTeacher = loadTeacher(teacher);
					Subject tempSubject = loadSubject(subject);

					String teacher_name = tempTeacher.getFname() + " " + tempTeacher.getLname();

				}

			} catch (Exception e) {
			// TODO: handle exception
			} finally {
			// close JDBC objects
			//cleanup(rs ,pStmt, con);
			JDBCUtil.cleanup(rs, pStmt, con);
			}
			return theClass;
		
	}
	
	//For loadClassStudents
	public List<Student> loadClassStudents(int classId){
		List<Student> students = new ArrayList<>();
		
		try {
			//ResultSet rs= pstmt.executeQuery();
			// get a connection
				con=JDBCUtil.getMySqlConnection();
			// create sql stmt
			//String sql = "SELECT * FROM students";
			//con = con.createStatement();
				PreparedStatement pstmt=con.prepareStatement("SELECT * FROM students WHERE class = " + classId); /*PLACE HOLDER*/
			// execute query
				 rs= pstmt.executeQuery();
			//rs = pstmt.executeQuery();

			// process result
				while (rs.next()) {

				// retrieve data from result set row
					int id = rs.getInt("id");
					String firstName = rs.getString("fname");
					String lastName = rs.getString("lname");
					int age = rs.getInt("age");
					int aclass = rs.getInt("class");

					// create new student object
					Student tempStudent = new Student(id, firstName, lastName, age, aclass);
					students.add(tempStudent);
				}

			} catch (Exception e) {
			// TODO: handle exception
			} finally {
			// close JDBC objects
			//cleanup(rs ,pStmt, con);
			JDBCUtil.cleanup(rs, pStmt, con);
			}
			return students;
		
	}

		
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

	try {
		if (myRs != null) {
			myRs.close();
		}
		if (myStmt != null) {
			myStmt.close();
		}
		if (myConn != null) {
			myConn.close();
		}

	} catch (Exception e) {
		e.printStackTrace();
	}


}}
