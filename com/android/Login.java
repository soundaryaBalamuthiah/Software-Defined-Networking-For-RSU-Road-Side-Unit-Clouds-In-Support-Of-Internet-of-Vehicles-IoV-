package com.android;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import jutils21.FrameWork;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.database.DataBaseStatement;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport implements ServletContextAware{

	String user=null,license=null,uname,lic;
	PreparedStatement preparedStatement = null;
	
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
	public void login()
	{
		try
		{
			HttpServletRequest req=(HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			FrameWork fr=new FrameWork();
			if(fr.get(req))
			 {
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			DataBaseStatement ds=new DataBaseStatement();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/vanet","root","root");
			preparedStatement = con.prepareStatement("select username,Lcnumber from registration where username='"+user+"'");
        	ResultSet rs=preparedStatement.executeQuery();
        	if(rs.next())
        	{
        		uname=rs.getString("username");
        		lic=rs.getString("Lcnumber");
        	}
        	if(uname.equals(user) && lic.equals(license))
        	{
        		out.println("success");
        	}
        	else
        	{
        		System.out.println();
        		out.println("failure");
        	}
		}
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
