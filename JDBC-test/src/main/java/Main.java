import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) {
		String dbUrl = "jdbc:mysql://localhost:3306/jdbc_test?useSSL=false";
		String dbUser = "root";
		String password = "1234";
		String dbDriver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(dbDriver);
			try {
				Connection conn = DriverManager.getConnection(dbUrl, dbUser, password);
				//insert(conn);
				
				printStudent(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void insert(Connection conn) {
		String sql = "insert into student(name, age, address) values(?,?,?)";
		PreparedStatement ps;
		try {
			for(int i=1; i <=5; i++) {
				ps = conn.prepareStatement(sql);
				
				ps.setString(1, "Tai"+i);
				ps.setInt(2, 22+i);
				ps.setString(3, "Ho Chi Minh"+i);
				
				ps.execute();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void printStudent(Connection conn) {
		String sql = "select * from student";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("name")+"-"+rs.getInt("age")+"-"+rs.getString("address"));
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
}
