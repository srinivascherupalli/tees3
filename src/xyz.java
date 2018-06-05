


import java.io.IOException;  
import java.io.PrintWriter;  
  





import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;

import tees.Mailer;


  
@WebServlet("/xyz") 
public class xyz extends HttpServlet {  
public void doGet(HttpServletRequest request,  
 HttpServletResponse response)  
    throws ServletException, IOException {  
  
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
    
    Random rand = new Random();
    int otp = rand.nextInt(900000) + 100000;
   
    System.out.println(otp);
    
    String x=request.getParameter("email");  
    String[] to={x};   
    String subject="One Time Password"; 
    String strAuthenID = request.getParameter("AuthenticationID");
    String msg1="Youre One Time Password is...";  
    String msg=msg1+ "'"+otp+"'";
    try
    {
    	  Class.forName("com.mysql.jdbc.Driver");
    	    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tees","root","root");
    	    Statement stt=conn.createStatement();
    int y=stt.executeUpdate("update users set status='Request',otp='"+otp+"' WHERE u_id='"+strAuthenID+"'");
    HttpSession session=request.getSession();
    
    }
    catch(Exception e)
    {
    System.out.print(e);
    e.printStackTrace();
    }
    Mailer.send(to, subject, msg); 
    out.println("<script>"
            +"alert('otp has been sent successfully')"
 			 +"</script>"); 
    RequestDispatcher rd = request.getRequestDispatcher("userauth1.jsp");
   	rd.forward(request,response);

    }  
  
}  