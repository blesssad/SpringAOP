package com.openschool.homework1.service;

import com.openschool.homework1.entity.EntityNotExistException;
import com.openschool.homework1.entity.MethodTimeEntity;
import com.openschool.homework1.entity.TimeDto;
import com.openschool.homework1.repository.TimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class TimeInformationService {
    private final TimeRepository trackTimeRepository;

    public MethodTimeEntity getByMethodNameAndClassName(String methodName, String className){
        MethodTimeEntity methodTimeEntity = trackTimeRepository.findByMethodNameAndClassName(methodName,className);
        if (methodTimeEntity == null){
            throw new EntityNotExistException("Такого метода не существует!");
        }

        return methodTimeEntity;
    }

    public TimeDto<Long> getTimeOfAllMethodsInClass(String className){
        AtomicLong time = new AtomicLong(0);
        trackTimeRepository.findAllByClassName(className).forEach(method -> time.addAndGet(method.getWorkTime()));

        return new TimeDto<>(time.get());
    }

    public TimeDto<Float> getAvgMethodTime(String methodName, String className){
        MethodTimeEntity methodTimeEntity = trackTimeRepository.findByMethodNameAndClassName(methodName, className);
        if (methodTimeEntity == null){
            throw new EntityNotExistException("Такого метода не существует!");
        }

        return new TimeDto<>((float) methodTimeEntity.getWorkTime() / methodTimeEntity.getCallCount());
    }

    public List<MethodTimeEntity> getAllMethodTime(String methodName){
        List<MethodTimeEntity> methodTimeEntities = trackTimeRepository.findAllByMethodName(methodName);
        if (methodTimeEntities.size() == 0){
            throw new EntityNotExistException("Таких методов не существует!");
        }

        return trackTimeRepository.findAllByMethodName(methodName);
    }
}
