import java.sql.*;
public class SavePointDemo1 
{
	public static void main(String[] args) throws Exception
	{
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott","tiger");
		Statement st = con.createStatement();
		con.setAutoCommit(false);
		st.executeUpdate("insert into politicians values ('kcr','trs')");
		st.executeUpdate("insert into politicians values ('babu','tdp')");
		Savepoint sp = con.setSavepoint();
		st.executeUpdate("insert into politicians values ('siddu','bjp')");
		//System.out.println("oops ..wrong entry just rollback");
		//con.rollback(sp);
		con.releaseSavepoint(sp);
		System.out.println("Operations are roll back from Savepoint");
		con.commit();
		con.close();
	}
}