package org.example;

import Dao.*;

import myBeans.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class useFactoryDaos {

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
            DAOFactory daoFactory = new DAOFactory(connection);
            DAO<?> empDAO = daoFactory.getDAO("employee");
            Emp emp7369 = (Emp) empDAO.find(7369);
            System.out.println("Employee Details:");
            System.out.println(emp7369);
            DAO<?> departmentDao = new DeptDAO(connection);
            Dept dept20 = (Dept) departmentDao.find(emp7369.getDepartment().getDeptno());
            System.out.println("Department Details:");
            System.out.println(dept20);
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
