package com.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;

/**
 * Servlet implementation class Uploads
 */
@WebServlet("/Uploads")
public class Uploads extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Uploads() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		User user=(User) request.getSession().getAttribute("usr");
		String message=null;
		String resource=null;
		if(user!=null)
		{
			 try {		           	               
		            File f=new File(request.getServletContext().getAttribute("FILES_DIR").toString());
		            String ss[]=f.list();
		            if(ss.length>0)
		            {
		            	request.setAttribute("files", ss);
		            }
		            else {
						message="Sorry There are no files uploaded yet..!";
					}
		           
		        } 
			 catch (Exception e) 
		        {
		        	e.getMessage();
		        	message="Sorry, Unable to retrive the files due to technical problems..!";
		        }	
			 resource="uploads.jsp";
	   }
		 
		else {
				message="Please login before downloading the file...!";
				resource="login.jsp";
		}
		request.setAttribute("msg", message);
		request.getRequestDispatcher(resource).forward(request, response);
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=(User)request.getSession().getAttribute("usr");
		String message=null;
		String resource=null;
		String fileName=request.getParameter("filename");
		if(user!=null)
		{
			if(fileName!=null || fileName.trim().length()!=0)
			{
				File file=new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileName);
				if(file.exists())
				{
					Path filePath=Paths.get(file.getAbsolutePath());
					try
					{
					Files.delete(filePath);
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
						message="Sorry, Unable to delete the File"+fileName;	
					}
				}
				else {
					message="Sorry, Unable to delete the File"+fileName;	
				}
				 try {		           	               
			            File f=new File(request.getServletContext().getAttribute("FILES_DIR").toString());
			            String ss[]=f.list();
			            request.setAttribute("files", ss);
			            resource="uploads.jsp";
			        } 
				 catch (Exception e) 
			        {
			        	e.getMessage();
			        }	     
				
			}
		}
		else {
			message="Please login before Deleting the file...!";
			resource="login.jsp";
		}
		request.setAttribute("msg", message);
		request.getRequestDispatcher(resource).forward(request, response);
	}
	
	
}