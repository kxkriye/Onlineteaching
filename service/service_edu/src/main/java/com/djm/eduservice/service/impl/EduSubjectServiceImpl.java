package com.djm.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.djm.eduservice.entity.EduSubject;
import com.djm.eduservice.entity.excel.SubjectData;
import com.djm.eduservice.entity.subject.OneSubject;
import com.djm.eduservice.entity.subject.TwoSubject;
import com.djm.eduservice.listener.SubjectExcelListener;
import com.djm.eduservice.mapper.EduSubjectMapper;
import com.djm.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author djm
 * @since 2021-09-19
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        //2 查询所有二级分类  parentid != 0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        //创建list集合，用于存储最终封装数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        for (int i=0;i<oneSubjectList.size();i++){
            EduSubject eduSubject = oneSubjectList.get(i);
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject,oneSubject);
            finalSubjectList.add(oneSubject);
            List<TwoSubject> list = new ArrayList<>();
            for (int i1=0;i1<twoSubjectList.size();i1++){
                EduSubject eduSubject1 = twoSubjectList.get(i1);
                TwoSubject twoSubject = new TwoSubject();
                BeanUtils.copyProperties(eduSubject1,twoSubject);
                if(eduSubject1.getParentId().equals(eduSubject.getId())){
                 list.add(twoSubject);
                }
            }
            oneSubject.setChildren(list);
        }
        return finalSubjectList;
    }
}
