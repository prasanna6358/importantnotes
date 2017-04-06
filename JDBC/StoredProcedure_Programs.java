Stored Procedures App1:JDBC Program to call StoredProcedure which can take two input numbers and produces the result.

/*
stored procedure:
=================
create or replace procedure addProc(num1 IN number,num2 IN number,num3 OUT number) as
BEGIN
   num3 :=num1+num2;
END;
/
*/
import java.sql.*;
class StoredProceduresDemo1 
{
	public static void main(String[] args) throws Exception
	{
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","durga");
		CallableStatement cst=con.prepareCall("{call addProc(?,?,?)}");
		cst.setInt(1,100);
		cst.setInt(2,200);
		cst.registerOutParameter(3,Types.INTEGER);
		cst.execute();
		System.out.println("Result.."+cst.getInt(3));
		con.close();
	}
}
Stored Procedures App2:JDBC Program to call StoredProcedure which can take employee number as input and provides corresponding salary.
/*
stored procedure:
=================
create or replace procedure getSal(id IN number,sal OUT number) as
BEGIN
   select esal into sal from employees where eno=id;
END;
/
*/
import java.sql.*;
class StoredProceduresDemo2 
{
	public static void main(String[] args) throws Exception
	{
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","durga");
		CallableStatement cst=con.prepareCall("{call getSal(?,?)}");
		cst.setInt(1,100);
		cst.registerOutParameter(2,Types.FLOAT);
		cst.execute();
		System.out.println("Salary ..."+cst.getFloat(2));
		con.close();
	}
}
Stored Procedures App3:JDBC Program to call StoredProcedure which can take employee number as input and provides corresponding name and salary.
/*
/*
stored procedure:
=================
create or replace procedure getEmpInfo(id IN number,name OUT varchar2,sal OUT number) as
BEGIN
   select ename,esal into name,sal from employees where eno=id;
END;
/
*/
import java.sql.*;
class StoredProceduresDemo3
{
	public static void main(String[] args) throws Exception
	{
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","durga");
		CallableStatement cst=con.prepareCall("{call getEmpInfo(?,?,?)}");
		cst.setInt(1,100);
		cst.registerOutParameter(2,Types.VARCHAR);
		cst.registerOutParameter(3,Types.FLOAT);
		cst.execute();
		System.out.println("Employee Name is :"+cst.getString(2));
		System.out.println("Employee Salary is :"+cst.getFloat(3));
		con.close();
	}
}
Stored Procedures App4:JDBC Program to call StoredProcedure which returns all Employees info by using SYS_REFCURSOR
==================================================================
/*
stored procedure:
=================
create or replace procedure getAllEmpInfo1(sal IN number,emps OUT SYS_REFCURSOR) as
BEGIN
   open emps for 
   select * from employees where esal<sal;
END;
/
*/
import java.sql.*;
import oracle.jdbc.*;// for OracleTyes.CURSOR and it is present in                              ojdbc6.jar
class StoredProceduresDemo4
{
	public static void main(String[] args) throws Exception
	{
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","durga");
		CallableStatement cst=con.prepareCall("{call getAllEmpInfo1(?,?)}");
		cst.setFloat(1,6000);
		cst.registerOutParameter(2,OracleTypes.CURSOR);
		cst.execute();
		ResultSet rs = (ResultSet)cst.getObject(2);
		boolean flag=false;
		System.out.println("ENO\tENAME\tESAL\tEADDR");
		System.out.println("-----------------------");
		while(rs.next())
		{
			flag=true;
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getFloat(3)+"\t"+rs.getString(4));
		}
		if(flag== false)
		{
			System.out.println("No Recors Available");
		}
		con.close();
	}
}
Stored Procedures App5:JDBC Program to call StoredProcedure which returns all Employees info by using SYS_REFCURSOR based initial characters of the name
==================================================================
/*
stored procedure:
=================
create or replace procedure getAllEmpInfo2(initchars IN varchar,emps OUT SYS_REFCURSOR) as
BEGIN
   open emps for 
   select * from employees where ename like initchars;
END;
/
*/
import java.sql.*;
import java.util.*;
import oracle.jdbc.*;// for OracleTyes.CURSOR and it is present in                              ojdbc6.jar
class StoredProceduresDemo5
{
	public static void main(String[] args) throws Exception
	{
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","durga");
		CallableStatement cst=con.prepareCall("{call getAllEmpInfo2(?,?)}");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter initial characters of the name");
		String initialchars=sc.next()+"%";
		cst.setString(1,initialchars);
		cst.registerOutParameter(2,OracleTypes.CURSOR);
		cst.execute();
		ResultSet rs = (ResultSet)cst.getObject(2);
		boolean flag= false;
		System.out.println("ENO\tENAME\tESAL\tEADDR");
		System.out.println("-----------------------");
		while(rs.next())
		{
			flag=true;
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getFloat(3)+"\t"+rs.getString(4));
		}
		if(flag== false)
		{
			System.out.println("No Recors Available");
		}
		con.close();
	}
}
Stored Procedures App6:JDBC Program to call Function which returns average salary of given two employees
==================================================================
/* create or replace function getAvg(id1 IN number,id2 IN number)return number
as
sal1 number;
sal2 number;
BEGIN
  select esal into sal1 from employees where eno=id1;
  select esal into sal2 from employees where eno=id2;
  return (sal1+sal2)/2;
END;
/
*/

import java.sql.*;
class StoredProceduresDemo6 
{
	public static void main(String[] args) throws Exception
	{
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","durga");
		CallableStatement cst=con.prepareCall("{?=call getAvg(?,?)}");
		cst.setInt(2,100);
		cst.setInt(3,200);
		cst.registerOutParameter(1,Types.FLOAT);
		cst.execute();
		System.out.println("Salary ..."+cst.getFloat(1));
		con.close();
	}
}
Stored Procedures App7:JDBC Program to call function returns all employees information based on employee numbers
==================================================================
/*
stored procedure:
=================
create or replace function getAllEmpInfo4(no1 IN number,no2 IN number) return SYS_REFCURSOR as
emps SYS_REFCURSOR;
BEGIN
   open emps for 
   select * from employees where eno>=no1 and eno<=no2;
   return emps;
END;
/
*/
import java.sql.*;
import oracle.jdbc.*;// for OracleTyes.CURSOR and it is present in                              ojdbc6.jar
class StoredProceduresDemo7
{
	public static void main(String[] args) throws Exception
	{
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","durga");
		CallableStatement cst=con.prepareCall("{?=call getAllEmpInfo4(?,?)}");
		cst.setInt(2,1000);
		cst.setInt(3,2000);
		cst.registerOutParameter(1,OracleTypes.CURSOR);
		cst.execute();
		ResultSet rs = (ResultSet)cst.getObject(1);
		boolean flag=false;
		System.out.println("ENO\tENAME\tESAL\tEADDR");
		System.out.println("-----------------------");
		while(rs.next())
		{
			flag=true;
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getFloat(3)+"\t"+rs.getString(4));
		}
		if(flag== false)
		{
			System.out.println("No Recors Available");
		}
		con.close();
	}
}
Stored Procedures App8:JDBC Program to call function to demonistrate SQL%ROWCOUNT implicit cursor
==================================================================
/*
stored procedure:
=================
create or replace function getDeletedEMPInfo(no1 IN number,count OUT number) return SYS_REFCURSOR as
emps SYS_REFCURSOR;
BEGIN
 open emps for
      select * from employees where eno=no1;
      delete from employees where eno=no1;
	  count :=SQL%ROWCOUNT;
   return emps;
END;
/
*/
import java.sql.*;
import oracle.jdbc.*;// for OracleTyes.CURSOR and it is present in                              ojdbc6.jar
class StoredProceduresDemo8
{
	public static void main(String[] args) throws Exception
	{
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","durga");
		CallableStatement cst=con.prepareCall("{?=call getDeletedEMPInfo(?,?)}");
		cst.setInt(2,100);
		cst.registerOutParameter(1,OracleTypes.CURSOR);
		cst.registerOutParameter(3,Types.INTEGER);
		cst.execute();
		ResultSet rs = (ResultSet)cst.getObject(1);
		System.out.println("ENO\tENAME\tESAL\tEADDR");
		System.out.println("-----------------------");
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getFloat(3)+"\t"+rs.getString(4));
		}
		int count=cst.getInt(3);
		System.out.println("The number of rows deleted: "+count);
		con.close();
	}
}

