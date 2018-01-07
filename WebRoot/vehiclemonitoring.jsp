<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="javax.servlet.ServletContext"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page language="java" %>
<%@page language="java" import="java.sql.*"%> 

<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=0" />
	<title></title>
	<link rel="shortcut icon" type="image/x-icon" href="css/images/favicon.ico" />
	<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<%--	<link href='http://fonts.googleapis.com/css?family=Raleway:400,900,800,700,600,500,300,200,100' rel='stylesheet' type='text/css'>--%>
	
	<script src="js/jquery-1.8.0.min.js" type="text/javascript"></script>
	<!--[if lt IE 9]>
		<script src="js/modernizr.custom.js"></script>
	<![endif]-->
	<script src="js/jquery.carouFredSel-5.5.0-packed.js" type="text/javascript"></script>
	<script src="js/functions.js" type="text/javascript"></script>
</head>
<body >\
<% response.setIntHeader("Refresh", 5); %>
<!-- wrapper -->
<div id="wrapper">
	<!-- shell -->
	<div class="shell">
		<!-- container -->
		<div class="container">
		<div align="center" >
		<br/><br/><h3><font size="5px" color="#00BFFF">Software Defined Networking For RSU Clouds in Internet Of Vehicles</font></h3></div>
			<!-- header -->
			<header id="header">
				<h1 id="logo"></h1>
				<div class="cl">&nbsp;</div>
			</header>
			<!-- end of header -->
			<!-- navigaation -->
			<!-- end of navigation -->
			<!-- slider-holder -->
			<div class="slider-holder">
				
				<!-- slider -->
				<div class="slider">
					

					<ul>
						<li id="img1">
							<div class="slide-cnt">
								<h4>Intelligent Parking System</h4>
								<h2>IOT ENVIRONMENT</h2>
								<p>The advances in cloud computing and internet of things (IoT)
have provided a promising opportunity to further address the
increasing transportation issues, such as heavy traffic, congestion,
and vehicle safety.</p>							</div>
							<img src="css/images/mac-img.png" alt="" />
						</li>

					</ul>
				</div>
				<!-- end of slider -->

				<!-- thumbs -->
				<div id="thumbs-wrapper">
					<div id="thumbs">
						<a href="#img1" class="selected"><img src="css/images/thumb.png"/></a>
						<a href="#img2"><img src="css/images/thumb2.png" /></a>
						<a href="#img3"><img src="css/images/thumb3.png" /></a>
						<a href="#img4"><img src="css/images/thumb4.png" /></a>
						<a href="#img5"><img src="css/images/thumb.png" /></a>
						<a href="#img6"><img src="css/images/thumb2.png" /></a>
						<a href="#img7"><img src="css/images/thumb3.png" /></a>
						<a href="#img8"><img src="css/images/thumb4.png" /></a>
					</div>
					<a id="prev" href="#"></a>
					<a id="next" href="#"></a>
				</div>
				<!-- end of thumbs -->
			</div>

			<!-- main -->
			<div class="main">
			
				<div align="center">
				<a href="minning.jsp" style="color: red;"><s:label>MINING VEHICLE DATA SETS</s:label></a><br>
				</div><br><br>
				<div id="alldetails">
				<%
				String username = request.getParameter("username");
				  Class.forName("com.mysql.jdbc.Driver").newInstance();
				  Connection con1 = DriverManager.getConnection("Jdbc:mysql://localhost/vanet", "root", "root");
				  PreparedStatement ps=con1.prepareStatement("select * from clouddb");
				   ResultSet rs=ps.executeQuery();  
				    if(rs.next())
				    {
					    ResultSet rs1=ps.executeQuery();
				    	%>
				    	<table align="center">
				    	<tr style="font-size:10px;text-align: center;font-weight: bold; color: blue;">
				    	<th>S.N.O</th>
				    	<th>Vehicle ID</th>
				    	<th>RFID No</th>
				    	<th>Driver Lcn</th>
				    	<th>Time</th>
				    	<th>Place</th>
				    	<th>Travel</th>
				    	<th>Jerk Level</th>
				    	<th>Speed</th>
				    	<th>Drunk</th>
				    	<th>Seat Belt</th>
				    	</tr>
				    	<%
				    	int i=0;
				    	while(rs1.next())
				    	{
				    		i++;
				    		String color="#008000";
				    		if(Integer.parseInt(rs1.getString(7))>80)
				    		{
				    			color=" #FFA500";
				    		}
				    		else if(Integer.parseInt(rs1.getString(8))>102)
				    		{
				    			color=" #FFA500";
				    		}
				    		if(rs1.getString(9).equals("drunken"))
				    		{
				    			color=" #FF0000";
				    		}
				    		else if(rs1.getString(10).equals("no"))
				    		{
				    			color="#FF0000";
				    		}
				    		%>
					    	<tr style="font-size:10px; text-align: center;color:<%=color %>;">
					    	<td><%=i%></td>
					    	<td><%=rs1.getString(1)%></td>
					    	<td><%=rs1.getString(2)%></td>
					    	<td><%=rs1.getString(3)%></td>
					    	<td><%=rs1.getString(4)%></td>
					    	<td><%=rs1.getString(5)%></td>
					    	<td><%=rs1.getString(6)%></td>
					    	<td><%=rs1.getString(7)%></td>
					    	<td><%=rs1.getString(8)%></td>
					    	<td><%=rs1.getString(9)%></td>
					    	<td><%=rs1.getString(10)%></td>
					    	</tr>
					    	<%
				    	}
				    	%>
				    	</table>
				    	
				    	<%
				    }
				    else
				    {
				    	
				    }
				%></div>
				<div id="drunken">
				<%
				    if(rs.next())
				    {
					    ResultSet rs1=ps.executeQuery();
				    	%>
				    	<table align="center">
				    	<tr style="font-size:10px;text-align: center;font-weight: bold; color: blue;">
				    	<th>S.N.O</th>
				    	<th>Vehicle ID</th>
				    	<th>RFID No</th>
				    	<th>Driver Lcn</th>
				    	<th>Time</th>
				    	<th>Place</th>
				    	<th>Travel</th>
				    	<th>Jerk Level</th>
				    	<th>Speed</th>
				    	<th>Drunk</th>
				    	<th>Seat Belt</th>
				    	</tr>
				    	<%
				    	int i=0;
				    	while(rs1.next())
				    	{
				    		
				    		String color="#008000";
				    		
				    		if(rs1.getString(9).equals("drunken"))
				    		{i++;
				    		%>
					    	<tr style="font-size:10px; text-align: center;color:<%=color %>;">
					    	<td><%=i%></td>
					    	<td><%=rs1.getString(1)%></td>
					    	<td><%=rs1.getString(2)%></td>
					    	<td><%=rs1.getString(3)%></td>
					    	<td><%=rs1.getString(4)%></td>
					    	<td><%=rs1.getString(5)%></td>
					    	<td><%=rs1.getString(6)%></td>
					    	<td><%=rs1.getString(7)%></td>
					    	<td><%=rs1.getString(8)%></td>
					    	<td><%=rs1.getString(9)%></td>
					    	<td><%=rs1.getString(10)%></td>
					    	</tr>
					    	<%}
				    	}
				    	%>
				    	</table>
				    	
				    	<%
				    }
				    else
				    {
				    	
				    }
				%>
				</div>
				<div id="roads">
				<%
				    if(rs.next())
				    {
					    ResultSet rs1=ps.executeQuery();
				    	%>
				    	<table align="center">
				    	<tr style="font-size:10px;text-align: center;font-weight: bold; color: blue;">
				    	<th>S.N.O</th>
				    	<th>Vehicle ID</th>
				    	<th>RFID No</th>
				    	<th>Driver Lcn</th>
				    	<th>Time</th>
				    	<th>Place</th>
				    	<th>Travel</th>
				    	<th>Jerk Level</th>
				    	<th>Speed</th>
				    	<th>Drunk</th>
				    	<th>Seat Belt</th>
				    	</tr>
				    	<%
				    	int i=0;
				    	while(rs1.next())
				    	{
				    		String color="#008000";
				    		
				    		if(Integer.parseInt(rs1.getString(7))>80)
				    		{
				    			i++;
				    		%>
					    	<tr style="font-size:10px; text-align: center;color:<%=color %>;">
					    	<td><%=i%></td>
					    	<td><%=rs1.getString(1)%></td>
					    	<td><%=rs1.getString(2)%></td>
					    	<td><%=rs1.getString(3)%></td>
					    	<td><%=rs1.getString(4)%></td>
					    	<td><%=rs1.getString(5)%></td>
					    	<td><%=rs1.getString(6)%></td>
					    	<td><%=rs1.getString(7)%></td>
					    	<td><%=rs1.getString(8)%></td>
					    	<td><%=rs1.getString(9)%></td>
					    	<td><%=rs1.getString(10)%></td>
					    	</tr>
					    	<%}
				    	}
				    	%>
				    	</table>
				    	
				    	<%
				    }
				    else
				    {
				    	
				    }
				%>
				</div>
				<div id="speed">
				<%
				    if(rs.next())
				    {
					    ResultSet rs1=ps.executeQuery();
				    	%>
				    	<table align="center">
				    	<tr style="font-size:10px;text-align: center;font-weight: bold; color: blue;">
				    	<th>S.N.O</th>
				    	<th>Vehicle ID</th>
				    	<th>RFID No</th>
				    	<th>Driver Lcn</th>
				    	<th>Time</th>
				    	<th>Place</th>
				    	<th>Travel</th>
				    	<th>Jerk Level</th>
				    	<th>Speed</th>
				    	<th>Drunk</th>
				    	<th>Seat Belt</th>
				    	</tr>
				    	<%
				    	int i=0;
				    	while(rs1.next())
				    	{
				    		String color="#008000";
				    		
				    		if(Integer.parseInt(rs1.getString(8))>102)
				    		{
				    			i++;
				    		%>
					    	<tr style="font-size:10px; text-align: center;color:<%=color %>;">
					    	<td><%=i%></td>
					    	<td><%=rs1.getString(1)%></td>
					    	<td><%=rs1.getString(2)%></td>
					    	<td><%=rs1.getString(3)%></td>
					    	<td><%=rs1.getString(4)%></td>
					    	<td><%=rs1.getString(5)%></td>
					    	<td><%=rs1.getString(6)%></td>
					    	<td><%=rs1.getString(7)%></td>
					    	<td><%=rs1.getString(8)%></td>
					    	<td><%=rs1.getString(9)%></td>
					    	<td><%=rs1.getString(10)%></td>
					    	</tr>
					    	<%}
				    	}
				    	%>
				    	</table>
				    	
				    	<%
				    }
				    else
				    {
				    	
				    }
				   
				%>
				</div>
				<div id="seatbelts">
				<%
				    if(rs.next())
				    {
					    ResultSet rs1=ps.executeQuery();
				    	%>
				    	<table align="center">
				    	<tr style="font-size:10px;text-align: center;font-weight: bold; color: blue;">
				    	<th>S.N.O</th>
				    	<th>Vehicle ID</th>
				    	<th>RFID No</th>
				    	<th>Driver Lcn</th>
				    	<th>Time</th>
				    	<th>Place</th>
				    	<th>Travel</th>
				    	<th>Jerk Level</th>
				    	<th>Speed</th>
				    	<th>Drunk</th>
				    	<th>Seat Belt</th>
				    	</tr>
				    	<%
				    	int i=0;
				    	while(rs1.next())
				    	{
				    		
				    		String color="#008000";
				    		
				    		if(rs1.getString(10).equals("no"))
				    		{
				    			i++;
				    		%>
					    	<tr style="font-size:10px; text-align: center;color:<%=color %>;">
					    	<td><%=i%></td>
					    	<td><%=rs1.getString(1)%></td>
					    	<td><%=rs1.getString(2)%></td>
					    	<td><%=rs1.getString(3)%></td>
					    	<td><%=rs1.getString(4)%></td>
					    	<td><%=rs1.getString(5)%></td>
					    	<td><%=rs1.getString(6)%></td>
					    	<td><%=rs1.getString(7)%></td>
					    	<td><%=rs1.getString(8)%></td>
					    	<td><%=rs1.getString(9)%></td>
					    	<td><%=rs1.getString(10)%></td>
					    	</tr>
					    	<%}
				    	}
				    	%>
				    	</table>
				    	
				    	<%
				    }
				    else
				    {
				    	
				    }
				    ps.close();
				    con1.close();
				%>
				</div>
				</div>

				<br><br><br>

			</div>
			<!-- end of main -->
			<div class="cl">&nbsp;</div>
			
			<!-- footer -->
			
			<!-- end of footer -->
		</div>
		<!-- end of container -->
	</div>
	<!-- end of shell -->
</div>
<!-- end of wrapper -->
</body>
</html>