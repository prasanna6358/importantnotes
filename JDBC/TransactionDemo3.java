import java.sql.*;
public class TransactionDemo3 
{
	public static void main(String[] args) throws Exception
	{
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb","root","root");
		System.out.println(con.getTransactionIsolation());//2	
		con.setTransactionIsolation(8);
		System.out.println(con.getTransactionIsolation());//8	
		con.close();
	}
}