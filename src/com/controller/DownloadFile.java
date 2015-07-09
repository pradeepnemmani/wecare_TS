package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.model.User;

/**
 * Servlet implementation class DownloadFile
 */
@WebServlet("/DownloadFile")
public class DownloadFile extends HttpServlet {
	 private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadFile() {
        super();
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
						message="Sorry There are no files to download please try later..!";
					}
		            resource="viewuploadedfile.jsp";
		        } 
			 catch (Exception e) 
		        {
		        	e.getMessage();
		        }	        
	   }
		 
		else {
				message="Please login before downloading the file...!";
				resource="login.jsp";
		}
		request.setAttribute("msg", message);
		request.getRequestDispatcher(resource).forward(request, response);
	}
}
