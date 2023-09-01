package com.djm.msmservice.service;

import org.aspectj.apache.bcel.classfile.Code;

import java.util.Map;

public interface MsmService {
    //发送短信的方法
    boolean send(String Code, String phone);
}
