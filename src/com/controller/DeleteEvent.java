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
 * Servlet implementation class DeleteEvent
 */
@WebServlet("/DeleteEvent")
public class DeleteEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEvent() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eventId=request.getParameter("eventid");
		User user=(User)request.getSession().getAttribute("usr");
		String message=null;
		String resource=null;
		if(user!=null)
		{
			try {
				DAO.deleteEvent(Integer.valueOf(eventId));
				List<Event> events=DAO.getEvents();
				request.setAttribute("events", events);
				resource="events.jsp";
			} catch (Exception e) {
				e.getMessage();
			}
		}
		else {
			message="please login before deleting the event...! ";
			resource="login.jsp";
		}
		request.setAttribute("msg", message);
		request.getRequestDispatcher(resource).forward(request, response);
	}
}
