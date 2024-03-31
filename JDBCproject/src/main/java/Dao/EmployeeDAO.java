package Dao;

import myBeans.Dept;
import myBeans.Emp;

import java.sql.*;

public class EmployeeDAO extends DAO<Emp>{
    public EmployeeDAO(Connection connect) {
        super(connect);
    }

    @Override
    public Emp find(int id) throws SQLException {
        Emp emp = new Emp();
        PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * from emp where empno = ?");
        preparedStatement.setInt(1, id);
        ResultSet results = preparedStatement.executeQuery();
        while (results.next()) {
            emp.setEmpNo(results.getLong("empno"));
            emp.setEname(results.getString("ename"));
            emp.setEfirst(results.getString("efirst"));
            emp.setJob(results.getString("job"));
            emp.setMgr(this.find(results.getInt("mgr")));
            emp.setHireDate(Date.valueOf(results.getString("hiredate")));
            emp.setSal(results.getInt("sal"));
            emp.setComm(results.getInt("comm"));
            emp.setTel(results.getInt("tel"));
            DAO<Dept> deptDAO = new DeptDAO(connect);
            emp.setDepartment(deptDAO.find(results.getInt("deptno")));
        }
        return emp;
    }

    @Override
    public boolean create(Emp object) {
        return false;
    }

    @Override
    public boolean update(Emp object) {
        return false;
    }

    @Override
    public boolean delete(Emp object) {
        return false;
    }
}
