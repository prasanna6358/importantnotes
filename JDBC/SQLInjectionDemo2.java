import java.sql.*;
import java.util.*;
public class SQLInjectionDemo2
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter username:");
		String uname=sc.next();
		System.out.println("Enter pwd:");
		String upwd=sc.next();
		String sqlQuery="select count(*) from users where uname=? and upwd=?";
		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ps.setString(1,uname);
		ps.setString(2,upwd);
		ResultSet rs =ps.executeQuery();
		int c=0;
		if(rs.next())
		{
			c=rs.getInt(1);
		}
		if(c==0)
			System.out.println("Invalid Credentials");
		else
			System.out.println("Valid Credentials");
		
		con.close();
	}
}
