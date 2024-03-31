package org.example;

import java.sql.*;
import java.util.*;
import org.apache.commons.lang3.StringUtils;

public class Main {

    static Connection connexion = null;
    static Scanner sc = null;
    public static void displayDepartment(Connection connexion) throws SQLException {
        Statement statement = connexion.createStatement();
        ResultSet resultat = statement.
                executeQuery( "SELECT deptno, dname, loc FROM dept" );

        while ( resultat.next() ) {
            int deptno = resultat.getInt( "deptno");
            String dname = resultat.getString( "dname" );
            String location = resultat.getString("loc");

            System.out.println("Department " + deptno + " is for "
                    + dname + " and located in "+location);
        }
        resultat.close();
        statement.close();
    }

    public static void moveDepartment(int empno, int newDeptno) throws SQLException{
//        Statement statement = connexion.createStatement();
//        int rows = statement.executeUpdate("UPDATE EMP set deptno = "+newDeptno+" WHERE empno = " + empno);
        PreparedStatement preparedStatement = connexion.prepareStatement("UPDATE EMP set deptno = ? WHERE empno = ?");
        preparedStatement.setInt(1, newDeptno);
        preparedStatement.setInt(2,empno);
        int rows = preparedStatement.executeUpdate();
        if (rows > 0){
            System.out.println(rows + " rows updated");
        }
        else {
            System.out.println("Nothing was updated");
        }
//        statement.close();
        preparedStatement.close();
    }

    public static void displayTable(String tablename) throws SQLException {
//        PreparedStatement preparedStatement = connexion.prepareStatement("SELECT * FROM ?");
//        preparedStatement.setString(1, tablename);
//        ResultSet results = preparedStatement.executeQuery();

        Statement statement = connexion.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM " + tablename);
//        ResultSet rs = preparedStatement.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        ArrayList<String> columnNames = new ArrayList<String>();
        for (int i = 1; i <= columnCount; i++ ) {
            String name = rsmd.getColumnName(i);
            columnNames.add(name);
            System.out.print("| "+ name + StringUtils.repeat(" ", 15).substring(name.length()) +"|");
        }
        System.out.print("\n");
        while ( rs.next() ) {
            for (String col : columnNames){
                String value = rs.getString(col);
                if(value == null){
                    value = "null";
                }
                System.out.print("| "+ value + StringUtils.repeat(" ", 15).substring(value.length())+"|");
            }
            System.out.print("\n");
        }
        statement.close();
    }


    public static void main(String[] args) {
        try {
            Class.forName( "org.postgresql.Driver" );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
        String url = "jdbc:postgresql://localhost:5432/lab3";
        String user = "postgres";
        String pass = "root";
        sc = new Scanner(System.in);
        try {
            connexion = DriverManager.getConnection( url, user, pass );
            /* Requests to bdd will be here */
            System.out.println("Bdd Connected");
            displayDepartment(connexion);
//            System.out.println("Enter Employee Number: ");
//            int empno = sc.nextInt();
//            System.out.println("Enter the new Department Number:");
//            int newDeptNo = sc.nextInt();
//            moveDepartment(empno, newDeptNo);
            displayTable("emp");
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            if ( connexion != null )
                try {
                    connexion.close();
                } catch ( SQLException ignore ) {
                    ignore.printStackTrace();
                }
        }
    }
}