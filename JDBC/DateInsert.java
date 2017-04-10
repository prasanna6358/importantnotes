import java.sql.*;
import java.util.*;
import java.text.*;
public class DateInsert
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
		System.out.println("Enter Person No:");
		int uno=sc.nextInt();
		System.out.println("Enter Person Name:");
		String uname=sc.next();
		System.out.println("Enter DOB(dd-mm-yy):");
		String udob=sc.next();

		System.out.println("Enter DOJ(yyyy-mm-dd):");
		String udoj=sc.next();

		SimpleDateFormat sdf= new SimpleDateFormat("dd-mm-yy");
		java.util.Date sdob=sdf.parse(udob);
		java.sql.Date sqdob= new java.sql.Date(sdob.getTime());

		java.sql.Date sqdoj=java.sql.Date.valueOf(udoj);

		String sqlQuery="insert into persons values(?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ps.setInt(1,uno);
		ps.setString(2,uname);
		ps.setDate(3,sqdob);
		ps.setDate(4,sqdoj);
		int  rc =ps.executeUpdate();
		if(rc==0)
			System.out.println("Record Not inserted");
		else
			System.out.println("Record Not inserted");
		
		con.close();
	}
}
