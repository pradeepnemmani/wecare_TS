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

import org.apache.catalina.Session;

import com.data.DAO;
import com.model.Topic;
import com.model.User;

/**
 * Servlet implementation class Addtopic
 */
@WebServlet("/Addtopic")
public class Addtopic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addtopic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String topicName=request.getParameter("name");
		String comment=request.getParameter("comment");
		User user=(User)request.getSession().getAttribute("usr");		
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
				Topic topic=new Topic();
				topic.setTname(topicName);
				topic.setComment(comment);
				topic.setUserid(user.getUserid());
				Date date=new Date();
				topic.setDate(String.valueOf(date.getTime()));		
				try
				{
					DAO.saveTopic(topic);					
					List<Topic> topics=DAO.getTopics(user.getUserid());
					request.setAttribute("topics", topics);
					resource="topics.jsp";				
				}
				catch (Exception e) {
					// TODO: handle exception
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
