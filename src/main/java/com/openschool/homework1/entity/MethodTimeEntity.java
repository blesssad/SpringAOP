package com.openschool.homework1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Table(name = "methodtime", schema = "public", catalog = "t1school")
@Getter
@Setter
public class MethodTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "method_name", nullable = false, length = 255)
    private String methodName;
    @Basic
    @Column(name = "class_name", nullable = false, length = 255)
    private String className;
    @Basic
    @Column(name = "call_count", nullable = false)
    private int callCount;
    @Basic
    @Column(name = "work_time", nullable = false)
    private long workTime;

    public MethodTimeEntity(String methodName, String className, int callCount, long workTime){
        this.methodName = methodName;
        this.className = className;
        this.callCount = callCount;
        this.workTime = workTime;
    }
}
