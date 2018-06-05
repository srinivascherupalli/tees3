

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import tees.DBConnection;

import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;


import java.sql.Connection;

@WebServlet("/UploadFileServlet")
@MultipartConfig(maxFileSize = 16177215)

public class UploadFileServlet extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
        
                InputStream inputStream = null;
            
     
                
                /*Random rand = new Random();
                int  n = rand.nextInt(9999) + 1;
                String idTemp=(String.valueOf(n));*/

                String filename=(request.getParameter("filename"));
                String indexterm=(request.getParameter("indexterm"));
                String description=(request.getParameter("description"));
                Part filePart = request.getPart("file");
          
                if (filePart != null) 
                {
                    System.out.println(filePart.getName());
                    System.out.println(filePart.getSize());
                    System.out.println(filePart.getContentType());
                    inputStream = filePart.getInputStream();
                    
                   
                } 
                Scanner s = new Scanner(inputStream).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";
                System.out.println(result);
                
                try 
                {
                	 DBConnection dbconn=new DBConnection();
                     Connection conn= dbconn.Connection();                  
                    String sql = "INSERT INTO files (fileName,indexterm,description,file_data,tf,file_data2) values (?, ?,?,AES_ENCRYPT(?,'set'),'0',?)";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, filename);
                    statement.setString(2, indexterm);  
                    statement.setString(3, description);
                    statement.setString(4, result);
                    statement.setString(5, result); 
                     int row = statement.executeUpdate(); 
                     if(row>0){
                     out.println("<script>"
	                           +"alert('File uploaded Successfully..')"
	                			 +"</script>");
	  	
                     request.setAttribute("msg", "File Uploaded Successfully");
                   
                     RequestDispatcher rs2 = request.getRequestDispatcher("fileupload.jsp");
                     rs2.include(request, response);   
                     }
                  }
                catch(Exception e){
                	e.printStackTrace();
                	}   
                
                
                try 
                {
                	 DBConnection dbconn=new DBConnection();
                     Connection conn= dbconn.Connection();                     
                    String sql = "UPDATE files SET tf = (SELECT COUNT(file_data)  WHERE file_data LIKE '%'"+indexterm+"'%')";
                    PreparedStatement statement = conn.prepareStatement(sql);
                   int row = statement.executeUpdate(); 
                     if(row>0){
                     out.println("<script>"
	                           +"alert('File uploaded Successfully..')"
	                			 +"</script>");
	  	
                     request.setAttribute("msg", "File Uploaded Successfully");
                   
                     RequestDispatcher rs2 = request.getRequestDispatcher("fileupload.jsp");
                     rs2.include(request, response);   
                     }
                  }
                catch(Exception e){
                	e.printStackTrace();
                	}   
                
    }   }