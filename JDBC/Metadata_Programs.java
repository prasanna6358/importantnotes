App1: Program to display DataBase meta information by using DataBaseMetaData
======================================================================
import java.sql.*;
class DatabaseMetaDataDemo1
{
	public static void main(String[] args) throws Exception
	{
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","durga");
		DatabaseMetaData dbmd=con.getMetaData();
		System.out.println("Database Product Name:"+dbmd.getDatabaseProductName());
		System.out.println("DatabaseProductVersion:"+dbmd.getDatabaseProductVersion());
		System.out.println("DatabaseMajorVersion:"+dbmd.getDatabaseMajorVersion());
    	System.out.println("DatabaseMinorVersion:"+dbmd.getDatabaseMinorVersion());
		System.out.println("JDBCMajorVersion:"+dbmd.getJDBCMajorVersion());
		System.out.println("JDBCMinorVersion:"+dbmd.getJDBCMinorVersion());
		System.out.println("DriverName:"+dbmd.getDriverName());
		System.out.println("DriverVersion:"+dbmd.getDriverVersion());
		System.out.println("URL:"+dbmd.getURL());
		System.out.println("UserName:"+dbmd.getUserName());
		System.out.println("MaxColumnsInTable:"+dbmd.getMaxColumnsInTable());
		System.out.println("MaxRowSize:"+dbmd.getMaxRowSize());
		System.out.println("MaxStatementLength:"+dbmd.getMaxStatementLength());
		System.out.println("MaxTablesInSelect"+dbmd.getMaxTablesInSelect());
		System.out.println("MaxTableNameLength:"+dbmd.getMaxTableNameLength());
		System.out.println("SQLKeywords:"+dbmd.getSQLKeywords());
		System.out.println("NumericFunctions:"+dbmd.getNumericFunctions());
		System.out.println("StringFunctions:"+dbmd.getStringFunctions());
		System.out.println("SystemFunctions:"+dbmd.getSystemFunctions());
    	System.out.println("supportsFullOuterJoins:"+dbmd.supportsFullOuterJoins());
		System.out.println("supportsStoredProcedures:"+dbmd.supportsStoredProcedures());
		con.close();
	}
}
App2: Program to display Table Names present in DataBase by using DataBaseMetaData
======================================================================
import java.sql.*;
import java.util.*;
class DatabaseMetaDataDemo2
{
	public static void main(String[] args) throws Exception
	{
		int count=0;
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","durga");
		DatabaseMetaData dbmd=con.getMetaData();
		String catalog=null;
		String schemaPattern=null;
		String tableNamePattern=null;
		String[] types=null;
		ResultSet rs = dbmd.getTables(catalog,schemaPattern,tableNamePattern,types);
		//the parameters can help limit the number of tables that are returned in the ResultSet
		//the ResultSet contains 10 columns and 3rd column represent table names.
		while(rs.next())
		{
			count++;
			System.out.println(rs.getString(3));
		}
		System.out.println("The number of tables:"+count);
		con.close();
	}
}
App3: Program to display Columns meta information by using ResultMetaData
======================================================================
import java.sql.*;
class ResultSetMetaDataDemo
{
	public static void main(String[] args) throws Exception
	{
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","durga");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from employees");
		ResultSetMetaData rsmd=rs.getMetaData();
		int count=rsmd.getColumnCount();
		for(int i=1;i<= count;i++)
		{
			System.out.println("Column Number:"+i);
			System.out.println("Column Name:"+rsmd.getColumnName(i));
			System.out.println("Column Type:"+rsmd.getColumnType(i));
			System.out.println("Column Size:"+rsmd.getColumnDisplaySize(i));
			System.out.println("---------------");
		}
		con.close();
	}
}
Important Methods of ParameterMetaData:
=======================================
1. int	getParameterCount()
    Retrieves the number of parameters in the PreparedStatement object for which this ParameterMetaData object contains information.
2.int	getParameterMode(int param)
       Retrieves the designated parameter's mode.
3. int	getParameterType(int param)
       Retrieves the designated parameter's SQL type.
4. String	getParameterTypeName(int param)
      Retrieves the designated parameter's database-specific type name.
5. int	getPrecision(int param)
    Retrieves the designated parameter's specified column size.
6. int	getScale(int param)
      Retrieves the designated parameter's number of digits to right of the decimal point.
7. int	isNullable(int param)
    Retrieves whether null values are allowed in the designated parameter.
8. boolean	isSigned(int param)
    Retrieves whether values for the designated parameter can be signed numbers.

App14: Program to display Parameter meta information by using ParameterMetaData
======================================================================
import java.sql.*;
class ParameterMetaDataDemo
{
	public static void main(String[] args) throws Exception
	{
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","durga");
		PreparedStatement pst = con.prepareStatement("insert into employees values(?,?,?,?)");
		ParameterMetaData pmd=pst.getParameterMetaData();
		int count=pmd.getParameterCount();
		for(int i=1;i<= count;i++)
		{
			System.out.println("Parameter Number:"+i);
			System.out.println("Parameter Mode:"+pmd.getParameterMode(i));
			System.out.println("Parameter Type:"+pmd.getParameterType(i));
			System.out.println("Parameter Precision:"+pmd.getPrecision(i));
			System.out.println("Parameter Scale:"+pmd.getScale(i));
			System.out.println("Parameter isSigned:"+pmd.isSigned(i));
			System.out.println("Parameter isNullable:"+pmd.isNullable(i));
			System.out.println("---------------");
		}
		con.close();
	}
}
