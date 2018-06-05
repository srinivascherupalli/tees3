<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="tees.DBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View File</title>
    </head>
    <body>
        
        <%  
            String filename=(request.getParameter("filename"));
        System.out.println(filename);
            
            Blob file = null;
            byte[ ] fileData = null ;

            try
            {    
                DBConnection dbconn=new DBConnection();
                Connection conn= dbconn.Connection();
            
                String sqlString = "SELECT file_data2 FROM files WHERE filename = '"+filename+"'";
                Statement myStatement = conn.createStatement();
                
                ResultSet rs=myStatement.executeQuery(sqlString);
                
                if (rs.next()) 
                {
                    file = rs.getBlob("file_data2");
                    fileData = file.getBytes(1,(int)file.length());
                } else 
                {
                    out.println("file not found!");
                    return;
                }
                
                response.setContentType("application/txt");
                response.setHeader("Content-Disposition", "inline");
                response.setContentLength(fileData.length);
                
                OutputStream output = response.getOutputStream();
                output.write(fileData);
                
                output.flush();
                
                RequestDispatcher rs2 = request.getRequestDispatcher("userview.jsp");
                rs2.include(request, response);   
            } catch (SQLException ex) {Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);} 
        %>
        
        <br><br>
       
        <div style="position: relative">
            
        </div>
        
    </body>
</html>