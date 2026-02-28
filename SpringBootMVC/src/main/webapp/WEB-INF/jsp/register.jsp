<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Register</title></head>
<body>
<h2>Registration Form</h2>
<form action="/register" method="post">
    Name: <input type="text" name="name" required/></br>
	</br>
    Email: <input type="email" name="email" required/></br>
	</br>
    <button type="submit">Register</button>
</form>
</body>
</html>