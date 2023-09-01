package com.djm.educenter.mapper;

import com.djm.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author djm
 * @since 2021-09-28
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {
    @Select("SELECT COUNT(*) FROM ucenter_member uc WHERE DATE(uc.gmt_create)=#{day}")
    Integer countRegisterDay(String day);
}
