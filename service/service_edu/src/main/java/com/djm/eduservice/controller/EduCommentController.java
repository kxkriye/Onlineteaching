package com.djm.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.djm.commonutils.JwtUtils;
import com.djm.commonutils.R;
import com.djm.commonutils.UcenterMemberVo;
import com.djm.eduservice.client.InfoClient;
import com.djm.eduservice.entity.EduComment;
import com.djm.eduservice.service.EduCommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author djm
 * @since 2021-10-04
 */
@RestController
@RequestMapping("/eduservice/comment")
//@CrossOrigin
public class EduCommentController {
    @Autowired
    InfoClient infoClient;
    @Autowired
    EduCommentService eduCommentService;
    @PostMapping("/auth/save")
    public R addComment(@RequestBody EduComment comment,HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberId)) {
            return R.error().code(28004).message("请登录");
        }
        UcenterMemberVo ucenterPay = infoClient.getUcenterPay(memberId);
        comment.setMemberId(ucenterPay.getId());
        comment.setAvatar(ucenterPay.getAvatar());
        comment.setNickname(ucenterPay.getNickname());
        boolean save = eduCommentService.save(comment);
        if(save){
            return R.ok();
        }
        return R.error();
    }
    //根据课程id查询评论列表
    @ApiOperation(value = "评论分页列表")
    @GetMapping("{page}/{limit}")
    public R index(
            @PathVariable Long page,
            @PathVariable Long limit,String courseId) {
        Page<EduComment> pageParam = new Page<>(page, limit);

        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.orderByDesc("id");

        eduCommentService.page(pageParam,wrapper);
        List<EduComment> commentList = pageParam.getRecords();

        Map<String, Object> map = new HashMap<>();
        map.put("items", commentList);
        map.put("current", pageParam.getCurrent());
        map.put("pages", pageParam.getPages());
        map.put("size", pageParam.getSize());
        map.put("total", pageParam.getTotal());
        map.put("hasNext", pageParam.hasNext());
        map.put("hasPrevious", pageParam.hasPrevious());
        return R.ok().data(map);
    }
}

