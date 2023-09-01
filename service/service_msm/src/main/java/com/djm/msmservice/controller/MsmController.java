package com.djm.msmservice.controller;

import com.djm.commonutils.R;
import com.djm.msmservice.service.MsmService;
import com.djm.msmservice.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author djm
 * @create 2021-09-28 21:03
 */
@RestController
@RequestMapping("/edumsm/msm")
//@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone) {
        System.out.println("...........................................................发送");
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return R.ok();
        }
        code = RandomUtil.getFourBitRandom();
        boolean isSend = msmService.send(code,phone);
        if (isSend){
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        }
        return R.error().message("短信发送失败");
    }



}