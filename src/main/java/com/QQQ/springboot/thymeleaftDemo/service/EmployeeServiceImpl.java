package com.QQQ.springboot.thymeleaftDemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QQQ.springboot.thymeleaftDemo.dao.EmployeeRepository;
import com.QQQ.springboot.thymeleaftDemo.entity.Employee;


//遇到问题：Consider defining a bean of type 'com.QQQ.springboot.crudDemo.service.EmployeeService' in your configuration.
//解决方案：@Service没加，得记得加啊！damn！
@Service
public class EmployeeServiceImpl implements EmployeeService {

    
    private EmployeeRepository employeeRepository;
    
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Override
//    @Transactional 这个也不用写，JPA提供这个的
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee findById(int id) {
        //返回的值类型是optional，注意这里的写法啊！
        //Optional是一个容器对象，可以包含也可以不包含非null值。
        //本质上，Optional是一个包装器类，其中包含对其他对象的引用。
        Optional<Employee> result = employeeRepository.findById(id);
        
        Employee theEmployee = null;
        if(result.isPresent()) {
            theEmployee =  result.get();
        }else {
            throw new RuntimeException("Employee id not found " + id);
        }
        return theEmployee;
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
