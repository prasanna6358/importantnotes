import java.sql.*;
public class SavePointDemo1T1 
{
	public static void main(String[] args) throws Exception
	{
		Connection con = DriverManager.getConnection("jdbc:odbc:demodsn","scott","tiger");
		Statement st = con.createStatement();
		con.setAutoCommit(false);
		st.executeUpdate("insert into politicians values ('kcr','trs')");
		st.executeUpdate("insert into politicians values ('babu','tdp')");
		Savepoint sp = con.setSavepoint();
		st.executeUpdate("insert into politicians values ('siddu','bjp')");
		System.out.println("oops ..wrong entry just rollback");
		con.rollback(sp);
		System.out.println("Operations are roll back from Savepoint");
		con.commit();
		con.close();
	}
}
//Note: For Type-1 Driver we will get  UnsupportedOperationException 
//Savepoint sp = con.setSavepoint();