package com.controller;

import java.io.File;
import java.io.IOException;
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
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.data.DAO;
import com.model.User;

/**
 * Servlet implementation class SaveImage
 */
@WebServlet("/SaveImage")
public class SaveImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServletFileUpload uploader = null;
    @Override
    public void init() throws ServletException{
    	
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        File filesDir = (File) getServletContext().getAttribute("IMAGES_DIR_FILE");
        fileFactory.setRepository(filesDir);
        this.uploader = new ServletFileUpload(fileFactory);
    }
       
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!ServletFileUpload.isMultipartContent(request)){
            throw new ServletException("Content type is not multipart/form-data");
        }
		User user=(User) request.getSession().getAttribute("usr");
		String message=null;
		String resource=null;

        try {
            List<FileItem> fileItemsList = uploader.parseRequest(request);
            Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
            while(fileItemsIterator.hasNext()){
                FileItem fileItem = fileItemsIterator.next();                           
                try {		           	              		             
//                	 File file = new File(request.getServletContext().getAttribute("IMAGES_DIR")+File.separator+user.getUserid());
//                     file.mkdirs();
//                     String dirPath=file.getAbsolutePath().toString();
//                     File file2=new File(dirPath+File.separator+fileItem.getName());
//                     fileItem.write(file2);
//                     DAO.updateImage(file2.getAbsolutePath().toString(), user.getUserid());
//                     user.setImage(file2.getAbsolutePath());
//                     request.getSession().setAttribute("usr", user);
//                     message="Welcome to wecare \n"+user.getName();
//                     request.setAttribute("message", message);
//                     resource="welcome.jsp";
                     
                     
                     File file1 = new File(request.getServletContext().getAttribute("IMAGES_DIR")+File.separator+user.getUserid());
                     if(file1.exists())
                     {
                    	 String[] files=file1.list();
                    	 if(files.length!=0)
                    	 {
                    		 Path path=Paths.get(file1.getAbsolutePath()+File.separator+files[0]);
                    		 Files.delete(path);
                    	 }
                     }
                     else {
						file1.mkdirs();
					}
                     File file3=new File(file1.getAbsolutePath().toString()+File.separator+fileItem.getName());
						fileItem.write(file3);
						DAO.updateImage(file3.getAbsolutePath().toString(), user.getUserid());
	                     user.setImage(file3.getAbsolutePath());
	                     request.getSession().setAttribute("usr", user);
	                     message="Welcome to wecare \n"+user.getName();
	                     request.setAttribute("message", message);
	                     resource="welcome.jsp";
                }
			 catch (Exception e) 
		        {
		        	e.getMessage();
		        	System.out.println("exception1=="+e.getMessage());
		        }	   
			}         
        } catch (Exception e) {
            e.getMessage();
        	System.out.println("exception2=="+e.getMessage());
        }       
        	request.getRequestDispatcher(resource).forward(request, response);
	}	
	}

	

