package com.isep.testjpa.controller;

import com.isep.testjpa.repository.EmpRepository;
import com.isep.testjpa.model.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SimpleController {

    @Autowired
    private EmpRepository empRepository;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String hello(@Param("name") String name) {
        return "Hello " + name;
    }

    @RequestMapping(value="/employees", method= RequestMethod.GET)
    public List<Emp> getEmployees() {
        return empRepository.findAll();
    }

    @RequestMapping(value = "/employees/{empid}", method = RequestMethod.GET)
    public Emp getEmployee(@PathVariable Long empid){
        return empRepository.findById(empid).get();
    }

    @RequestMapping(value = "/employees/{empid}", method = RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable Long empid){
        empRepository.deleteById(empid);
    }

    @PutMapping(value = "/employees/{empid}")
    public void updateEmployee(@RequestBody Emp newEmployee, @PathVariable Long empid) {
        Emp updatedEmp = empRepository.findById(empid).map(Emp -> {
            Emp.setEfirst(newEmployee.getEfirst());
            Emp.setEname(newEmployee.getEname());
            Emp.setMgr(newEmployee.getMgr());
            Emp.setJob(newEmployee.getJob());
            return empRepository.save(Emp);
        }).orElseGet(() -> {
            newEmployee.setEmpno(empid);
            return empRepository.save(newEmployee);
        });
    }

    @PatchMapping(value = "/employees/{empid}")
    public Emp updatePatchEmployee(@RequestBody Emp partialUpdate, @PathVariable Long empid) throws Exception {
        return empRepository.findById(empid).map(Emp -> {
            if (partialUpdate.getEfirst() != null) Emp.setEfirst(partialUpdate.getEfirst());
            if (partialUpdate.getEname() != null) Emp.setEname(partialUpdate.getEname());
            if (partialUpdate.getSal() != null) Emp.setSal(partialUpdate.getSal());
            if (partialUpdate.getJob() != null) Emp.setJob(partialUpdate.getJob());
            return empRepository.save(Emp);
        }).orElseThrow(() -> new Exception("Employee not Found!"));
    }

    @PostMapping(value="/employees")
    public Emp addEmployee(@RequestBody Emp emp) {
        return empRepository.save(emp);
    }

}
