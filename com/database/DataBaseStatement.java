package com.database;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.StringTokenizer;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DataBaseStatement extends ActionSupport implements Runnable {
	String sql = "";
	Connection conn = null;

	Map<String, Object> session=ActionContext.getContext().getSession();
	public String insertUser() {
		sql = "insert into registration values(?,?,?,?,?,?)";
		return sql;
	}
	
	public String updatecloud() {
		sql = "insert into clouddb values(?,?,?,?,?,?,?,?,?)";
		return sql;
	}
	public String pair()
	{
		sql = "insert into pairing values(?,?,?)";
		return sql;
	}
	public String select() {
		sql = "select * from clouddb";
		return sql;
	}
	public void context()
	{
		(new Thread(new DataBaseStatement())).start();
 	}
	public void run()
	{
		try
		{
			InetAddress ia = InetAddress.getByName("225.0.8.3");
            MulticastSocket ms = new MulticastSocket(4003);
            ms.joinGroup(ia);
            PreparedStatement preparedStatement = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn=DriverManager.getConnection("jdbc:mysql://localhost/vanet","root","root");
            System.out.println("-----------------**************connection created*************---------------");
            preparedStatement = conn.prepareStatement(pair());
			preparedStatement.setString(1, "undefined");
			preparedStatement.setString(2, "undefined");
			preparedStatement.setString(3, "undefined");
			preparedStatement.execute();
            while (true)
            {
                byte[] b = new byte[5120];
                DatagramPacket ds = new DatagramPacket(b, b.length);
                ms.receive(ds);
                String data = new String(ds.getData()).trim();
                StringTokenizer st = new StringTokenizer(data, ",");
                String checkString = st.nextToken();
                if (checkString.equalsIgnoreCase("update"))
                {
                	System.out.println("-----------------**************data recived*************---------------"+data);
                	preparedStatement = conn.prepareStatement(updatecloud());
    				preparedStatement.setString(1, st.nextToken());
    				preparedStatement.setString(2, st.nextToken());
    				preparedStatement.setString(3, st.nextToken());
    				preparedStatement.setString(4, st.nextToken() );
    				preparedStatement.setString(5, st.nextToken());
    				preparedStatement.setString(6, st.nextToken());
    				preparedStatement.setString(7, st.nextToken());
    				preparedStatement.setString(8, st.nextToken() );
    				preparedStatement.setString(9, st.nextToken() );
    				preparedStatement.execute();
    				System.out.println("-----------------------************completed**************--------------");
                }
                if (checkString.equalsIgnoreCase("pair"))
                {
                	String username=st.nextToken();
                	String lcn=st.nextToken();
                	String path=st.nextToken();
                	preparedStatement = conn.prepareStatement("TRUNCATE TABLE pairing");
                	preparedStatement.execute();
                	
                	preparedStatement = conn.prepareStatement(pair());
    				preparedStatement.setString(1, username);
    				preparedStatement.setString(2, lcn);
    				preparedStatement.setString(3, path);
    				preparedStatement.execute();
                }
            }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
