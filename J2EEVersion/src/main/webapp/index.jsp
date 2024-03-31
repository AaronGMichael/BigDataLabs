<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Landing Page</title>
</head>
<body>
<h1>Find Employee Details:</h1>
<br/>
<form method="POST" action="./getEmployeeDetails">
    <label for="empid">Employee ID:</label>
    <input id="empid" name="empid" type="text">
    <button type="submit">Submit</button>
</form>
<h1>Find Department Details:</h1>
<br/>
<form method="POST" action="./getDepartmentDetails">
    <label for="deptid">Department ID:</label>
    <input id="deptid" name="deptid" type="text">
    <button type="submit">Submit</button>
</form>
</body>
</html>