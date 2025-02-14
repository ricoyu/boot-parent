package com.loserico.jpa.service;

import com.loserico.jpa.entity.Department;
import com.loserico.orm.dao.CriteriaOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class JpaService {

    @Autowired
    private CriteriaOperations criteriaOperations;


    public List<Department> findDepartments() {
        List<Department> departments = criteriaOperations.find(Department.class);
        departments.forEach(department -> {
            log.info("{}", department);
        });

        return departments;
    }
}
