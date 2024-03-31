package Dao;

import java.sql.Connection;

public class DAOFactory {

    Connection connection = null;
    public DAOFactory(Connection connection){
        this.connection = connection;
    }

    public DAO<?> getDAO(String type){
        return switch (type.toLowerCase()) {
            case "employee" -> new EmployeeDAO(this.connection);
            case "department" -> new DeptDAO(this.connection);
            default -> null;
        };
    }
}
