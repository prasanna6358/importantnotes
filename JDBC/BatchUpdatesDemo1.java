import java.sql.*;
public class BatchUpdatesDemo1 
{
	public static void main(String[] args) throws Exception
	{
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott","tiger");
		Statement st = con.createStatement();
		st.addBatch("select * from employees");
		st.addBatch("insert into employees values(400,'veena',1500,'Hyd')");
		st.addBatch("update employees set ename='krish' where eno=200");
		st.addBatch("delete from employees where esal>2700");
		int[] count=st.executeBatch();
		int updateCount=0;
		for(int x: count)
		{
			updateCount=updateCount+x;
		}
		System.out.println("The number of rows updated :"+updateCount);
		con.close();
	}
}