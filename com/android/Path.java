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

public class Path extends ActionSupport implements ServletContextAware {

	String src, dst, journeytype;
	String finalpath = "No path found";
	public String Ex_Date, Ex_Time;
	public int hour, minute, second;
	Calendar cal = new GregorianCalendar();
	PreparedStatement preparedStatement = null;

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}

	public String getJourneytype() {
		return journeytype;
	}

	public void setJourneytype(String journeytype) {
		this.journeytype = journeytype;
	}

	public void path() {
		try {
			PrintWriter pw = ServletActionContext.getResponse().getWriter();
			DataBaseStatement ds = new DataBaseStatement();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost/vanet", "root", "root");
			Statement stat = con.createStatement();
			hour = cal.get(Calendar.HOUR);
			minute = cal.get(Calendar.MINUTE);
			Ex_Time = hour + ":" + minute;

			Vector vv = new Vector();
			ResultSet rs = stat.executeQuery("select * from path");
			while (rs.next()) {
				String rs2 = rs.getString(2);
				String rs3 = rs.getString(3);
				String rs4 = rs.getString(4);
				vv.add(rs2 + "," + rs4 + "," + rs3);
			}
			rs = stat.executeQuery("select * from path where sorce='" + src
					+ "' and destination='" + dst + "'");
			HashSet st = new HashSet();
			if (rs.next()) {
				rs = stat.executeQuery("select * from path where sorce='" + src
						+ "' and destination='" + dst + "'");
				while (rs.next()) {
					String firstvia = rs.getString(4);
					String firstpathstrs[] = firstvia.split("\\,");
					Vector check = new Vector();
					String addline = src;
					for (int i = 0; i < firstpathstrs.length; i++) {
						String currentelement = firstpathstrs[i];
						Iterator it = vv.iterator();
						addline = addline + "," + currentelement;
						while (it.hasNext()) {
							String currentpath = it.next().toString();
							if (currentpath.contains(currentelement)
									&& currentpath.contains(dst)
									&& (!check.contains(currentpath))) {
								// System.out.println("-----"+currentelement+"-----------"+destination);
								// System.out.println("--------------------->"+currentpath);
								check.add(currentpath);
								int s = currentpath.indexOf(currentelement);
								int d = currentpath.indexOf(dst);
								String newvia = "";
								if (s < d) {
									newvia = StringUtils.substringBetween(
											currentpath, currentelement, dst)
											.replaceFirst(",", "");
									if (newvia.length() > 4) {
										int lastin = newvia.lastIndexOf(",");
										newvia = newvia.substring(0, lastin);
									}
								} else {
									String betw = StringUtils.substringBetween(
											currentpath, dst, currentelement)
											.replaceFirst(",", "");
									if (betw.length() > 4) {
										int lastin = betw.lastIndexOf(",");
										betw = betw.substring(0, lastin);
										String reverse[] = betw.split("\\,");
										Vector revvec = new Vector(Arrays
												.asList(reverse));
										Collections.reverse(revvec);
										newvia = revvec.toString().replace("[",
												"").replace("]", "");
									}
								}
								StringTokenizer stk = new StringTokenizer(
										firstvia, currentelement);
								// System.out.println("---------*************************new via*******-----"+addline+","+newvia+","+destination);
								st.add(addline + "," + newvia + "," + dst);
							}
						}
					}
				}
			} else {
				rs = stat.executeQuery("select * from path where sorce='" + src
						+ "'");
				Vector nextvia = new Vector();
				while (rs.next()) {
					String current = rs.getString(4);
					if (current.contains(dst)) {
						StringTokenizer s = new StringTokenizer(current, dst);
						nextvia.add(s.nextToken());
					}
				}
				Iterator ii = nextvia.iterator();
				while (ii.hasNext()) {
					String firstvia = ii.next().toString();
					String firstpathstrs[] = firstvia.split("\\,");
					Vector check = new Vector();

					String addline = src;
					for (int i = 0; i < firstpathstrs.length; i++) {
						String currentelement = firstpathstrs[i];
						Iterator it = vv.iterator();
						addline = addline + "," + currentelement;
						while (it.hasNext()) {
							String currentpath = it.next().toString();
							if (currentpath.contains(currentelement)
									&& currentpath.contains(dst)
									&& (!check.contains(currentpath))) {
								// System.out.println("-----"+currentelement+"-----------"+destination);
								// System.out.println("--------------------->"+currentpath);
								check.add(currentpath);
								int s = currentpath.indexOf(currentelement);
								int d = currentpath.indexOf(dst);
								String newvia = "";
								if (s < d) {
									newvia = StringUtils.substringBetween(
											currentpath, currentelement, dst)
											.replaceFirst(",", "");
									if (newvia.length() > 4) {
										int lastin = newvia.lastIndexOf(",");
										System.out.println("------newvia--"+newvia);
										System.out.println("------lastin--"+lastin);
										newvia = newvia.substring(0, lastin);
									}
								} else {
									String betw = StringUtils.substringBetween(
											currentpath, dst, currentelement)
											.replaceFirst(",", "");
									if (betw.length() > 4) {
										int lastin = betw.lastIndexOf(",");
										betw = betw.substring(0, lastin);
										String reverse[] = betw.split("\\,");
										Vector revvec = new Vector(Arrays
												.asList(reverse));
										Collections.reverse(revvec);
										newvia = revvec.toString().replace("[",
												"").replace("]", "");
									}

								}

								StringTokenizer stk = new StringTokenizer(
										firstvia, currentelement);
								// System.out.println("---------*************************new via*******-----"+addline+","+newvia+","+destination);
								st.add(addline + "," + newvia + "," + dst);
							}
						}
					}

				}
			}
			//System.out.println("------rameshbabu----"+st);
			Iterator chec = st.iterator();
			int val = 0;
			int co = 0;
			TreeMap pathcaluculation = new TreeMap();
			Random ra = new Random();
			while (chec.hasNext()) {
				int safety = 0;
				int comfortable = 0;
				int fast = 0;
				String currentpath = chec.next().toString();
				StringTokenizer str = new StringTokenizer(currentpath, ",");
				Vector strings = new Vector();
				while (str.hasMoreTokens()) {
					strings.add(str.nextToken());
				}
				while (strings.size() >= 2) {
					String pah = strings.get(0).toString().replaceAll("[^\\w]",
							"")
							+ "->"
							+ strings.get(1).toString()
									.replaceAll("[^\\w]", "");
					System.out
							.println("---------**************current check string********************------------>"
									+ pah);
					rs = stat
							.executeQuery("select drunk,speed,jerklevel,seatbelt,vid from clouddb where previous='"
									+ pah + "'");

					if (rs.next()) {
						rs = stat
								.executeQuery("select drunk,speed,jerklevel,seatbelt,vid from clouddb where previous='"
										+ pah.replace("[\\w]", "") + "'");
						while (rs.next()) {
							if (journeytype.equals("Saftey")) {
								safety = safety
										+ Integer.parseInt(rs.getString("speed"))
										+ Integer.parseInt(rs.getString("speed"));
							} else if (journeytype.equals("Comfortable")) {
								comfortable = comfortable
										+ Integer.parseInt(rs.getString("speed"))
										+ Integer.parseInt(rs.getString("speed"));

							} else {
								fast++;
							}
						}
					} else {
						if (journeytype.equals("Saftey")) {
							safety = safety + ra.nextInt(80) + ra.nextInt(80);
						} else if (journeytype.equals("Comfortable")) {
							comfortable = comfortable + ra.nextInt(80)
									+ ra.nextInt(80);

						} else {
							fast = fast + ra.nextInt(10);
						}

					}
					strings.remove(0);
				}
				if (journeytype.equals("Saftey")) {

					pathcaluculation.put(safety, currentpath);
				} else if (journeytype.equals("Comfortable")) {
					pathcaluculation.put(comfortable, currentpath);
				} else {
					pathcaluculation.put(fast, currentpath);
				}
			}
			if (journeytype.equals("Saftey")) {
				if (pathcaluculation.size() > 1) {
					finalpath = (String) pathcaluculation.get(pathcaluculation
							.lastKey());
				} else {
					finalpath = (String) pathcaluculation.get(pathcaluculation
							.firstKey());
				}
			} else if (journeytype.equals("Comfortable")) {
				if (pathcaluculation.size() > 1) {
					pathcaluculation.remove(pathcaluculation.firstKey());
					finalpath = (String) pathcaluculation.get(pathcaluculation
							.firstKey());
				} else {
					finalpath = (String) pathcaluculation.get(pathcaluculation
							.firstKey());
				}
			} else {
				finalpath = (String) pathcaluculation.get(pathcaluculation
						.firstKey());
			}

			System.out.println("--finalpath---" + finalpath);
			pw.println("success$"
					+ finalpath.replaceAll(",,", ",").replaceAll(",", "->").replace("'","").replace("',,","->"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void destination()
	{
		String all[] = null;
        try {
        	PrintWriter pw = ServletActionContext.getResponse().getWriter();
        	DataBaseStatement ds = new DataBaseStatement();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost/vanet", "root", "root");
			Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("select * from path where sorce='" + src + "'");
            System.out.println("---src---"+src);
            int i = 0;
            Vector vv = new Vector();
            while (rs.next()) {
                vv.add(rs.getString(3).toString());
                String via = rs.getString(4);
                StringTokenizer str = new StringTokenizer(via, ",");
                while (str.hasMoreTokens()) {
                    String cur = str.nextToken();
                    if (!vv.contains(cur)) {
                        vv.add(cur);
                    }
                }
                i++;
            }
            System.out.println("--------------" + vv);
            all = new String[vv.size()];
            Iterator it = vv.iterator();
            int j = 0;
            while (it.hasNext()) {
                all[j] = it.next().toString();
                j++;
            }
            System.out.println("---------------------------size==========" + all);
            //val.setdestinations(all);
            pw.println("success$"
					+ vv);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub

	}

}
