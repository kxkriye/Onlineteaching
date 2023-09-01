package com.djm.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.djm.eduservice.client.VodClient;
import com.djm.eduservice.entity.EduVideo;
import com.djm.eduservice.mapper.EduVideoMapper;
import com.djm.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author djm
 * @since 2021-09-20
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;
    @Override
    public void removeVideoByCourseId(String courseId) {

        QueryWrapper<EduVideo> queryWrapper =new QueryWrapper();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.select("video_source_id");
        List<EduVideo> list = baseMapper.selectList(queryWrapper);
        List<String> videoIds =new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            EduVideo eduVideo = list.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)) {
                //放到videoIds集合里面
                videoIds.add(videoSourceId);
            }
        }
        if(videoIds.size()>0) {
            vodClient.deleteBatch(videoIds);
        }
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
