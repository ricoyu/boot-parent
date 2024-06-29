package com.loserico.jpa.service;

import com.loserico.common.spring.annotation.PostInitialize;
import com.loserico.jpa.entity.Department;
import com.loserico.orm.dao.CriteriaOperations;
import com.loserico.orm.predicate.Querys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class JpaService {

    @Autowired
    private CriteriaOperations criteriaOperations;


    @PostInitialize
    public Department findDepartments() {
        List<Department> departments = criteriaOperations.find(Department.class, Querys.gt("id", 1)
                .eq("deptName", "生产部"));
        departments.forEach(department -> {
            log.info("{}", department);
        });

        return departments.get(0);
    }
}
