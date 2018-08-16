package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
	getConnection() - db����
	close() - db����
*/

public class DBconn {
	
	public Connection getConnection()
	{
		String className="com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://my5509.gabiadb.com:3306/mydb";  
		String user = "bit504";  		//mysql ���̵�
		String password = "bitcamp504*";//��й�ȣ
		Connection conn = null;
		
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}
	
	
	
	public void close(Connection conn,PreparedStatement pstmt)
	{
		if(conn!=null)try {conn.close();}catch(Exception e) {}
		if(pstmt!=null)try {pstmt.close();}catch(Exception e) {}
	}
}