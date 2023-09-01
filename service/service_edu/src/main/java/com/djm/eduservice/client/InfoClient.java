package com.djm.eduservice.client;

import com.djm.commonutils.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;


/**
 * @author djm
 * @create 2021-10-04 12:57
 */
@FeignClient(name = "service-ucenter")
@Component
public interface InfoClient {
    @GetMapping("/educenter/member/ucenterservice/member/getUcenterPay/{memberId}")
    public UcenterMemberVo getUcenterPay(@PathVariable("memberId") String memberId);
    //好像传不了request,过大
    @GetMapping("getMemberInfo1")
    public UcenterMemberVo getMemberInfo1(HttpServletRequest request);
}
