package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import forms.Product;
import forms.RegistrationForm;

public class DAO {

	Connection con=GetConnection.getConnection();
	
	Configuration configuration=null;
	SessionFactory sessionFactory=null;
	Transaction tx=null;
	Session session=null;
	
	public DAO() {
		
		configuration=new Configuration().configure();
		sessionFactory=configuration.buildSessionFactory();
		
	}
	public boolean isValidUser(String username,String password)
	{
		boolean isValidUser=false;
		
		try {
			
		
			PreparedStatement ps=con.prepareStatement("select count(*) from login where username=? and password=?");
			
			ps.setString(1,username);
			ps.setString(2,password);
			
			int count=0;
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				count=rs.getInt(1);
			}
			
			isValidUser=count==1?true:false;
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return isValidUser;
	}
	//====================================================
	public boolean isRegistredUser(String username)
	{
		boolean isRegistredUser=false;
		
		try {
			
			PreparedStatement ps=con.prepareStatement("select count(*) from login where username=?");
			
			ps.setString(1,username);
			
			int count=0;
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				count=rs.getInt(1);
			}
			
			isRegistredUser=count==1?true:false;
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return isRegistredUser;
	}
	//==================================================================
	public boolean reigsterUser(RegistrationForm registrationForm,String password)
	{
		boolean isUserRegistred=false;
		
		try {
			
			String role="user";
			
			con.setAutoCommit(false);
			
			Statement st=con.createStatement();
			
			st.addBatch("insert into users values('"+registrationForm.getUsername()+"','"+registrationForm.getEmail()+"','"+registrationForm.getMobile()+"','"+registrationForm.getAddress()+"')");
			st.addBatch("insert into login values('"+registrationForm.getUsername()+"','"+password+"','"+role+"')");
			
			int[] results=st.executeBatch();
			
			for(int i: results)
			{
				if(i==0)
				{
					isUserRegistred=false;
				}
			}
			
			if(!isUserRegistred)
			{
				con.commit();
			}
			else
			{
				con.rollback();
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isUserRegistred;
	}
	//===================================================
	public boolean addProduct(Product product)
	{
		boolean isProductAdded=false;
		
		try {
			
			PreparedStatement ps=con.prepareStatement("insert into products values(?,?,?,?,?,?)");
			
			ps.setInt(1,product.getId());
			ps.setString(2,product.getName());
			ps.setFloat(3,product.getPrice());
			ps.setString(4,product.getMname());
			ps.setString(5,product.getCategory());
			ps.setFloat(6,product.getDiscount());
			
			isProductAdded=ps.executeUpdate()==1?true:false;
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return isProductAdded;
	}
}

