package com.loserico.jpa.entity;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import static jakarta.persistence.GenerationType.IDENTITY;

/**
 *
 * <p>
 * Copyright: Copyright (c) 2024-02-23 16:34
 * <p>
 * Company: Sexy Uncle Inc.
 * <p>

 * @author Rico Yu  ricoyu520@gmail.com
 * @version 1.0
 */
@Data
@Entity(name = "dept")
public class Department {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "department_name")
    private String deptName;
}
