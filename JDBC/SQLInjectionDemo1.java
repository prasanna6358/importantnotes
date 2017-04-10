import java.sql.*;
import java.util.*;
public class SQLInjectionDemo1
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter username:");
		String uname=sc.next();
		System.out.println("Enter pwd:");
		String upwd=sc.next();
		String sqlQuery="select count(*) from users where uname='"+uname+"' and upwd='"+upwd+"'";
		ResultSet rs =st.executeQuery(sqlQuery);
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
