package com.openschool.homework1.controller;

import com.openschool.homework1.entity.MethodTimeEntity;
import com.openschool.homework1.entity.TimeDto;
import com.openschool.homework1.service.TimeInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/time")
public class TrackTimeController {
    private final TimeInformationService timeInformationService;

    @GetMapping("/{className}/{methodName}")
    public ResponseEntity<MethodTimeEntity> getMethodTimeEntity(
            @PathVariable("className") String className,
            @PathVariable("methodName") String methodName) {
        MethodTimeEntity methodTimeEntity = timeInformationService.getByMethodNameAndClassName(methodName, className);
        if (methodTimeEntity != null) {
            return ResponseEntity.ok(methodTimeEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{methodName}")
    public ResponseEntity<List<MethodTimeEntity>> getMethodTimeEntitiesByClassName(
            @PathVariable("methodName") String methodName) {
        List<MethodTimeEntity> methodTimeEntities = timeInformationService.getAllMethodTime(methodName);
        if (!methodTimeEntities.isEmpty()) {
            return ResponseEntity.ok(methodTimeEntities);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/avgtime/{className}/{methodName}")
    public ResponseEntity<TimeDto<Float>> getAvgMethodTime(
            @PathVariable("methodName") String methodName,
            @PathVariable("className") String className) {
        TimeDto<Float> avgMethodTime = timeInformationService.getAvgMethodTime(methodName, className);
        return ResponseEntity.ok(avgMethodTime);
    }

    @GetMapping("/total/{className}")
    public ResponseEntity<TimeDto<Long>> getTimeOfAllMethodsInClass(
            @PathVariable("className") String className) {
        TimeDto<Long> totalTime = timeInformationService.getTimeOfAllMethodsInClass(className);
        return ResponseEntity.ok(totalTime);
    }

}
