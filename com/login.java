package com;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;

import com.database.DataBaseStatement;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.*;
import javax.comm.*;

import java.sql.*;

import jutils21.FrameWork;


public class login extends ActionSupport implements SerialPortEventListener, Runnable
{
	 static CommPortIdentifier portId;
	    static Enumeration portList;
	    InputStream inputStream;
	    SerialPort serialPort;
	    Thread readThread;
	    String value = "";
	    String a = "";
	String username=null,password=null,s;
	DataBaseStatement ds=new DataBaseStatement();
	ServletContext sc=ServletActionContext.getServletContext();
	Statement st=(Statement) sc.getAttribute("statement");
	Connection con=(Connection) sc.getAttribute("connection");
	PreparedStatement preparedStatement = null;
	Map<String, Object> session=ActionContext.getContext().getSession();
	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String execute()
	{
			try 
			{
				HttpServletRequest req=(HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
				FrameWork fr=new FrameWork();
				if(fr.get(req))
				 {
			    if(username.equals("admin") && password.equals("admin"))
			    {
			    	ds.context();
			    	s=SUCCESS;
					session.put("user", getUsername());
					session.put("login","true");
					ActionContext.getContext().getSession().get("user");
					ActionContext.getContext().getSession().get("login");
					
					portList = CommPortIdentifier.getPortIdentifiers();
			        System.out.println("Receiver Start..." + portList.hasMoreElements());
			        while (portList.hasMoreElements()) {
			            //System.out.println("While 1");
			            portId = (CommPortIdentifier) portList.nextElement();
			            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
			                //if (portId.getName().equals("COM1")) {
			                    //                if (portId.getName().equals("/dev/term/a")) {
			                    //System.out.println("COM!   7777");
			                    //Tagreader reader = new Tagreader();
			                    try {
			                        serialPort = (SerialPort) portId.open("SimpleReadApp", 9600);
			                    } catch (PortInUseException e) {
			                        System.out.println(e);
			                    }
			                    try {
			                        inputStream = serialPort.getInputStream();
			                    } catch (IOException e) {
			                        System.out.println(e);
			                    }
			                    try {
			                        serialPort.addEventListener(this);
			                    } catch (TooManyListenersException e) {
			                        System.out.println(e);
			                    }
			                    serialPort.notifyOnDataAvailable(true);
			                    try {
			                        serialPort.setSerialPortParams(9600,
			                                SerialPort.DATABITS_8,
			                                SerialPort.STOPBITS_1,
			                                SerialPort.PARITY_NONE);
			                    } catch (UnsupportedCommOperationException e) {
			                        System.out.println(e);
			                    }
			                    readThread = new Thread(this);
			                    readThread.start();
			                //}
			            }
			        }
					
//					System.out.println("----------------"+session);
			    }
			    else
			    {
			    	addActionError("Enter correct username and password");
			    	s=ERROR;
			    }
			}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}	
		return s;
	}
	
	public String Signout()
	{
		((SessionMap) session).invalidate();
		addActionMessage("You successfully signed out");
		session.clear();
		return "SUCCESS";
	}

	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
        case SerialPortEvent.BI:
        case SerialPortEvent.OE:
        case SerialPortEvent.FE:
        case SerialPortEvent.PE:
        case SerialPortEvent.CD:
        case SerialPortEvent.CTS:
        case SerialPortEvent.DSR:
        case SerialPortEvent.RI:
        case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
            break;
        case SerialPortEvent.DATA_AVAILABLE:
            byte[] readBuffer = new byte[20];
            try {
                while (inputStream.available() > 0) {
                    int numBytes = inputStream.read(readBuffer);
                }
                String data = new String(readBuffer).trim();
                a = a + data;
                //System.out.println("Data length..is."+data);
                //System.out.println("Data length..is."+data.length());
                if (data.length() <= 20) {
                    //db.info(a);
                    System.out.println(" Tag ID is......" + a);
                    BufferedWriter output = null;
    				File f1 = new File("webapps/RSUCloud/a.txt");
    				if (!f1.exists()) {
    					f1.createNewFile();
    					
    				}
    				output = new BufferedWriter(new FileWriter(f1));
					output.write(a);
					output.close();
                    a = "";
                    serialPort.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
            break;
    }
		
	}

	public void run() {
		 try {
	            Thread.sleep(20000);
	        } catch (InterruptedException e) {
	            System.out.println(e);
	        }
		
	}
	
	
}
