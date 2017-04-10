import java.sql.*;
import java.util.*;
import java.io.*;
class JdbcPropertiesDemo2
{
	public static void main(String[] args) throws Exception
	{
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream("db1.properties");
		p.load(fis);// to load all properties from properties file into java Properties object
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",p);
		Statement st =con.createStatement();
		ResultSet rs=st.executeQuery("select * from employees");
		System.out.println("ENO\tENAME\tESAL\tEADDR");
		System.out.println("---------------------------");
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getFloat(3)+"\t"+rs.getString(4));
		}
		con.close();
	}
}