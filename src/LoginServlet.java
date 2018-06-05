

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisDoctor
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		 String userName = request.getParameter("userName");
	        String password = request.getParameter("password");
	        String otp = request.getParameter("otp");
		          
		try{  
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tees","root","root");		
			
			
			HttpSession session=request.getSession(); 
			  
	    	  String status="Request";
	 	 //creating connection with the database 
	       
	         java.sql.PreparedStatement ps =con.prepareStatement("select * from users where userName=? and password=? and otp=?");
	         ps.setString(1, userName);
	         ps.setString(2, password);
	         ps.setString(3, otp);
	         ResultSet rs = ps.executeQuery();
			if(rs.next()){
				System.out.println("login successful");
				request.setAttribute("msg","Password matched");
				getServletContext().getRequestDispatcher("/userview.jsp").forward(request,response);
			}else{
				request.setAttribute("msg","Password Mismatched");
				getServletContext().getRequestDispatcher("/datauser.jsp").forward(request,response);
			}
		}catch(Exception e){
			
			e.printStackTrace();
		}
	}
		  
		 

	}


