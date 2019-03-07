package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import forms.Login;

import persistance.DAO;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		
		System.out.println("entered login:\t"+username+"\t"+password);
		
		
	    Login login=new Login();
		login.setUsername(username);
		login.setPassword(password);
		
		System.out.println("out side of if statement");
		if(new DAO().isValidUser(username, password))
				{
			System.out.println("in login if conditon");
			response.sendRedirect("welcome.jsp");
		}
		else
		{
			System.out.println("in login else condition");
			response.sendRedirect("registration.jsp");
		}
	}
	}


