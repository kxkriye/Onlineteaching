package com.djm.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.djm.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.djm.eduservice.entity.frontvo.CourseFrontVo;
import com.djm.eduservice.entity.frontvo.CourseWebVo;
import com.djm.eduservice.entity.vo.CourseInfoVo;
import com.djm.eduservice.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author djm
 * @since 2021-09-20
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);

    Map<String,Object> getCourseFrontList(Page<EduCourse> pageParam, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
