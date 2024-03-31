package org.example.j2eeversion;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.postgresql.ds.PGSimpleDataSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "All Departments", value = "/getAllDepartments")
public class AllDepartments extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<h1>All Employees:</h1>");
        out.println("<table>");
        try {
            Class.forName( "org.postgresql.Driver" );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = HikariDbConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM DEPT");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            ArrayList<String> columnNames = new ArrayList<String>();
            out.println("<tr>");
            for (int i = 1; i <= columnCount; i++ ) {
                String name = rsmd.getColumnName(i);
                columnNames.add(name);
                out.print("<th>"+ name +"</th>");
            }
            out.println("</tr>");
            while ( rs.next() ) {
                out.println("<tr>");
                for (String col : columnNames){
                    String value = rs.getString(col);
                    if(value == null){
                        value = "null";
                    }
                    out.print("<td>"+ value + "</td>");
                }
                out.print("</tr>");
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ( connection != null )
                try {
                    connection.close();
                } catch ( SQLException ignore ) {
                    ignore.printStackTrace();
                }
        }
        out.println("</table>");
        out.println("</body></html>");
    }
}
