package org.example.j2eeversion;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Dao.DAO;
import Dao.DAOFactory;
import Dao.DeptDAO;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import myBeans.Dept;
import myBeans.Emp;

@WebServlet(name = "Employee Details", value = "/getEmployeeDetails")
public class EmployeeDetails extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Class.forName( "org.postgresql.Driver" );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
        String url = "jdbc:postgresql://localhost:5432/lab3";
        String user = "postgres";
        String pass = "root";
        Connection connection = null;
        response.setContentType("text/html");
        String empid = request.getParameter("empid");
        String message = "";
        try {
            connection = DriverManager.getConnection( url, user, pass );
            DAOFactory daoFactory = new DAOFactory(connection);
            DAO<?> empDAO = daoFactory.getDAO("employee");
            Emp emp = (Emp) empDAO.find(Integer.parseInt(empid));
            System.out.println("Employee" + emp);
            System.out.println("123r");
            if(emp == null)
                message = "Not Found!";
            else message = emp.toString();
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            if ( connection != null )
                try {
                    connection.close();
                } catch ( SQLException ignore ) {
                    ignore.printStackTrace();
                }
        }
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }
}