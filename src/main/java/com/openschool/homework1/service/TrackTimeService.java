package com.openschool.homework1.service;

import com.openschool.homework1.entity.MethodTimeEntity;
import com.openschool.homework1.repository.TimeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrackTimeService {
    private final TimeRepository trackTimeRepository;

    @Transactional
    public void saveMethod(String methodName, String className, Long time){
        MethodTimeEntity methodTimeEntity;

        if (trackTimeRepository.existsByMethodNameAndClassName(methodName, className)){
            methodTimeEntity = trackTimeRepository.findByMethodNameAndClassName(methodName, className);
            int callCount = methodTimeEntity.getCallCount();
            long workTime = methodTimeEntity.getWorkTime();

            methodTimeEntity.setWorkTime(workTime + time);
            methodTimeEntity.setCallCount(callCount + 1);
        }
        else{
            methodTimeEntity = new MethodTimeEntity(methodName, className, 1, time);
        }

        trackTimeRepository.save(methodTimeEntity);
    }
}
