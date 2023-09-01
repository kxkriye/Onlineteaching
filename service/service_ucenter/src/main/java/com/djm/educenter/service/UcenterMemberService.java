package com.djm.educenter.service;

import com.djm.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.djm.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author djm
 * @since 2021-09-28
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);

    UcenterMember getOpenIdMember(String openid);

    Integer countRegisterDay(String day);
}
