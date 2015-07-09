package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.data.DAO;
import com.model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid=request.getParameter("username");
		String pass=request.getParameter("password");
		String message=null;
		String resource="login.jsp";
		if(uid==null || uid.trim().equals("")){
			message="Please enter UserID";
		}
		else if(pass==null || pass.trim().equals("")){
			message="Please enter Password";
		}
		else{
			try {
				User usr=DAO.getUser(uid);
				if(usr.getPassword().equals(pass)){
					resource="welcome.jsp";
					HttpSession session=request.getSession();
					session.setAttribute("usr",usr );
					request.setAttribute("message", "Welcome to wecare \n"+usr.getName());
					request.setAttribute("user", usr);
					if(usr.getUserid().contentEquals("admin"))
					{
						session.setAttribute("admin", true);
					}
				}
				else{
					message="Invalid Password";
				}
				
				
			} catch (Exception e) {
				message=e.getMessage();
			}
		}
		request.setAttribute("msg", message);
		RequestDispatcher rd=request.getRequestDispatcher(resource);
		rd.forward(request, response);		
	}
}
