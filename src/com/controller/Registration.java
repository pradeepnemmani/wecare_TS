package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DAO;
import com.model.User;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid=request.getParameter("username");
		String pass=request.getParameter("password");
		String name=request.getParameter("name");
		String gen=request.getParameter("gender");
		String city=request.getParameter("city");
		String message=null;
		String resource="registration.jsp";
		if(uid==null || uid.trim().equals("")){
			message="Please enter UserID";
		}
		else if(pass==null || pass.trim().equals("")){
			message="Please enter Password";
		}
		else if(name==null|| name.trim().equals("")){
			message="Please enter name";
		}
		else if(gen==null||gen.equals("")){
			message="Please enter gender";		
			
		}
		else if(city==null||city.equals("")){
			
		}
		else{
			User usr=new User();
			usr.setUserid(uid);
			usr.setPassword(pass);
			usr.setName(name);
			usr.setGender(gen);
			usr.setCity(city);
			try {
				DAO.saveUser(usr);
				message="Registered Successfully :Please Login";
				resource="login.jsp";
			} catch (Exception e) {
				message=e.getMessage();
			}
			
		}
		request.setAttribute("msg", message);
		RequestDispatcher rd=request.getRequestDispatcher(resource);
		rd.forward(request, response);
	}

}
