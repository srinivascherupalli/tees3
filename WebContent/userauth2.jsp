<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@page import="java.sql.ResultSet.*"%>
<%@page import="java.sql.Statement.*"%>
<%@page import="java.sql.Connection.*"%>
<link rel="stylesheet" href="css/tees.css">



<div class="sidenav">
  <a href="index.jsp">Home</a>
  <a href="userauth2.jsp">Send Authentication Request</a>
  <a href="search.jsp">Search</a>
  <a href="index.jsp">Logout</a>
</div>  
</div>    
<div class="main">
<table>
<h2 align="center"><font><strong>Authentication List</strong></font></h2>

<tr bgcolor="#A52A2A">
<td><b>Name</b></td>
<td><b>Email</b></td>
<td><b>User Name</b></td>
<td><b>Gender</b></td>
<td><b>Birth Date</b></td>
<td><b>Country</b></td>
<td><b>Authentication</b></td>
</tr></div>


<%
try{ 
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tees","root","root");
	PreparedStatement ps=con.prepareStatement("SELECT * FROM users where status='Request' and otp1='empty'");		
	ResultSet resultSet=ps.executeQuery();
while(resultSet.next()){
%>

<tr bgcolor="#DEB887">
<td><%=resultSet.getString("fullName") %></td>
<td><%=resultSet.getString("Email") %></td>
<td><%=resultSet.getString("userName") %></td>
<%-- <td><%=resultSet.getString("password") %></td>
 --%><td><%=resultSet.getString("gender") %></td>
<td><%=resultSet.getString("dob") %></td>
<td><%=resultSet.getString("country") %></td>
<td> <a href="sendrequest.jsp?AuthenticationID=<%=resultSet.getString("u_id")%>&&email=<%=resultSet.getString("Email")%>">request</a></td> 
</tr>

<% 
}

} catch (Exception e) {
e.printStackTrace();
}
%>

</table>
</div>