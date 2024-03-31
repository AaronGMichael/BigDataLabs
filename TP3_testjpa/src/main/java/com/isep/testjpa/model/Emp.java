package com.isep.testjpa.model;

import javax.persistence.*;

@Entity
public class Emp {
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "monitor_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long empno;
    @Column(name = "ename")
    private String ename;
    @Column(name = "efirst")
    private String efirst;
    @Column(name = "job")
    private String job;
    @Column(name = "mgr")
    private Long mgr;
    @Column(name = "sal")
    private Long sal;
    public Long getEmpno() {
        return empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEfirst() {
        return efirst;
    }

    public void setEfirst(String efirst) {
        this.efirst = efirst;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Long getMgr() {
        return mgr;
    }

    public void setMgr(Long mgr) {
        this.mgr = mgr;
    }

    public Long getSal() {
        return sal;
    }

    public void setSal(Long sal) {
        this.sal = sal;
    }

    public void setEmpno(Long empno) {
        this.empno = empno;
    }
}