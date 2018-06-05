

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OtpSessionServlet
 */
@WebServlet("/OtpSessionServlet")
public class OtpSessionServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String n=request.getParameter("otp");  
		String filename=request.getParameter("filename"); 
		try 
        {
		Class.forName("com.mysql.jdbc.Driver"); 
  	    java.sql.Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/tees","root","root");	
  	    PreparedStatement ps1=con1.prepareStatement("select * from users where otp1='"+n+"'");                	 
  	  HttpSession session=request.getSession();
  	    ResultSet rs = ps1.executeQuery();
  	  try{
			if(rs.next())
		{
				System.out.println(rs.getString(1));
				session.setAttribute("filename", filename);
		session.setAttribute("otp", rs.getString(2));
		
			getServletContext().getRequestDispatcher("/view_file.jsp").forward(request,response);
		}else{
			request.setAttribute("msg", "not send ");
			getServletContext().getRequestDispatcher("/Verify.jsp").forward(request,response);
		}
	}catch(Exception e){
    	e.printStackTrace();
    	}    

}catch(Exception e){
	e.printStackTrace();
}}}