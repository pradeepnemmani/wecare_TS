package com.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.data.DAO;
import com.model.Topic;
import com.model.User;

/**
 * Servlet implementation class EditTopic
 */
@WebServlet("/EditTopic")
public class EditTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTopic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("topicid");
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("usr");	
		String message=null;
		String resource=null;	
		if(user!=null)
		{
			int topicId=Integer.parseInt(id);
			try
			{
				Topic topic=DAO.getTopic(topicId);
				resource="edittopic.jsp";
				request.setAttribute("topic", topic);
			}
			catch (Exception e) {
				e.getMessage();
			}
		}
		else {
			message="please login before editing any topic...!";
			resource="login.jsp";
		}	 
		request.setAttribute("msg", message);
		RequestDispatcher rd=request.getRequestDispatcher(resource);
		rd.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("topicid");
		String topicName=request.getParameter("name");
		String comment=request.getParameter("comment");
		String date=request.getParameter("date");
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("usr");		
		String msg=null;
		String resource=null;	
		if(user!=null)
		{		
			topicName=topicName.trim();
			comment=comment.trim();	
			if(topicName==null ||topicName.trim().length()==0)
			{
				msg="please enter the topic Name";
			}
			else if(comment.equalsIgnoreCase("") || comment.trim().length()==0)
			{
				msg="please enter the comment";
			}
			else {
				int topicId=Integer.parseInt(id);
				Topic topic=new Topic();
				topic.setTid(topicId);
				topic.setTname(topicName);
				topic.setComment(comment);
				topic.setDate(date);
				topic.setUserid(user.getUserid());
				try
				{
					DAO.editTopic(topic);					
					List<Topic> topics=DAO.getTopics(user.getUserid());
					request.setAttribute("topics", topics);
					resource="topics.jsp";
				}
				catch (Exception e) {
					e.getMessage();
			}
		}
		}
		else
		{
			msg="Please logIn before adding the topic";
			resource="login.jsp";
		}		 
		request.setAttribute("msg", msg);
		request.getRequestDispatcher(resource).forward(request, response);
	}

}


	

