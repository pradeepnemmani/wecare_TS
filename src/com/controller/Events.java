package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DAO;
import com.model.Event;
import com.model.User;

/**
 * Servlet implementation class Events
 */
@WebServlet("/Events")
public class Events extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Events() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user=(User)request.getSession().getAttribute("usr");
		String message=null;
		String resource=null;
		if(user!=null)
		{
			try
			{
				List<Event> events=DAO.getEvents();
				request.setAttribute("events", events);
				resource="events.jsp";
			}
			catch (Exception e) {
				e.getMessage();
			}
		}
		else {
			resource="login.jsp";
			message="Please login before viewing the events...!";
		}
		request.setAttribute("msg", message);
		request.getRequestDispatcher(resource).forward(request, response);
	}
	/**
	 * 
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
