package com.QQQ.springboot.thymeleaftDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.QQQ.springboot.thymeleaftDemo.entity.Employee;
import com.QQQ.springboot.thymeleaftDemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    
    @Autowired
    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
        
    }
    
    @GetMapping("/list")
    public String listEmployee(Model theModel) {
        List<Employee> list = employeeService.findAll();
        theModel.addAttribute("employees",list);
        
        return "employees/list-employees";
    }
    
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        
        Employee thEmployee = new Employee();
        theModel.addAttribute("employee",thEmployee);
        
        return "employees/employee-form";
    }
    
    @PostMapping("save")
    public String save(@ModelAttribute("employee") Employee thEmployee) {
        
       employeeService.save(thEmployee);
        
       //POST/Redirect/Get pattern
        return "redirect:/employees/list";
    }
    
    //show form for update
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int id,
            Model theModel) {
        Employee theEmployee = employeeService.findById(id);
        
        theModel.addAttribute("employee", theEmployee);
        
        return "employees/employee-form";
    }
    
    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int id) {
        
        employeeService.deleteById(id);
        
        return "redirect:/employees/list";
    }
    
    @GetMapping("/search")
    public String search(@RequestParam("employeeName") String name, Model theModel) {
        List<Employee> theEmployees = employeeService.searchBy(name);
        
        theModel.addAttribute("employees", theEmployees);
        
        return "/employees/list-employees";
    }

}
