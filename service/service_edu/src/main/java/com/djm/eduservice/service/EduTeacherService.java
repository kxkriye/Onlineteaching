package com.djm.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.djm.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author djm
 * @since 2021-09-16
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String,Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
