package org.example.j2eeversion;

import Dao.DAO;
import Dao.DAOFactory;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import myBeans.Dept;
import myBeans.Emp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "Department Details", value = "/getDepartmentDetails")
public class DepartmentDetails extends HttpServlet {

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
        String empid = request.getParameter("deptid");
        String message = "";
        try {
            connection = DriverManager.getConnection( url, user, pass );
            DAOFactory daoFactory = new DAOFactory(connection);
            DAO<?> deptDAO = daoFactory.getDAO("department");
            Dept dept = (Dept) deptDAO.find(Integer.parseInt(empid));
            if(dept == null)
                message = "Not Found!";
            else message = dept.toString();
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