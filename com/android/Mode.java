package com.android;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.database.DataBaseStatement;
import com.opensymphony.xwork2.ActionSupport;

public class Mode extends ActionSupport implements ServletContextAware{

	String user=null,license=null,uname,lic;
	PreparedStatement preparedStatement = null;
	Statement st;
	ResultSet rs;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	
	public void retrieve()
	{
		try
		{
			System.out.println("--inside mode--");
			PrintWriter pw = ServletActionContext.getResponse().getWriter();
			DataBaseStatement ds=new DataBaseStatement();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/vanet","root","root");
			st=con.createStatement();
			rs=st.executeQuery("select path from pairing where username='"+user+"' and lcn='"+license+"'");
			if(rs.next())
			{
				String pth=rs.getString("path");
				pw.println("success$"+pth);
			}
			else
			{
				pw.println("failure");
			}
			System.out.println("---manual mode respond---");
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
