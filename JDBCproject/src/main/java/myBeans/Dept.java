package myBeans;

public class Dept {
    private int deptno;
    private String dname;
    private String loc;

    public int getDeptno() {
        return deptno;
    }

    public String getDname() {
        return dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        String pat = null;
        pat = "Dept No: "+this.getDeptno() +"\nDepartment Name: "+this.getDname()+"\nLocation: "+this.getLoc();
        return pat;
    }
}
