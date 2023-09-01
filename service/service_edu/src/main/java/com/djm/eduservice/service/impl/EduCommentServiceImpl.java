package com.djm.eduservice.service.impl;

import com.djm.commonutils.R;
import com.djm.commonutils.UcenterMemberVo;
import com.djm.eduservice.client.InfoClient;
import com.djm.eduservice.entity.EduComment;
import com.djm.eduservice.entity.EduCourse;
import com.djm.eduservice.mapper.EduCommentMapper;
import com.djm.eduservice.service.EduCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djm.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author djm
 * @since 2021-10-04
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {

}
