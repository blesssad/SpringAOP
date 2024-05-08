package com.openschool.homework1.repository;

import com.openschool.homework1.entity.MethodTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeRepository extends JpaRepository<MethodTimeEntity, Long> {
    MethodTimeEntity findByMethodNameAndClassName(String methodName, String className);

    List<MethodTimeEntity> findAllByClassName(String className);
    List<MethodTimeEntity> findAllByMethodName(String methodName);

    boolean existsByMethodNameAndClassName(String methodName, String className);
}
