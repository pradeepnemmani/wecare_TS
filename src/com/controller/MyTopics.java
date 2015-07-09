package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.buf.MessageBytes;

import com.data.DAO;
import com.data.DBManager;
import com.model.Topic;
import com.model.User;

/**
 * Servlet implementation class MyTopics
 */
@WebServlet("/MyTopics")
public class MyTopics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyTopics() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("usr");
		String message=null;
		String resource=null;
			try
			{
				if(user!=null)
				{
					List<Topic> topics=DAO.getTopics(user.getUserid());
					request.setAttribute("topics", topics);
					resource="topics.jsp";				
				}
			else
			{
				resource="login.jsp";
				message="Login..! before viewing the topics";
			}
		}
		catch (Exception e) {
			e.getMessage();
		}
		request.setAttribute("msg", message);
		request.getRequestDispatcher(resource).forward(request, response);
	}

}
