<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/tees.css">
<title>Register</title>
<script> 
function validate()
{ 
 var fullname = document.form.fullname.value;
 var email = document.form.email.value;
 var username = document.form.username.value; 
 var password = document.form.password.value;
 var conpassword= document.form.conpassword.value;
 
 if (fullname==null || fullname=="")
 { 
 alert("Full Name can't be blank"); 
 return false; 
 }
 else if (email==null || email=="")
 { 
 alert("Email can't be blank"); 
 return false; 
 }
 else if (username==null || username=="")
 { 
 alert("Username can't be blank"); 
 return false; 
 }
 else if(password.length<6)
 { 
 alert("Password must be at least 6 characters long."); 
 return false; 
 } 
 else if (password!=conpassword)
 { 
 alert("Confirm Password should match with the Password"); 
 return false; 
 } 
 } 
</script> 
</head>
<body>
<div class="sidenav">
  <a href="index.jsp">Home</a>
  <a href="dataowner.jsp">Dataowner</a>
  <a href="datauser.jsp">Datauser</a>
  <a href="cloud.jsp">Cloud</a>
</div>  
<center><h2>Registration Form </h2></center>
<div calss="main">
<form name="form" action="RegisterServlet1" method="post" onsubmit="return validate()">

<table align="center">
 <tr>
 <td>Full Name</td>
 <td><input type="text" name="fullname" /></td>
 </tr>
 <tr>
 <td>Email</td>
 <td><input type="text" name="email" /></td>
 </tr>
 <tr>
 <td>Username</td>
 <td><input type="text" name="username" /></td>
 </tr>
 <tr>
 <td>Password</td>
 <td><input type="password" name="password" /></td>
 </tr>
 <tr>
 <td>Confirm Password</td>
 <td><input type="password" name="conpassword" /></td>
 </tr>
 <tr>
 <td>Gender</td>
 <td><select name="gender" required>
<option value="" selected>Select</option>
  <option value="male">Male</option>
  <option value="female">Female</option>
</select></td>
 </tr>
 <tr>
 <td>Birth Date</td>
 <td><input type="date" name="dob" /></td>
 </tr>
 <tr>
 <td>Country</td>
 <td><input type="text" name="country" /></td>
 </tr>
 <tr>
 <td><%=(request.getAttribute("errMessage") == null) ? "": request.getAttribute("errMessage")%></td>
 </tr>
 <tr>
 <td><input type="submit" id="submit" value="Register"></input></td>
	 <td><input type="reset" id="reset" value="Reset"></input></td>
 </tr>
</table>
</form>
</div>
</body>
</html>