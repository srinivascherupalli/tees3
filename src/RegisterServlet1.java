

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet1
 */
@WebServlet("/RegisterServlet1")
public class RegisterServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 String fullName = request.getParameter("fullname");
		 String email = request.getParameter("email");
		 String userName = request.getParameter("username");
		 String password = request.getParameter("password");
		 String gender = request.getParameter("gender");
		 String dob = request.getParameter("dob");
		 String country = request.getParameter("country");  
			PrintWriter out=response.getWriter();          
			try{  
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tees","root","root");		
				PreparedStatement ps=con.prepareStatement( "insert into users(u_id,fullName,Email,userName,password,gender,dob,country,status,otp,otp1) values (NULL,?,?,?,?,?,?,?,'Requested','empty','empty')");  
			  
						ps.setString(1, fullName);
						 ps.setString(2, email);
						 ps.setString(3, userName);
						 ps.setString(4, password);
						 ps.setString(5, gender);
						 ps.setString(6, dob);
						 ps.setString(7, country);
						 int i= ps.executeUpdate();
						 
			if(i>0)
				
				System.out.println("value Registered");
				request.setAttribute("msg","Registration successfull...Login here");
			getServletContext().getRequestDispatcher("/datauser.jsp").forward(request, response);
			      
			          
			}catch (Exception e2) {System.out.println(e2);}  
			          
			out.close();  
			}  
	}


