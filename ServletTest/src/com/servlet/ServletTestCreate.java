package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletTestCreate
 * 
 * https://www.ianus-fdz.de/datenportal/collections/2017-04711/Tell-Fecheriye/B-0002/B-0002_TF-00002_pottery/Dateixyz.jpg
 * 
 * @author MR
 */
@WebServlet("/ServletTestCreate")
public class ServletTestCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String DOMAIN_NAME = "http://datenportal.ianus-fdz.de/";
	
	private static String contextPath = "pages/collectionView.jsp?dipId=";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTestCreate() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init() throws ServletException {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fullPath = request.getPathInfo();
		
		if(fullPath != null && !fullPath.equals("/")){
			
			String [] paths = fullPath.split("/");
			
			List<String> pathList = Arrays.asList(paths);
			
			System.out.println("Main Path: " + fullPath);
			
			String relativepath = relativePath(fullPath); 
			
			System.out.println("Relative Path: " + relativepath);

			String collectionId = pathList.get(1);
				
			String iId = getInternalId(collectionId);
				
			if(iId != null){
						
				String newURL = getDomainName() + getContextPath() + iId;
					
				response.setContentType("text/html");
					
				response.sendRedirect(newURL);
				
			}else {
					
				PrintWriter out = response.getWriter();
					
				out.print("<html><body><h1>Not found any Internal ID</h1><p>" + collectionId + "</p></body></html>");
				
				out.flush();
			}
	
		}else {
			response.sendRedirect(getDomainName());
		}
		
	}
	
	private String getInternalId(String collectionId){
		
		String iId = "320";
		
		return iId;
	}
	
	private String getDomainName(){
		
		return DOMAIN_NAME;
		
	}
	
	private String getContextPath(){
		
		return contextPath;
	}
	
	private static String relativePath(String fullPath) {
		
		String[] paths = fullPath.split("/");
		
		List<String> pathList = Arrays.asList(paths);
		
		return fullPath.replace(pathList.get(1) + "/" , "");
	
	}
	
	
	@Override
	public void destroy() {
		// do nothing.
	}

}
