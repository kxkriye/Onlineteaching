package com.djm.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author djm
 * @create 2021-09-19 16:06
 */
public interface ossservice {
    String upload(MultipartFile file);
}
