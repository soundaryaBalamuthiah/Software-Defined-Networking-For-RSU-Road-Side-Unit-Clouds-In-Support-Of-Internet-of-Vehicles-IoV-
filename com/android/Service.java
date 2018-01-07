package com.android;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.database.DataBaseStatement;
import com.opensymphony.xwork2.ActionSupport;

public class Service extends ActionSupport implements ServletContextAware{
	
	PreparedStatement preparedStatement = null;

	public void service()
	{
		try
		{
			PrintWriter pw = ServletActionContext.getResponse().getWriter();
			DataBaseStatement ds=new DataBaseStatement();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/vanet","root","root");
			preparedStatement = con.prepareStatement("select DISTINCT city from latilongi");
        	ResultSet rs=preparedStatement.executeQuery();
        	pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			pw.println("<data>");
			while (rs.next())
			{
					String location=rs.getString("city").toString().trim();
					pw.println("<products>"); 
					pw.println("<name>");
					pw.println(location.trim());
					pw.println("</name>");
					pw.println("</products>");
			}
			con.close();
			pw.println("</data>");
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
