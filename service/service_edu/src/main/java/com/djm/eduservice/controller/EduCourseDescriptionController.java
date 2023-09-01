package com.djm.eduservice.controller;


import com.djm.eduservice.entity.EduCourseDescription;
import com.djm.eduservice.service.EduCourseDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程简介 前端控制器
 * </p>
 *
 * @author djm
 * @since 2021-09-20
 */
@RestController
@RequestMapping("/eduservice/edu-course-description")
public class EduCourseDescriptionController {
    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;
    @PostMapping
    public void asd(@RequestBody EduCourseDescription eduCourseDescription){
        eduCourseDescriptionService.save(eduCourseDescription);
    }
}

