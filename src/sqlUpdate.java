import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

import com.mysql.jdbc.RowData;

public class sqlUpdate {
	
	static Connection myConn=null;
	static Statement stmt=null;
	static ResultSet rs=null;
	
	public static void main(String[] args) throws SQLException {
			delete();
			insert();
			select();
			update();
			select();
			delete();
			select();
			insert();
			select();
			
			
		}
		public static void select() throws SQLException {

		try {
			Properties pros = new Properties();
			pros.load(new FileInputStream("Day23.properties"));
			String theUser = pros.getProperty("user");
			String thePass = pros.getProperty("password");
			String theDburl = pros.getProperty("dburl");
			
			myConn= (Connection)DriverManager.getConnection(theDburl,theUser,thePass);
			stmt= myConn.createStatement();
			ResultSet rs =stmt.executeQuery("select * from student where first_name = 'George' and last_name = 'Washington'");
			System.out.println("Student ID:" + "\t " + "Fist Name:" +  "  Last Name:" + "\t  GPA:" + "\tSAT:" + "\tMajor ID:");
			System.out.println("---------------------------------------------------------------------");
//			if (rs == null) {
//				System.out.println("Student not found");
//			} 
//			
			while (rs.next()) {
				System.out.println("\t" + rs.getInt("id") + "\t " + rs.getString("first_name") + "\t    "  + rs.getString("last_name")
				+ "\t  " + rs.getDouble("gpa")+ "\t" + rs.getInt("sat") + "\t\t" + rs.getInt("major_id") );
			}
		System.out.println();
		}catch (Exception exc) {
			exc.printStackTrace();
		}finally {
			MyClose(myConn, stmt, rs);
		}
		
	}
	public static void MyClose(Connection con, Statement stmt, ResultSet rs) throws SQLException {
		if(con!=null)
			con.close();
		if (stmt !=null)	
				stmt.close();
		if (rs!=null)
			rs.close();
			
		

	}
	public static void update() throws SQLException {
		try {
		Properties pros = new Properties();
		pros.load(new FileInputStream("Day23.properties"));
		String theUser = pros.getProperty("user");
		String thePass = pros.getProperty("password");
		String theDburl = pros.getProperty("dburl");
			
		myConn= (Connection)DriverManager.getConnection(theDburl,theUser,thePass);
		stmt= myConn.createStatement();
		String sql = "update student set gpa= 3.5, sat=1450, major_id=1 where last_name = 'Washington' and sat=1600";
		
		int rowAffected= stmt.executeUpdate(sql);
		System.out.println("Row Affected: " + rowAffected);
		} catch (Exception exc) {
			exc.printStackTrace();
		}finally {
			if(myConn!=null)
				myConn.close();
			if (stmt !=null)	
			stmt.close();
			if (rs!=null)
				rs.close();
		}
		
	}

	public static void insert() throws SQLException {

		try {
			Properties pros = new Properties();
			pros.load(new FileInputStream("Day23.properties"));
			String theUser = pros.getProperty("user");
			String thePass = pros.getProperty("password");
			String theDburl = pros.getProperty("dburl");
			
			myConn= (Connection)DriverManager.getConnection(theDburl,theUser,thePass);
			stmt= myConn.createStatement();
			String query = "insert student(id,gpa,sat,first_name,last_name,major_id) values(999,4.0,1600,'George','Washington',null)";
			int rowAffected= stmt.executeUpdate(query);
			System.out.println("Row Affected: " + rowAffected);

			
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			if(myConn!=null)
				myConn.close();
			if (stmt !=null)	
			stmt.close();
			
		}

	}
	public static void delete() throws SQLException {

		try {
			Properties pros = new Properties();
			pros.load(new FileInputStream("Day23.properties"));
			String theUser = pros.getProperty("user");
			String thePass = pros.getProperty("password");
			String theDburl = pros.getProperty("dburl");
			
			myConn= (Connection)DriverManager.getConnection(theDburl,theUser,thePass);
			stmt= myConn.createStatement();
			String query = "delete from student where id = 999";
			stmt.executeUpdate(query);
			int rowAffected= stmt.executeUpdate(query);
			System.out.println("Row Affected: " + rowAffected);

			
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			if(myConn!=null)
				myConn.close();
			if (stmt !=null)	
			stmt.close();
			
		}

	}
	}