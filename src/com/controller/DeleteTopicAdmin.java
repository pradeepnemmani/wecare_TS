package com.controller;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class DeleteTopicAdmin
 */
@WebServlet("/DeleteTopicAdmin")
public class DeleteTopicAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTopicAdmin() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String topicId=request.getParameter("topicid");
		User user=(User)request.getSession().getAttribute("usr");
		String message=null;
		String resource=null;		
		if(user!=null)
		{
			try {
					int tId=Integer.valueOf(topicId);
					DAO.deleteTopic(tId);
					List<Topic> topics=new ArrayList<>(); 		
					String topic=request.getParameter("topicName");
					topics=DAO.searchTopicByName(topic);	
					resource="adminsearchtopics.jsp";	
					request.setAttribute("topics", topics);				
				} 
			catch (Exception e) {
				e.getMessage();
			}					
		}
		else
		{
			message="Please Login before deleting The Topic..!";
			resource="login.jsp";
		}
		request.setAttribute("msg", message);
		RequestDispatcher dispatcher=request.getRequestDispatcher(resource);
		dispatcher.forward(request, response);
	}

}
