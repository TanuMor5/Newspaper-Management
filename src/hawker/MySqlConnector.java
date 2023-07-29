package hawker;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnector {
	static Connection con;

	public static Connection doConnect()
	{
	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/2023_jan","root","tanu1234$");
			System.out.println("Connected........");
		}
		catch(Exception exp)
		{
			System.out.println(exp);
		}
		return con;
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		doConnect();

	}
}
