package com.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DAO;
import com.model.Comment;
import com.model.Topic;
import com.model.User;

/**
 * Servlet implementation class CommentTopic
 */
@WebServlet("/CommentTopic")
public class CommentTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentTopic() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String topicId=request.getParameter("topicid");
		User user=(User)request.getSession().getAttribute("usr");		
		Map<User,Topic> topicMap=new HashMap<>();		
		Map<User,Comment> topicComments=new HashMap<>();		
		List<Map<User,Comment>> commentsAndUsersList=new ArrayList<>();
		Map<Map<User,Topic>,List<Map<User,Comment>>> map=new HashMap<>();
		String message=null;
		String resource=null;
		if(user!=null)
		{
			int tId=Integer.valueOf(topicId);
			try
			{
				Topic topic=DAO.getTopic(tId);
				User topicUser=DAO.getUser(topic.getUserid());
				topicMap.put(topicUser,topic);
				List<Comment> commentsList=DAO.getComment(tId);
				for(Comment comment:commentsList)
				{
					User commentedUser=DAO.getUser(comment.getUserId());
					topicComments.put( commentedUser,comment);
				}	
				commentsAndUsersList.add(topicComments);
				map.put(topicMap,commentsAndUsersList );			
				request.setAttribute("map", map);
				resource="comment.jsp";
			}
			catch (Exception e) {
				e.getMessage();
			}
		}
		else {
			message="Please Login Before Commenting the topic...!";
			resource="login.jsp";
		}
		request.setAttribute("msg", message);
		request.getRequestDispatcher(resource).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String comnt=request.getParameter("comment");	
		String topicId=request.getParameter("tid");
		User user=(User)request.getSession().getAttribute("usr");
		String message=null;
		String resource=null;
		if(user!=null)
		{
			int tId=Integer.valueOf(topicId);
			Date date=new Date();
			Comment comment=new Comment();
			comment.setTopicId(tId);
			comment.setUserId(user.getUserid());
			comment.setComment(comnt);
			comment.setTime(String.valueOf(date));
			try
			{				
				boolean stored=DAO.addComment(comment);
				if(!stored)
				{
					message="Sorry...! Unabe to store the comment. Please try again...!";
				}
				Map<User,Topic> topicMap=new HashMap<>();			
				Map<User,Comment> topicComments=new HashMap<>();				
				List<Map<User,Comment>> commentsAndUsersList=new ArrayList<>();
				Map<Map<User,Topic>,List<Map<User,Comment>>> map=new HashMap<>();				
				Topic topic=DAO.getTopic(tId);
				User topicUser=DAO.getUser(topic.getUserid());
				topicMap.put(topicUser,topic);
				List<Comment> commentsList=DAO.getComment(tId);
				for(Comment comntt:commentsList)
				{
					User commentedUser=DAO.getUser(comntt.getUserId());
					topicComments.put( commentedUser,comntt);
				}	
				commentsAndUsersList.add(topicComments);
				map.put(topicMap,commentsAndUsersList );
				
				request.setAttribute("map", map);
				resource="comment.jsp";
			}
			catch (Exception e) {
				e.getMessage();
				resource="comment.jsp";
			}		
		}
		else 
		{
			message="Please Login Before Commenting the topic...!";
			resource="login.jsp";
		}
		request.setAttribute("msg", message);
		request.getRequestDispatcher(resource).forward(request, response);	}
}
