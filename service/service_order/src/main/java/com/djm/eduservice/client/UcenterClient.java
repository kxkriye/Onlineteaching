package com.djm.eduservice.client;

import com.djm.commonutils.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    @GetMapping("/educenter/member/ucenterservice/member/getUcenterPay/{memberId}")
    public UcenterMemberVo getUcenterPay(@PathVariable("memberId") String memberId);
}
