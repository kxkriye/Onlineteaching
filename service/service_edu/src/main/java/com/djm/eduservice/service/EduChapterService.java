package com.djm.eduservice.service;

import com.djm.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.djm.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author djm
 * @since 2021-09-20
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
