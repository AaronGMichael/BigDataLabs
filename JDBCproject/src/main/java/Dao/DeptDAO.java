package Dao;

import myBeans.Dept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptDAO extends DAO<Dept>{

    public DeptDAO(Connection connect) {
        super(connect);
    }

    @Override
    public Dept find(int id) throws SQLException {
        Dept dept = new Dept();
        PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * from dept where deptno = ?");
        preparedStatement.setInt(1, id);
        ResultSet results = preparedStatement.executeQuery();
        while (results.next()) {
            dept.setDeptno(results.getInt("deptno"));
            dept.setDname(results.getString("dname"));
            dept.setLoc(results.getString("loc"));
        }
        return dept;
    }

    @Override
    public boolean create(Dept object) {
        return false;
    }

    @Override
    public boolean update(Dept object) {
        return false;
    }

    @Override
    public boolean delete(Dept object) {
        return false;
    }
}
