package persistance;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class GetConnection {

	private static Connection con=null;
	
	public static Connection getConnection()
	{
		if(con==null)
		{
		
		try {
				
				
			/*	Properties p=new Properties();
				
				
				/*Class c=GetConnection.class;
				
				InputStream  is=c.getResourceAsStream("/resources/dbinfo.properties");
				
				
				p.load(is);
				
				*/
				
			/*	p.load(GetConnection.class.getResourceAsStream("hibernate.cfg.xml"));
				
				System.out.println(p.getProperty("driver"));
				System.out.println(p.getProperty("url"));
				System.out.println(p.getProperty("username"));
				System.out.println(p.getProperty("password"));
				
				Class.forName(p.getProperty("driver"));
				
				con=DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));
				*/
				System.out.println(con==null?"in if no connection":"in if connnection success");
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
		System.out.println(con==null?"no connection":"connnection success");
		return con;
	}
}

