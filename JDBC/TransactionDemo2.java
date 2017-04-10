import java.sql.*;
public class TransactionDemo2 
{
	public static void main(String[] args) throws Exception
	{
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott","tiger");
		System.out.println(con.getTransactionIsolation());//2	
		con.setTransactionIsolation(8);
		System.out.println(con.getTransactionIsolation());//	
		con.close();
	}
}