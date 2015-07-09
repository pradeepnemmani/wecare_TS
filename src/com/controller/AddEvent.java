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
 * Servlet implementation class AddEvent
 */
@WebServlet("/AddEvent")
public class AddEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String description=request.getParameter("desciption");
		String date=request.getParameter("date");
		String plotNo=request.getParameter("plotNo");
		String area=request.getParameter("area");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String country=request.getParameter("country");
		String pincode=request.getParameter("pincode");
		User user=(User) request.getSession().getAttribute("usr");
		String message=null;
		String resource=null;
		if(user!=null)
		{		
			Event event=new Event();
			event.setName(name);
			event.setDescription(description);
			event.setDate(date);
			event.setPlotNo(plotNo);
			event.setArea(area);
			event.setCity(city);
			event.setState(state);
			event.setCounry(country);
			event.setPincode(Integer.valueOf(pincode));
			try
			{			
				DAO.addEvent(event);
				List<Event> events=DAO.getEvents();
				request.setAttribute("events", events);
				resource="events.jsp";				
			}
			catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
				System.out.println(e.getMessage());
				message=e.getMessage();
			}
		}
		else {
			message="Please Login Before Adding The Event...!";
			resource="login.jsp";
		}
		request.setAttribute("msg", message);
		request.getRequestDispatcher(resource).forward(request, response);
	}

	
}
