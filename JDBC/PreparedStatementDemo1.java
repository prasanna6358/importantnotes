import java.sql.*;
public class PreparedStatementDemo1
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		Class.forName(driver);
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		String sqlQuery ="delete from employees where ename=?";
    
		PreparedStatement pst = con.prepareStatement(sqlQuery);
		pst.setString(1,"durga");
		int updateCount=pst.executeUpdate();
		System.out.println("The number of rows deleted :"+updateCount);

		System.out.println("Reusing PreparedStatement to delete one more record...");
        pst.setString(1,"Sunny");
        int updateCount1=pst.executeUpdate();
		System.out.println("The number of rows deleted :"+updateCount1);

		con.close();
	}
}
