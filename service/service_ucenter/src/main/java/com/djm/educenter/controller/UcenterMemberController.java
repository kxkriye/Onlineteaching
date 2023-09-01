package com.djm.educenter.controller;


import com.djm.commonutils.JwtUtils;
import com.djm.commonutils.R;
import com.djm.commonutils.UcenterMemberVo;
import com.djm.educenter.entity.UcenterMember;
import com.djm.educenter.entity.vo.RegisterVo;
import com.djm.educenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author djm
 * @since 2021-09-28
 */
@RestController
@RequestMapping("/educenter/member")
//@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member) {
        //member对象封装手机号和密码
        //调用service方法实现登录
        //返回token值，使用jwt生成
        String token = memberService.login(member);
        return R.ok().data("token",token);
    }
    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }
    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }
    //根据token获取用户信息
    @GetMapping("getMemberInfo1")
    public UcenterMemberVo getMemberInfo1(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        if(StringUtils.isEmpty(memberId)) {
          return null;
        }
        UcenterMember member = memberService.getById(memberId);
        UcenterMemberVo ucenterMemberVo = new UcenterMemberVo();
        BeanUtils.copyProperties(member,ucenterMemberVo);
        return ucenterMemberVo;
    }
    @GetMapping("/ucenterservice/member/getUcenterPay/{memberId}")
    public UcenterMemberVo getUcenterPay(@PathVariable("memberId") String memberId){
        UcenterMember member = memberService.getById(memberId);
        UcenterMemberVo ucenterMemberVo = new UcenterMemberVo();
        BeanUtils.copyProperties(member,ucenterMemberVo);
        return ucenterMemberVo;
    }
    @GetMapping("countRegister/{day}")
    public R countRegister(@PathVariable("day") String day){
        Integer count = memberService.countRegisterDay(day);
        return R.ok().data("countRegister",count);
    }
}

