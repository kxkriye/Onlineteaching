package com.djm.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.djm.eduservice.entity.EduSubject;
import com.djm.eduservice.entity.excel.SubjectData;
import com.djm.eduservice.service.EduSubjectService;
import com.djm.servicebase.GuliException;

/**
 * @author djm
 * @create 2021-09-19 22:04
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService eduSubjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService eduSubjectService) {

        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null) {
            throw new GuliException(20001,"文件数据为空");
        }
        EduSubject eduSubject = this.existOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        if (eduSubject==null){
            eduSubject = new EduSubject();
            eduSubject.setTitle(subjectData.getOneSubjectName());
            eduSubject.setParentId("0");
            eduSubjectService.save(eduSubject);
        }
        String pid=eduSubject.getId();
        EduSubject eduTwoSubject = this.existTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(),pid);
        if(eduTwoSubject==null){
            eduTwoSubject  = new EduSubject();
            eduTwoSubject.setTitle(subjectData.getTwoSubjectName());
            eduTwoSubject.setParentId(pid);
            eduSubjectService.save(eduTwoSubject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
    private EduSubject existOneSubject(EduSubjectService subjectService, String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id","0");
        return  subjectService.getOne(queryWrapper);
    }
    private EduSubject existTwoSubject(EduSubjectService subjectService, String name,String pid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",pid);
        return  subjectService.getOne(queryWrapper);
    }
}
