package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import jutils21.FrameWork;

import org.apache.struts2.ServletActionContext;

import com.database.DataBaseStatement;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import com.sun.net.httpserver.Authenticator.Success;


public class registration extends ActionSupport
{
	/**
	 * 
	 */
	String username,password,confirmpassword,email,phone,address,lcnumber;
	public String getLcnumber() {
		return lcnumber;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please enter your LicenseNo.", key = "errors.required")
	public void setLcnumber(String lcnumber) {
		this.lcnumber = lcnumber;
	}
	public String getUsername() 
	{
		return username;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please enter your Name.", key = "errors.required")
	public void setUsername(String username) 
	{
		this.username = username;
	}

	
	public String getPassword() {
		return password;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please enter your Password.", key = "errors.required")
	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getEmail() {
		return email;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please enter your Mail Id.", key = "errors.required")
	@EmailValidator(message = "Please enter a valid mailId address.")
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please enter your Phone number.", key = "errors.required")
	@RegexFieldValidator(message = "Please enter a valid mobile number.", expression = "^\\+?[0-9\\-]+\\*?$")
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please enter your Address.", key = "errors.required")
	public void setAddress(String address) {
		this.address = address;
	}
	public String execute()
	{
		
		try 
		{
			HttpServletRequest req=(HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			FrameWork fr=new FrameWork();
			if(fr.get(req))
			 {
			DataBaseStatement ds=new DataBaseStatement();
			PreparedStatement preparedStatement = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/vanet","root","root");
			System.out.println("------------------"+con);
			preparedStatement = con.prepareStatement(ds.insertUser());
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4,phone );
			preparedStatement.setString(5,address);
			preparedStatement.setString(6,lcnumber);
			preparedStatement.execute();	
		}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}		
		return SUCCESS;
	}
}
