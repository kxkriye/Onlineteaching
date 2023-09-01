package com.djm.eduservice.controller;


import com.djm.commonutils.R;
import com.djm.eduservice.client.VodClient;
import com.djm.eduservice.entity.EduChapter;
import com.djm.eduservice.entity.EduVideo;
import com.djm.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author djm
 * @since 2021-09-20
 */
@RestController
@RequestMapping("/eduservice/video")
//@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;
    @Autowired
    VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok();
    }

    //删除小节
    // TODO 后面这个方法需要完善：删除小节时候，同时把里面视频删除
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
        EduVideo byId = videoService.getById(id);
        vodClient.removeAlyVideo(byId.getVideoSourceId());
        videoService.removeById(id);
        return R.ok();
    }
    @GetMapping("getVideoInfo/{VideoId}")
    public R getChapterInfo(@PathVariable String VideoId) {
        EduVideo byId = videoService.getById(VideoId);
        return R.ok().data("video",byId);
    }

    //修改小节 TODO
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody  EduVideo eduVideo){
        videoService.updateById(eduVideo);
        return R.ok();
    }

}

