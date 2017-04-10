Application-1: How to Create a Table
========================
import java.sql.*;
public class CreateTableDemo 
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		String sql_query="create table employees(eno number,ename varchar2(10),esal number,eaddr varchar2(10))";
        Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		st.executeUpdate(sql_query);
		System.out.println("Table Created Successfully");
		con.close();
	}
}
Application-2: How to Delete a Table
========================
import java.sql.*;
public class DropTableDemo 
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		String sql_query="drop table students";
        Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		st.executeUpdate(sql_query);
		System.out.println("Table Deleted Successfully");
		con.close();
	}
}
Application-3: How to Insert a record into Table
===============================
import java.sql.*;
public class InsertSingleRowDemo 
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		String sql_query="insert into employees values(100,'durga',1000,'hyd')";
        Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		int updateCount=st.executeUpdate(sql_query);
		System.out.println("The number of rows inserted :"+updateCount);
		con.close();
	}
}
Application-4: How to Insert Multiple records into Table
====================================
import java.sql.*;
import java.util.*;
public class InsertMultipleRowsDemo 
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			System.out.println("Employee Number:");
			int eno=sc.nextInt();
			System.out.println("Employee Name:");
			String ename=sc.next();
			System.out.println("Employee Sal:");
			double esal=sc.nextDouble();
			System.out.println("Employee Address:");
			String eaddr=sc.next();
			String sqlQuery=String.format("insert into employees values(%d,'%s',%f,'%s')",eno,ename,esal,eaddr);
			st.executeUpdate(sqlQuery);
			System.out.println("Record Inserted Successfully");
			System.out.println("Do U want to Insert one more record[Yes/No]:");
			String option = sc.next();
			if(option.equalsIgnoreCase("No"))
			{
				break;
			}
		}
		con.close();
	}
}
Application-5: How to update a record in the Table
===============================
import java.sql.*;
public class UpdateSingleRowDemo
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		String sql_query="update employees set esal=10000 where ename='durga'";
        Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		int updateCount=st.executeUpdate(sql_query);
		System.out.println("The number of rows updated :"+updateCount);
		con.close();
	}
}
Application-6: How to update multiple records in the Table
======================================
import java.sql.*;
import java.util.*;
public class UpdateMultipleRowsDemo
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Bonus Amount:");
		double bonus =sc.nextDouble();
		System.out.println("Enter Salary Range:");
		double salRange =sc.nextDouble();
		String sqlQuery=String.format("update employees set esal=esal+%f where esal<%f",bonus,salRange);
		int updateCount=st.executeUpdate(sqlQuery);
		System.out.println("The number of rows updated :"+updateCount);
		con.close();
	}
}
Application-7: How to delete a record from the Table
==================================
import java.sql.*;
public class DeleteSingleRowDemo
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		String sqlQuery="delete from employees where ename='durga'";
        Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		int updateCount=st.executeUpdate(sqlQuery);
		System.out.println("The number of rows deleted :"+updateCount);
		con.close();
	}
}
Application-8: How to delete multiple records from the Table
=======================================
import java.sql.*;
import java.util.*;
public class DeleteMultipleRowsDemo
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter CutOff Salary:");
		double cutOff =sc.nextDouble();
		String sqlQuery=String.format("delete from employees where esal>=%f",cutOff);
		int updateCount=st.executeUpdate(sqlQuery);
		System.out.println("The number of rows deleted :"+updateCount);
		con.close();
	}
}
Application-9: How to Select All Rows  from the Table
==================================
import java.sql.*;
public class SelectAllRowsDemo
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		String sqlQuery="select * from employees";
		boolean flag= false;
		ResultSet rs =st.executeQuery(sqlQuery);
		System.out.println("ENO\tENAME\tESALARY\tEADDR");
		System.out.println("--------------------------------------");
		while(rs.next())
		{
			flag=true;
			//System.out.println(rs.getInt("eno")+"\t"+rs.getString("ename")+"\t"+rs.getDouble("esal")+"\t"+rs.getString("eaddr"));
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getString(4));
		}
		if(flag==false)
		{
			System.out.println("No Records found");
		}
		con.close();
	}
}
Application-10: How to Select All Rows  from the Table based on Sorting order of the salaries 
==================================
import java.sql.*;
public class SelectAllRowsSortingDemo
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		String sqlQuery="select * from employees order by esal DESC";
		boolean flag= false;
		ResultSet rs =st.executeQuery(sqlQuery);
		System.out.println("ENO\tENAME\tESALARY\tEADDR");
		System.out.println("--------------------------------------");
		while(rs.next())
		{
			flag=true;
			//System.out.println(rs.getInt("eno")+"\t"+rs.getString("ename")+"\t"+rs.getDouble("esal")+"\t"+rs.getString("eaddr"));
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getString(4));
		}
		if(flag==false)
		{
			System.out.println("No Records found");
		}
		con.close();
	}
}
Note: For Ascending order query is :
select * from employees order by esal ASC;
 
Application-11: How to Select Particular Columns  from the Table
=========================================
import java.sql.*;
public class SelectParticularColumnsDemo
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		String sqlQuery="select ename,eaddr from employees";
		boolean flag=false;
		ResultSet rs =st.executeQuery(sqlQuery);
		System.out.println("ENAME\tEADDR");
		System.out.println("------------------");
		while(rs.next())
		{
			flag=true;
			System.out.println(rs.getString("ename")+"\t"+rs.getString("eaddr"));
			//System.out.println(rs.getString(1)+"\t"+rs.getString(2));
		}
		if(flag==false)
		{
			System.out.println("No Records found");
		}
		con.close();
	}
}

Application-12: How to Select Range of Records based on Address
=========================================

import java.sql.*;
import java.util.*;

public class SelectRangeOfRecordsDemo1
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter City Name:");
		String addr=sc.next();
		String sqlQuery=String.format("select * from employees where eaddr='%s'",addr);
		boolean flag=false;
		ResultSet rs =st.executeQuery(sqlQuery);
		System.out.println("ENO\tENAME\tESALARY\tEADDR");
		System.out.println("--------------------------------------");
		while(rs.next())
		{
			flag=true;
			//System.out.println(rs.getInt("eno")+"\t"+rs.getString("ename")+"\t"+rs.getDouble("esal")+"\t"+rs.getString("eaddr"));
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getString(4));
		}
		if(flag==false)
		{
			System.out.println("No Records found");
		}
		con.close();
	}
}
Application-13: How to Select Range of Records based on Salaries
=========================================

import java.sql.*;
import java.util.*;

public class SelectRangeOfRecordsDemo2
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Begin Salary Range:");
		double beginSal=sc.nextDouble();
		System.out.println("Enter End Salary Range:");
		double endSal=sc.nextDouble();
		String sqlQuery=String.format("select * from employees where esal>%f and esal<%f",beginSal,endSal);
		boolean flag=false;
		ResultSet rs =st.executeQuery(sqlQuery);
		System.out.println("ENO\tENAME\tESALARY\tEADDR");
		System.out.println("--------------------------------------");
		while(rs.next())
		{
			flag=true;
			//System.out.println(rs.getInt("eno")+"\t"+rs.getString("ename")+"\t"+rs.getDouble("esal")+"\t"+rs.getString("eaddr"));
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getString(4));
		}
		if(flag==false)
		{
			System.out.println("No Records found");
		}
		con.close();
	}
}

Application-14: How to Select Range of Records based on Initial Characters of the Employee Name
=========================================
import java.sql.*;
import java.util.*;
public class SelectRangeOfRecordsDemo3
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Initial Characters of Employee Name:");
		String initialChar=sc.next()+"%";
		String sqlQuery=String.format("select * from employees where ename like '%s'",initialChar);
		boolean flag=false;
		ResultSet rs =st.executeQuery(sqlQuery);
		System.out.println("ENO\tENAME\tESALARY\tEADDR");
		System.out.println("--------------------------------------");
		while(rs.next())
		{
			flag=true;
			//System.out.println(rs.getInt("eno")+"\t"+rs.getString("ename")+"\t"+rs.getDouble("esal")+"\t"+rs.getString("eaddr"));
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getString(4));
		}
		if(flag==false)
		{
			System.out.println("No Records found");
		}
		con.close();
	}
}

Application-15: To Display Number of rows by SQL Aggregate Function count(*)
=========================================
Note: SQL Aggregate Function: count(*) returns number of rows present in the table

import java.sql.*;
public class RowCountDemo
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		String sqlQuery="select count(*) from employees";
		ResultSet rs =st.executeQuery(sqlQuery);
		if(rs.next())
		{
			System.out.println(rs.getInt(1));
		}
		con.close();
	}
}
Application-16: How to Select Highest Salaried Employee information by using SQL Aggregate Function max
=========================================

import java.sql.*;
public class HighestSalaryEmpDemo
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		String sqlQuery="select  * from employees where esal in (select max(esal) from employees)";
		ResultSet rs =st.executeQuery(sqlQuery);
		if(rs.next())
		{
			System.out.println("Highest sal employee information");
			System.out.println("----------------------------------------");
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getString(4));
		}
		con.close();
	}
}

Note: To find Minimum salaried employee information,
String sqlQuery="select  * from employees where esal in (select min(esal) from employees)";

Application-17: How to Select Nth Highest Salaried Employee information
=======================================
import java.sql.*;
import java.util.*;
public class NthHighestSalaryEmpDemo
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Number:");
		int n = sc.nextInt();
		String sqlQuery="select  * from ( select eno,ename,esal,eaddr, rank() over (order by esal DESC) ranking from employees)  where ranking="+n;
		ResultSet rs =st.executeQuery(sqlQuery);
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getString(4));
		}
		con.close();
	}
}
Note: The rank function will assign a ranking to each row starting from 1.
Application-18: How to display retrieved data from the database through html
==========================================
import java.sql.*;
import java.io.*;
public class SelectAllRowsToHtmlDemo 
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		String sqlQuery="select * from employees";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		ResultSet rs =st.executeQuery(sqlQuery);
		String data="";
		data = data+"<html><body><center><table border='1' bgcolor='green'>";
		data=data+"<tr><td>ENO</td><td>ENAME</td><td>ESAL</td><td>EADDR</td></tr>";
		while(rs.next())
		{
			data=data+"<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getDouble(3)+"</td><td>"+rs.getString(4)+"</td></tr>";
		}
		data=data+"</table><center></body></html>";
		FileOutputStream fos = new FileOutputStream("emp.html");
		byte[] b = data.getBytes();
		fos.write(b);
		fos.flush();
		System.out.println("Open emp.html to get Employees data");
		fos.close();
		con.close();
	}
}
Application-19: How to execute select and non-select queries by using execute() method
==========================================
import java.sql.*;
import java.util.*;
public class SelectNonSelectDemo
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);// This step is optional
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Query: ");
		String sqlQuery=sc.nextLine();
		boolean b = st.execute(sqlQuery);
		if(b== true)//select query
		{
			ResultSet rs =st.getResultSet();
			while(rs.next())
		   {
			    System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getString(4));
		    }
		}
		else //non-select query
		{
			int rowCount=st.getUpdateCount();
			System.out.println("The number of records effected is:"+rowCount);
		}
		con.close();
	}
}

Application-20: execute methods LoopHoles-1:
===============================
 If we use executeUpdate() method for DDL Queries like create table,drop table etc..then record manipulation is not available on database. In this case return value we cannot expect and it is varied from driver to driver. For Type-1 Driver we will get -1 and for oracle Type-4 Driver we will get 0.

For Type-1 Driver:
============
import java.sql.*;
public class ExecuteMethodLoopHoles1T1
{
	public static void main(String[] args) throws Exception
	{
		String driver="sun.jdbc.odbc.JdbcOdbcDriver";
		String jdbc_url="jdbc:odbc:demodsn";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		int updateCount=st.executeUpdate("create table emp1(eno number)");
		System.out.println(updateCount);//-1
		con.close();
	}
}
For Type-4 Driver:
============
import java.sql.*;
public class ExecuteMethodLoopHoles1T4
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		int updateCount=st.executeUpdate("create table emp2(eno number)");
		System.out.println(updateCount);//0
		con.close();
	}
}
Application-21: execute methods LoopHoles-2:
===============================
If we pass non-select query as argument to executeQuery() method then result is varied from driver to driver. In the case of Type-1 Driver we will get SQLException : No ResultSet was produced.
In the case of Type-4 Driver provided by Oracle then we wont get any exception and empty ResultSet object will be created.If we are trying to access that ResultSet then we will get SQLException
For Type-1:
=======
import java.sql.*;
public class ExecuteMethodLoopHoles2T1
{
	public static void main(String[] args) throws Exception
	{
		String driver="sun.jdbc.odbc.JdbcOdbcDriver";
		String jdbc_url="jdbc:odbc:demodsn";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		ResultSet rs=st.executeQuery("update employees set esal=7777 where ename='durga'");
		con.close();
	}
}

For Type-4 Driver:
============
import java.sql.*;
public class ExecuteMethodLoopHoles2T4
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		ResultSet rs=st.executeQuery("update employees set esal=7777 where ename='durga'");
		con.close();
	}
}
Application-22: execute methods LoopHoles-3:
===============================
If we send select Query as argument to executeUpdate() method then result is varied from driver to driver. In the case of Type-1 Driver we will get SQLException: No row count was produced.

In the case of Type-4 Driver provided by Oracle then we wont get any exception and returns the number of records retrieved from the database.

For Type-1:
=======
import java.sql.*;
public class ExecuteMethodLoopHoles3T1
{
	public static void main(String[] args) throws Exception
	{
		String driver="sun.jdbc.odbc.JdbcOdbcDriver";
		String jdbc_url="jdbc:odbc:demodsn";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		int rowCount=st.executeUpdate("select * from employees");
		System.out.println(rowCount);
		con.close();
	}
}
For Type-4 Driver:
============
import java.sql.*;
public class ExecuteMethodLoopHoles3T4
{
	public static void main(String[] args) throws Exception
	{
		String driver="oracle.jdbc.OracleDriver";
		String jdbc_url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="scott";
		String pwd="tiger";
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbc_url,user,pwd);
		Statement st = con.createStatement();
		int rowCount=st.executeUpdate("select * from employees");
		System.out.println(rowCount);
		con.close();
	}
}

