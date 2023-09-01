package com.djm.eduservice.service;

import com.djm.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.djm.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author djm
 * @since 2021-09-19
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService subjectService);

    List<OneSubject> getAllOneTwoSubject();
}
