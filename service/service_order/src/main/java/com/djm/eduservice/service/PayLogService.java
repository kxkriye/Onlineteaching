package com.djm.eduservice.service;

import com.djm.eduservice.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author djm
 * @since 2021-10-06
 */
public interface PayLogService extends IService<PayLog> {

    Map createNatvie(String orderNo);

    Map<String,String> queryPayStatus(String orderNo);

    void updateOrdersStatus(Map<String, String> map);
}
