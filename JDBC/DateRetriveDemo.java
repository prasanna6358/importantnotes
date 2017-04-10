import java.sql.*;
import java.text.*;
public class DateRetriveDemo
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		PreparedStatement ps = con.prepareStatement("select * from users6");
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
		ResultSet rs =ps.executeQuery();
		while(rs.next())
		{
			String name=rs.getString(1);
			java.sql.Date sdate=rs.getDate(2);
			String s =sdf.format(sdate);
            System.out.println(name+"..."+s);
		}
		con.close();
	}
}
