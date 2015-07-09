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
 * Servlet implementation class EditEvent
 */
@WebServlet("/EditEvent")
public class EditEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEvent() {
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
			try
			{
				Event event=DAO.getEvent(Integer.valueOf(eventId));
				if(event!=null)
				{
					resource="editevent.jsp";
					request.setAttribute("event", event);
				}
				else {
					message="Sorry..,we are unable to allow You to edit this event., due to few technical problems...!";
					List<Event> events=DAO.getEvents();
					request.setAttribute("events", events);
					resource="events.jsp";
				}
				
			}
			catch (Exception e) {
				e.getMessage();
				System.out.println("From Get edit event==="+e.getMessage());
			}
		}
		else {
			message="please login before editing any topic..!";
			resource="login.jsp";
		}
		request.setAttribute("msg",message);
		request.getRequestDispatcher(resource).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eid=request.getParameter("eventid");
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
			event.seteId(Integer.valueOf(eid));
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
				DAO.editEdit(event);
				List<Event> events=DAO.getEvents();
				request.setAttribute("events", events);
				resource="events.jsp";
			}
			catch (Exception e) {
				e.getMessage();
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
