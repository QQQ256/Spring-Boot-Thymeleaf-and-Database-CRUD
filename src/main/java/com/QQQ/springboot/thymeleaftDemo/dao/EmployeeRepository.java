package com.QQQ.springboot.thymeleaftDemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.QQQ.springboot.thymeleaftDemo.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer>{//key:employee value:primary key
    //啥也不用写，这个API会提供那些CRUD的实现。
    
    //写个function，可以sort by lastName
    //这个是JPA MAGIC！！！！！！！！！！
    public List<Employee> findAllByOrderByLastNameAsc();
}
