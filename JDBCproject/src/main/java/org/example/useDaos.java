package org.example;

import Dao.*;
import myBeans.Emp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class useDaos {
    public static void main(String[] args) {
        try {
            Class.forName( "org.postgresql.Driver" );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
        String url = "jdbc:postgresql://localhost:5432/lab3";
        String user = "postgres";
        String pass = "root";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection( url, user, pass );
//            DAO<Dept> departmentDao = new DeptDAO(connection);
//            Dept dept20 = departmentDao.find(20);
//            System.out.println(dept20);
            DAO<Emp> empDAO = new EmployeeDAO(connection);
            Emp emp7369 = empDAO.find(7369);
            System.out.println(emp7369);
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
    }
}
