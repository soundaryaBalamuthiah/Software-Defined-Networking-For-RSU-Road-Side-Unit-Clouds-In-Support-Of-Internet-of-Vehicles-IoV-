package com.android;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.database.DataBaseStatement;
import com.opensymphony.xwork2.ActionSupport;

public class Calculation extends ActionSupport implements ServletContextAware{

	String location,selected;
	Vector seploc=new Vector();
	Vector allcount=new Vector();
	PreparedStatement preparedStatement = null;
	ResultSet rs=null;
	Statement st=null;
	int hotcount=0;
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}
	
	public void request()
	{
		try
		{
			PrintWriter pw = ServletActionContext.getResponse().getWriter();
			DataBaseStatement ds=new DataBaseStatement();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/vanet","root","root");
			st=con.createStatement();
			StringTokenizer str=new StringTokenizer(location,"->");
			while(str.hasMoreTokens())
			{
				seploc.add(str.nextToken());
			}
			if(selected.equals("Restraunts"))
			{
				System.out.println("----inside restraunts----");
				for(int i=0;i<seploc.size();i++)
				{
					String s=seploc.get(i).toString();
					rs=st.executeQuery("select hotels from latilongi where city='"+s+"'");
					while(rs.next())
					{
						String h=rs.getString("hotels");
						hotcount=hotcount+Integer.parseInt(h);
						
					}
				}
				System.out.println("----restraunts hot count----"+hotcount);
				allcount.add(hotcount);
			}
			else if(selected.equals("PetrolBunks"))
			{
				System.out.println("----inside petrol bunks----");
				for(int i=0;i<seploc.size();i++)
				{
				String s=seploc.get(i).toString();
				rs=st.executeQuery("select petrolbunk from latilongi where city='"+s+"'");
				while(rs.next())
				{
					String p=rs.getString("petrolbunk");
					hotcount=hotcount+Integer.parseInt(p);
					
				}
				}
				System.out.println("----petrol hot count----"+hotcount);
				allcount.add(hotcount);
			}
			else if(selected.equals("Hospitals"))
			{
				System.out.println("----inside hospitals----");
				for(int i=0;i<seploc.size();i++)
				{
				String s=seploc.get(i).toString();
				rs=st.executeQuery("select hospitals from latilongi where city='"+s+"'");
				while(rs.next())
				{
					String hos=rs.getString("hospitals");
					hotcount=hotcount+Integer.parseInt(hos);
					
				}
				}
				System.out.println("----hospitals hot count----"+hotcount);
				allcount.add(hotcount);
			}
			else if(selected.equals("DrunkersTravelled"))
			{
				System.out.println("----inside drunkers----");
				for(int i=0;i<seploc.size();i++)
				{
				String s=seploc.get(i).toString();
				rs=st.executeQuery("select drunk from clouddb where place='"+s+"'");
				while(rs.next())
				{
					String hos=rs.getString("drunk");
					if(hos.equals("drunken"))
					{
						hotcount=hotcount+1;
					}
				}
				
				}
				System.out.println("----drunkers hot count----"+hotcount);
				allcount.add(hotcount);
			}
			else if(selected.equals("NonDrunkers"))
			{
				System.out.println("----inside nondrunkers---");
				for(int i=0;i<seploc.size();i++)
				{
				String s=seploc.get(i).toString();
				rs=st.executeQuery("select drunk from clouddb where place='"+s+"'");
				while(rs.next())
				{
					String hos=rs.getString("drunk");
					if(hos.equals("not drunken"))
					{
						hotcount=hotcount+1;
					}
				}
				
				}
				System.out.println("----non drunkers hot count----"+hotcount);
				allcount.add(hotcount);
			}
			System.out.println("----output---"+allcount.toString().replace("[","").replace("]",""));
			pw.println("success$"+allcount.toString().replace("[","").replace("]",""));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
}
