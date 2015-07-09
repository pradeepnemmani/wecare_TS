package listener;

import java.io.File;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class FileLocationContextListener
 *
 */
	 
	@WebListener
	public class FileLocationContextListener implements ServletContextListener {
	 
	    public void contextInitialized(ServletContextEvent servletContextEvent) {
	        String rootPath = System.getProperty("catalina.home");
	        ServletContext ctx = servletContextEvent.getServletContext();
	        File file = new File(rootPath + File.separator + "files");
	        if(!file.exists()) 
	        	{
	        		file.mkdirs();
	        		  
	        	}
	      
	        ctx.setAttribute("FILES_DIR_FILE", file);
	        ctx.setAttribute("FILES_DIR", file.getAbsolutePath());
	        
	        File imagesDir=new File(rootPath+File.separator+"images");
	        if(!(imagesDir.exists()))
	        		{
	        			imagesDir.mkdirs();
	        			
	        		}        
	        ctx.setAttribute("IMAGES_DIR", imagesDir.getAbsolutePath());
	        
	        File eventImagesDir=new File(rootPath+File.separator+"Event Images");
	        ctx.setAttribute("EVENTIMAGES_DIR", eventImagesDir.getAbsolutePath());
	    }
	 
	    public void contextDestroyed(ServletContextEvent servletContextEvent) {
	        //do cleanup if needed
	    }
	     	}
