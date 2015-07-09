package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

import com.model.User;

@WebServlet("/UploadDownloadFileServlet")
public class UploadDownloadFileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServletFileUpload uploader = null;
    @Override
    public void init() throws ServletException{
    	
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
        fileFactory.setRepository(filesDir);
        this.uploader = new ServletFileUpload(fileFactory);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String fileName = request.getParameter("fileName");
        User user=(User) request.getSession().getAttribute("usr");
        String message=null;
        String resource=null;
        if(user!=null)
        {
        	if(fileName == null || fileName.equals("")){
                throw new ServletException("File Name can't be null or empty");
            }
        	
            File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileName);
            try
            {
	            if(file.exists())
	            {
	            	 ServletContext ctx = getServletContext();
	                 InputStream fis = new FileInputStream(file);
	                 String mimeType = ctx.getMimeType(file.getAbsolutePath());
	                 response.setContentType(mimeType != null? mimeType:"application/octet-stream");
	                 response.setContentLength((int) file.length());
	                 response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");             
	                 ServletOutputStream os       = response.getOutputStream();
	                 byte[] bufferData = new byte[1024];
	                 int read=0;
	                 while((read = fis.read(bufferData))!= -1){
	                     os.write(bufferData, 0, read);
	                 }
	                 os.flush();
	                 os.close();
	                 fis.close();
	                 message="file succesfully downloaded...!";
	            }
            }
            catch (Exception e) {
            	message="sorry,you cannot download this file due to few technical problems,please try later..!";
			}
            File f=new File(request.getServletContext().getAttribute("FILES_DIR").toString());
            String ss[]=f.list();
            request.setAttribute("files", ss);
            resource="viewuploadedfile.jsp";
                     
        }
        else {
			message="Please login before uploading the file..!";
			resource="login.jsp";
		}
        request.setAttribute("msg", message);
        try
        {
        
        	request.getRequestDispatcher(resource).forward(request, response);
        }
        catch (Exception e) {
			// TODO: handle exception
		}
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!ServletFileUpload.isMultipartContent(request)){
            throw new ServletException("Content type is not multipart/form-data");
        }        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            List<FileItem> fileItemsList = uploader.parseRequest(request);
            Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
            while(fileItemsIterator.hasNext()){
                FileItem fileItem = fileItemsIterator.next();                             
                File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
                fileItem.write(file);
                File f=new File(request.getServletContext().getAttribute("FILES_DIR").toString());
              String ss[]=f.list();
              request.setAttribute("files", ss);
            }
        } catch (FileUploadException e) {
        	e.getMessage();
        } catch (Exception e) {
        	e.getMessage();
        }
        request.getRequestDispatcher("viewuploadedfile.jsp").forward(request, response);
    }
 
}