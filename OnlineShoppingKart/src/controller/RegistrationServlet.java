package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistance.DAO;
import forms.RegistrationForm;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String mobile=request.getParameter("mobile");
		
		RegistrationForm regForm=new RegistrationForm();
		regForm.setUsername(username);
		regForm.setEmail(email);
		regForm.setAddress(address);
		regForm.setMobile(Long.parseLong(mobile));
		
		if(new DAO().reigsterUser(regForm, password))
		{
			response.sendRedirect("index.jsp");
		}
		else
		{
			response.sendRedirect("registration.jsp");
		}
	}
}
