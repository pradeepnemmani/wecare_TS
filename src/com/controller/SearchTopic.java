package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DAO;
import com.model.Topic;
import com.model.User;

/**
 * Servlet implementation class SearchTopic
 */
@WebServlet("/SearchTopic")
public class SearchTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchTopic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String topic=request.getParameter("topic");
		request.getSession().setAttribute("topicName", topic);
		User user=(User)request.getSession().getAttribute("usr");
		String message=null;
		String resource=null;
			try
			{
				if(user!=null)
				{
					List<Topic> topics=DAO.searchTopicByName(topic);										
					
						if(user.getUserid().equals("admin"))
						{
							resource="adminsearchtopics.jsp";
						}					
						else
						{
							resource="searchtopics.jsp";
						}						
						if(topics.size()>0)
						{
							request.setAttribute("topics", topics);
						}
						else 
						{
							message="No Topics were found for the request...!";
						}				
				}
			else
			{
				resource="login.jsp";
				message="Login..! before searching for a topics";
			}
		}
		catch (Exception e) {
			e.getMessage();
		}
		request.setAttribute("msg", message);
		request.setAttribute("topic", topic);
		request.getRequestDispatcher(resource).forward(request, response);
	}
}
