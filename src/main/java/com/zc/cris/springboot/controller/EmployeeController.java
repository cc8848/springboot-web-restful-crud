package com.zc.cris.springboot.controller;

import com.zc.cris.springboot.dao.DepartmentDao;
import com.zc.cris.springboot.dao.EmployeeDao;
import com.zc.cris.springboot.entities.Department;
import com.zc.cris.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    /**
     * @return java.lang.String
     * @Author zc-cris
     * @Description 来到展示员工数据的首页
     * @Param [model]
     **/
    @GetMapping("/emps")
    public String emps(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps", employees);
//        employees.forEach(System.out::println);
        return "emp/list";
    }

    /**
     * @return java.lang.String
     * @Author zc-cris
     * @Description 去到添加员工的页面
     * @Param [Model]
     **/
    @GetMapping("/emp")
    public String toAddEmpPage(Model model) {
        // 部门数据需要从数据库查询出来展示在添加员工页面
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/addEmp";
    }

    /**
     * @return java.lang.String
     * @Author zc-cris
     * @Description 添加员工信息成功并来到员工信息展示首页
     * @Param [employee]
     **/
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        System.out.println(employee);
        employeeDao.save(employee);

        // redirect: 表示重定向到一个地址 /表示当前项目路径
        // forward:表示转发到一个地址
        return "redirect:/emps";    // 默认是get请求
//        return "forward:/emps"; 默认是post请求
    }

    /**
     * @return java.lang.String
     * @Author zc-cris
     * @Description 去到修改员工数据的页面（页面回显）
     * @Param [id, model]
     **/
    @GetMapping("/emp/{id}")
    public String toUpdateEmpPage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.get(id);
        Collection<Department> departments = departmentDao.getDepartments();
//        System.out.println(employee);
        model.addAttribute("emp", employee);
        model.addAttribute("depts", departments);

        // 添加员工和修改员工页面二合一
        return "emp/addEmp";
    }

    /**
     * @return java.lang.String
     * @Author zc-cris
     * @Description 将修改后的数据保存到数据库并重定向到数据展示首页
     * @Param [employee]
     **/
    @PutMapping("/emp")
    public String updateEmp(Employee employee) {
//        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * @return java.lang.String
     * @Author zc-cris
     * @Description 使用delete 方式删除指定员工数据
     * @Param [id]
     **/
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }


}
