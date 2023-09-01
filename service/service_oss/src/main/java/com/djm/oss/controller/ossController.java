package com.djm.oss.controller;

import com.djm.commonutils.R;
import com.djm.oss.service.ossservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author djm
 * @create 2021-09-19 16:07
 */
//@CrossOrigin
@RestController
@RequestMapping("/eduoss/fileoss")
public class ossController {
    @Autowired
    ossservice ossservice;

    @PostMapping
    public R uploadFileAvatar(MultipartFile file){
        String url = ossservice.upload(file);
        return R.ok().data("url",url);
    }
}
