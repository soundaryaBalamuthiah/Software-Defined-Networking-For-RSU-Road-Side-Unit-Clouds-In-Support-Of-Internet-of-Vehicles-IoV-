package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import org.apache.struts2.ServletActionContext;
import javax.xml.ws.Response;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.jasper.tagplugins.jstl.core.Out;

import com.database.DataBaseStatement;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class andriodpairin extends ActionSupport
{
	
	PreparedStatement preparedStatement = null;
	String un;
	public String execute()
	{
		try
		{
			DataBaseStatement ds=new DataBaseStatement();
			PreparedStatement preparedStatement = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/vanet","root","root");
			preparedStatement = con.prepareStatement("select * from pairing");
        	ResultSet rs=preparedStatement.executeQuery();
        	if(rs.next())
        	{
        		un="success$"+rs.getString(1).toString()+"*"+rs.getString(2).toString()+"*"+rs.getString(3).toString();
        	}
        	else
        	{
        		un="failure$";
        	}
			HttpServletResponse response = ServletActionContext.getResponse();
			  PrintWriter pw = response.getWriter();
			  pw.write(un);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return null;
	}
	
	
}
