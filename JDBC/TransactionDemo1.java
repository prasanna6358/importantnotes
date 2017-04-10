import java.sql.*;
import java.util.*;
public class TransactionDemo1 
{
	public static void main(String[] args) throws Exception
	{
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott","tiger");
		Statement st = con.createStatement();
		System.out.println("Data before Transaction");
		System.out.println("-------------------------");
		ResultSet rs =st.executeQuery("select * from accounts");
		while(rs.next())
		{
			System.out.println(rs.getString(1)+"..."+rs.getInt(2));
		}
		System.out.println("Transaction begins...");
		con.setAutoCommit(false);
		st.executeUpdate("update accounts set balance=balance-10000 where name='durga'");
		st.executeUpdate("update accounts set balance=balance+10000 where name='sunny'");
		System.out.println("Can you please confirm this transaction of 10000....[Yes|No]");
		Scanner sc = new Scanner(System.in);
		String option = sc.next();
		if(option.equalsIgnoreCase("yes"))
		{
			con.commit();
			System.out.println("Transaction Commited");
		}
		else
		{
			con.rollback();
			System.out.println("Transaction Rolled Back");
		}
		System.out.println("Data After Transaction");
		System.out.println("-------------------------");
		ResultSet rs1 =st.executeQuery("select * from accounts");
		while(rs1.next())
		{
			System.out.println(rs1.getString(1)+"..."+rs1.getInt(2));
		}		
		con.close();
	}
}