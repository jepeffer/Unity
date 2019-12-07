package Unity;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

/**
 * This class handles all direct to server connection and communcation.
 * 
 * @author American Amusements
 *
 */
public class Server
{
	private ArrayList<String>	Users	= new ArrayList<String>();
	private ArrayList<String>	POSUser	= new ArrayList<String>();
	
	/**
	 * This method is for calls in SQL directly to the local db.
	 * 
	 * @param cmd
	 */
	public void createRequest(String cmd)
	{
		String connectionUrl = "jdbc:sqlserver://127.0.0.1:9001;";
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try (Connection con = DriverManager.getConnection(connectionUrl, "unitypos", "unity812");
				Statement stmt = con.createStatement();)
		{
			String SQL = cmd;
			stmt.execute(SQL);
			con.close();
			stmt.close();
		}
		
		// Handle any errors that may have occurred.
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Specific call used in FrmUser to get the users.
	 * 
	 * @return
	 */
	public ArrayList<String> loadUsers()
	{
		Users.clear(); // New one everytime
		String connectionUrl = "jdbc:sqlserver://127.0.0.1:9001;";
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try (Connection con = DriverManager.getConnection(connectionUrl, "unitypos", "unity812");
				Statement stmt = con.createStatement();)
		{
			String SQL = "SELECT * FROM Users";
			ResultSet rs = stmt.executeQuery(SQL);
			
			// Iterate through the data in the result set and display it.
			while (rs.next())
			{
				Users.add(rs.getString("Name"));
			}
			con.close();
			rs.close();
		}
		// Handle any errors that may have occurred.
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return Users;
	}
	
	/**
	 * Method used for prepared statements
	 * 
	 * @param cmd       - Command to run
	 * @param arrayList - Parameters
	 * @param ID        - Which call is it
	 * @return String with data if needed
	 * @throws SQLException
	 */
	public String UVIS(String cmd, ArrayList<String> arrayList) throws SQLException
	{
		String connectionUrl = "jdbc:sqlserver://127.0.0.1:9001;";
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1)
		{
			e1.printStackTrace();
		}
		CallableStatement pstmt = null;
		try (Connection con = DriverManager.getConnection(connectionUrl, "unitypos", "unity812");
				Statement stmt = con.createStatement();)
		{
			pstmt = con.prepareCall(cmd);
			
			for (int i = 0; i < arrayList.size(); i++)
			{
				pstmt.setString(i + 1, arrayList.get(i));
			}
			
			pstmt.registerOutParameter(6, Types.VARCHAR);
			pstmt.registerOutParameter(7, Types.VARCHAR);
			pstmt.executeUpdate();
			
			String RedeemVendor = pstmt.getString(6);
			String RedeemMach = pstmt.getString(7);
			con.close();
			stmt.close();
			// System.out.println(RedeemVendor + RedeemMach);
			return String.format("%s|%s", RedeemVendor, RedeemMach);
		}
	}
	
	public void deleteUserRequest(String cmd, ArrayList<String> arrayList) throws SQLException
	{
		
		String connectionUrl = "jdbc:sqlserver://127.0.0.1:9001;sslProtocl=TLSv1;";
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1)
		{
			e1.printStackTrace();
		}
		CallableStatement pstmt = null;
		try (Connection con = DriverManager.getConnection(connectionUrl, "unitypos", "unity812");
				Statement stmt = con.createStatement();)
		{
			pstmt = con.prepareCall(cmd);
			for (int i = 0; i < arrayList.size(); i++)
			{
				pstmt.setString(i + 1, arrayList.get(i));
			}
			ResultSet rs = pstmt.executeQuery();
			con.close();
			stmt.close();
			rs.close();
		}
	}
	
	public String UnityPOSUsersByNameRequest(String cmd, ArrayList<String> arrayList) throws SQLException
	{
		String connectionUrl = "jdbc:sqlserver://127.0.0.1:9001;";
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1)
		{
			e1.printStackTrace();
		}
		CallableStatement pstmt = null;
		try (Connection con = DriverManager.getConnection(connectionUrl, "unitypos", "unity812");
				Statement stmt = con.createStatement();)
		{
			pstmt = con.prepareCall(cmd);
			for (int i = 0; i < arrayList.size(); i++)
			{
				pstmt.setString(i + 1, arrayList.get(i));
			}
			ResultSet rs = pstmt.executeQuery();
			
			String temp = "";
			while (rs.next())
			{
				temp = temp + rs.getString(3) + "|"; // Pipe as a delimeter
				temp = temp + rs.getString(2) + "|";
				temp = temp + rs.getString(1) + "|";
				temp = temp + rs.getString(4);
			}
			con.close();
			stmt.close();
			rs.close();
			return temp;
		}
		
	}
	
	public String UnityPOSUserLoginRequest(String cmd, ArrayList<String> arrayList) throws SQLException
	{
		String connectionUrl = "jdbc:sqlserver://127.0.0.1:9001;";
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1)
		{
			e1.printStackTrace();
		}
		CallableStatement pstmt = null;
		try (Connection con = DriverManager.getConnection(connectionUrl, "unitypos", "unity812");
				Statement stmt = con.createStatement();)
		{
			pstmt = con.prepareCall(cmd);
			for (int i = 0; i < arrayList.size(); i++)
			{
				pstmt.setString(i + 1, arrayList.get(i));
			}
			ResultSet rs = pstmt.executeQuery();
			
			String temp = "";
			while (rs.next())
			{
			}
			con.close();
			stmt.close();
			rs.close();
			// System.out.println("Name should be: " + rs.getNString(3));
			return temp;
		}
		
	}
	
	/**
	 * Returns a string of booleans.
	 * 
	 * @param cmd
	 * @param currentRequest
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<String> UnityPOSUsers(String cmd, ArrayList<String> currentRequest) throws SQLException
	{
		
		String connectionUrl = "jdbc:sqlserver://127.0.0.1:9001;";
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try (Connection con = DriverManager.getConnection(connectionUrl, "unitypos", "unity812");
				Statement stmt = con.createStatement();)
		{
			ResultSet rs = stmt.executeQuery(cmd);
			
			// Iterate through the data in the result set and display it.
			while (rs.next())
			{
				POSUser.add(rs.getString("UserID"));
				POSUser.add(rs.getString("Name"));
			}
			con.close();
			rs.close();
		}
		// Handle any errors that may have occurred.
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return POSUser;
		
	}
	
	public String UnityPOSLocation()
	{
		String connectionUrl = "jdbc:sqlserver://127.0.0.1:9001;";
		String serverLocation = "";
		String cmd = "SELECT \"Name\", \"Value\" FROM \"LocationInfo\" where \"LocationInfo\".\"Name\" = \'Name\'";
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try (Connection con = DriverManager.getConnection(connectionUrl, "unitypos", "unity812");
				Statement stmt = con.createStatement();)
		{
			ResultSet rs = stmt.executeQuery(cmd);
			
			// Iterate through the data in the result set and display it.
			while (rs.next())
			{
				serverLocation = rs.getString("Value");
			}
			con.close();
			rs.close();
		}
		// Handle any errors that may have occurred.
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return serverLocation;
		
	}
	
	public void UnityPOSUserLogoutRequest(String cmd, ArrayList<String> arrayList) throws SQLException
	{
		String connectionUrl = "jdbc:sqlserver://127.0.0.1:9001;";
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1)
		{
			e1.printStackTrace();
		}
		CallableStatement pstmt = null;
		try (Connection con = DriverManager.getConnection(connectionUrl, "unitypos", "unity812");
				Statement stmt = con.createStatement();)
		{
			pstmt = con.prepareCall(cmd);
			for (int i = 0; i < arrayList.size(); i++)
			{
				pstmt.setString(i + 1, arrayList.get(i));
			}
			ResultSet rs = pstmt.executeQuery();
			
			con.close();
			stmt.close();
			rs.close();
			// System.out.println("Name should be: " + rs.getNString(3));
		}
		
	}
}
