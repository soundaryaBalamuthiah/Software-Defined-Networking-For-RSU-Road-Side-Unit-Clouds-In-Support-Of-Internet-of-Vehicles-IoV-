<%@ page language="java" import="java.util.*" import="java.io.*"
	pageEncoding="ISO-8859-1"%>
<%@page import="javax.servlet.ServletContext"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport"
			content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=0" />
		<title></title>
		<link rel="shortcut icon" type="image/x-icon"
			href="css/images/favicon.ico" />
		<link rel="stylesheet" href="css/style.css" type="text/css"
			media="all" />
<%--		<link--%>
<%--			href='http://fonts.googleapis.com/css?family=Raleway:400,900,800,700,600,500,300,200,100'--%>
<%--			rel='stylesheet' type='text/css'>--%>

		<script src="js/jquery-1.8.0.min.js" type="text/javascript">
</script>
		<!--[if lt IE 9]>
		<script src="js/modernizr.custom.js"></script>
	<![endif]-->
		<script src="js/jquery.carouFredSel-5.5.0-packed.js"
			type="text/javascript">
</script>
		<script src="js/functions.js" type="text/javascript">
</script>
		<script>
function callsub() {

	document.f.action = "registration";
	document.f.submit();

}
</script>

	</head>
	<body>
		<%
			response.setIntHeader("Refresh", 5);
		%>
		<%
			String line = "";
		%>

		<!-- wrapper -->
		<div id="wrapper">
			<!-- shell -->
			<div class="shell">
				<!-- container -->
				<div class="container">
					<div align="center">
						<br />
						<br />
						<h3>
							<font size="5px" color="#00BFFF">Software Defined
								Networking For RSU Clouds in Internet Of Vehicles</font>
						</h3>
					</div>
					<!-- header -->
					<header id="header">
					<div class="cl">
						&nbsp;
					</div>
					</header>
					<!-- end of header -->
					<!-- navigaation -->
					<nav id="navigation">
					<a href="#" class="nav-btn">HOME<span></span> </a>
					<ul>
						<li>
							<a href="index.jsp">Home</a>
						</li>
						<li class="active">
							<a href="register.jsp">User Register</a>
						</li>
						<li>
							<a href="login.jsp">Cloud Login</a>
						</li>
					</ul>
					<div class="cl">
						&nbsp;
					</div>
					</nav>
					<!-- end of navigation -->
					<!-- slider-holder -->
					<div class="slider-holder">

						<!-- slider -->
						<div class="slider">


							<div class="arrs">
								<a href="#" class="prev-arr"></a>
								<a href="#" class="next-arr"></a>
							</div>

							<ul>
								<li id="img1">
									<div class="slide-cnt">
										<h4>
											Intelligent Parking System
										</h4>
										<h2>
											IOT ENVIRONMENT
										</h2>
										<p>
											The advances in cloud computing and internet of things (IoT)
											have provided a promising opportunity to further address the
											increasing transportation issues, such as heavy traffic,
											congestion, and vehicle safety.
										</p>
									</div>
									<img src="css/images/mac-img.png" alt="" />
								</li>
							</ul>
						</div>
						<!-- end of slider -->

						<!-- thumbs -->
						<div id="thumbs-wrapper">
							<div id="thumbs">
								<a href="#img1" class="selected"><img
										src="css/images/thumb.png" /> </a>
								<a href="#img2"><img src="css/images/thumb2.png" /> </a>
								<a href="#img3"><img src="css/images/thumb3.png" /> </a>
								<a href="#img4"><img src="css/images/thumb4.png" /> </a>
								<a href="#img5"><img src="css/images/thumb.png" /> </a>
								<a href="#img6"><img src="css/images/thumb2.png" /> </a>
								<a href="#img7"><img src="css/images/thumb3.png" /> </a>
								<a href="#img8"><img src="css/images/thumb4.png" /> </a>
							</div>
							<a id="prev" href="#"></a>
							<a id="next" href="#"></a>
						</div>
						<!-- end of thumbs -->
					</div>

					<!-- main -->
					<div class="main">

						<div align="center">
							<div id="errorreportdiv"></div>
									<h3><font size="5px" color="#00BFFF">Monitoring Using RFID</font></h3>


							<s:form name="f">

								<%
									try {
											FileReader fr = new FileReader("webapps/RSUCloud/a.txt");
											BufferedReader br = new BufferedReader(fr);
											line = br.readLine();
											System.out.println("--line---"+line);
									//if (line != null) {
												Class.forName("com.mysql.jdbc.Driver").newInstance();
												Connection con1 = DriverManager.getConnection(
														"Jdbc:mysql://localhost/vanet", "root", "root");
												PreparedStatement ps = con1
														.prepareStatement("select * from clouddb where rfidno=?");
												ps.setString(1, line);
												ResultSet rs = ps.executeQuery();
												if (rs.next()) {
												%>
								<table border="2" id="tabcolor" width="500">
												<tr>
												<th>Vehicle ID</th>
												<th>RFID No</th>
												<th>License No</th>
												<th>Time</th>
												<th>Source</th>
												<th>Travelled Path</th>
												<th>Jerk Level</th>
												<th>Speed</th>
												<th>Drunken</th>
												<th>Seat Belt</th>
												</tr>
												<tr>
												<td><%=rs.getString("vid")%></td>
												<td><%=rs.getString("rfidno")%></td>
												<td><%=rs.getString("lcn")%></td>
												<td><%=rs.getString("time")%></td>
												<td><%=rs.getString("place")%></td>
												<td><%=rs.getString("previous")%></td>
												<td><%=rs.getString("jerklevel")%></td>
												<td><%=rs.getString("speed")%></td>
												<td><%=rs.getString("drunk")%></td>
												<td><%=rs.getString("seatbelt")%></td>
												</tr>
												</table>
												<%
											//	}
											}
											fr.close();
										} catch (Exception e) {
											e.printStackTrace();
										}
								%>
							</s:form>
						</div>

					</div>
					<!-- end of main -->
					<div class="cl">
						&nbsp;
					</div>

					<!-- footer -->
					<div id="footer">
						<div class="footer-nav">

							<div class="cl">
								&nbsp;
							</div>
						</div>

						<div class="cl">
							&nbsp;
						</div>
					</div>
					<!-- end of footer -->
				</div>
				<!-- end of container -->
			</div>
			<!-- end of shell -->
		</div>
		<!-- end of wrapper -->
	</body>
</html>