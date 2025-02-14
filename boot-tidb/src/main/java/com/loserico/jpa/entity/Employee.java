package com.loserico.jpa.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee")
@Data
public class Employee {
    
    /**
     * 员工ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    /**
     * 员工姓名
     */
    @Column(name = "full_name", nullable = false)
    private String fullName;
    
    /**
     * 员工性别
     */
    @Column(name = "gender", nullable = false)
    private String gender;
    
    /**
     * 员工年龄
     */
    @Column(name = "age", nullable = false)
    private int age;
    
    /**
     * 员工工号
     */
    @Column(name = "empId", nullable = false, unique = true)
    private String empId;
    
    /**
     * 员工薪水
     */
    @Column(name = "salary", nullable = false)
    private double salary;
    
    /**
     * 用户名（唯一）
     */
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    /**
     * 员工地址
     */
    @Column(name = "address")
    private String address;
    
    /**
     * 创建时间
     */
    @Column(name = "createTime", columnDefinition = "DATETIME")
    private Date createTime;
    
    /**
     * 修改时间
     */
    @Column(name = "updateTime", columnDefinition = "DATETIME")
    private Date updateTime;
    
    /**
     * 是否删除
     */
    @Column(name = "deleted")
    private boolean deleted;
    
    // Constructors and other methods
    
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + fullName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", empId='" + empId + '\'' +
                ", salary=" + salary +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                '}';
    }
}
