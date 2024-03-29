package com.lrng.js;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class AlbumController
 */
@WebServlet("/AlbumController")
public class AlbumController extends HttpServlet {
	private static final String ALBUM_NAME = "albumName";
	private static final long serialVersionUID = 1L;
	private static final String FILE_RELATIVE_PATH = "/src/app/images/";
	 private int maxFileSize = 50 * 1024;
	   private int maxMemSize = 4 * 1024;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlbumController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullPath = "";
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	      response.setContentType("text/html");
	      java.io.PrintWriter out = response.getWriter( );
	      if( !isMultipart ){
	         out.println("<html>");
	         out.println("<head>");
	         out.println("<title>Servlet upload</title>");  
	         out.println("</head>");
	         out.println("<body>");
	         out.println("<p>No file uploaded</p>"); 
	         out.println("</body>");
	         out.println("</html>");
	         return;
	      }
	      DiskFileItemFactory factory = new DiskFileItemFactory();
	      // maximum size that will be stored in memory
	      factory.setSizeThreshold(maxMemSize);
	      // Location to save data that is larger than maxMemSize.
	      factory.setRepository(new File("c:\\temp"));

	      // Create a new file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      // maximum file size to be uploaded.
	      //upload.setSizeMax( maxFileSize );
	      
	      try{ 
	          // Parse the request to get file items.
	          List fileItems = upload.parseRequest(request);
	    	
	          // Process the uploaded file items
	          Iterator i = fileItems.iterator();

	          out.println("<html>");
	          out.println("<head>");
	          out.println("<title>Servlet upload</title>");  
	          out.println("</head>");
	          out.println("<body>");
	          while ( i.hasNext () ) 
	          {
	             FileItem fi = (FileItem)i.next();
	             if ( !fi.isFormField () )	
	             {
	                // Get the uploaded file parameters
	                String fieldName = fi.getFieldName();
	                String fileName = fi.getName();
	                String contentType = fi.getContentType();
	                boolean isInMemory = fi.isInMemory();
	                long sizeInBytes = fi.getSize();
	                // Write the file
	                File file = null;
	                if( fileName.lastIndexOf("\\") >= 0 ){
	                   file = new File( fullPath + "/" +
	                   fileName.substring( fileName.lastIndexOf("\\"))) ;
	                }else{
	                   file = new File( fullPath + "/" +
	                   fileName.substring(fileName.lastIndexOf("\\")+1)) ;
	                }
	                fi.write( file ) ;
	                out.println("Uploaded Filename: " + fileName + "<br>");
	             }else{
	            	 if(fi.getFieldName().equals(ALBUM_NAME)){
	            		 byte[] data = new byte[(int)fi.getSize()];
	            		 fi.getInputStream().read(data, 0,new Long(fi.getSize()).intValue());	            		 
	            		 fullPath = createAlbum(request,new String(data));
	            	 }
	             }
	          }
	          out.println("</body>");
	          out.println("</html>");
	       }catch(Exception ex) {
	           System.out.println(ex);
	       }
	}

	private String createAlbum(HttpServletRequest request,String albumName) {
//		String albumName = request.getParameter(ALBUM_NAME);
		ServletContext context = request.getServletContext();
		String fullPath = context.getRealPath(FILE_RELATIVE_PATH);
		fullPath += "/"+albumName;
		File albumFolder = new File(fullPath);
		if(albumFolder == null || !albumFolder.exists() || !albumFolder.isDirectory()){
			if(albumFolder.mkdir()){
				System.out.println("Directory is created");
			}else{
				System.out.println("Unable to create directory");
			}
		}else{
			System.out.println("Album exists already");
		}
		return fullPath;
	}

}
