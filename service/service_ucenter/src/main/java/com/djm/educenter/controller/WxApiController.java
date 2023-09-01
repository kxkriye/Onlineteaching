package com.djm.educenter.controller;

import com.djm.commonutils.JwtUtils;
import com.djm.educenter.entity.UcenterMember;
import com.djm.educenter.service.UcenterMemberService;
import com.djm.educenter.utils.ConstantWxUtils;
import com.djm.educenter.utils.HttpClientUtils;
import com.djm.servicebase.GuliException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @author djm
 * @create 2021-09-29 21:28
 */
//@CrossOrigin
@Controller  //只是请求地址，不需要返回数据
@RequestMapping("/api/ucenter/wx")
public class WxApiController {
    @Autowired
    private UcenterMemberService memberService;
    //2 获取扫描人信息，添加数据
    @GetMapping("callback")
    public String callback(String code, String state) {
        try {
            //1 获取code值，临时票据，类似于验证码
            //2 拿着code请求 微信固定的地址，得到两个值 accsess_token 和 openid
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";
            //拼接三个参数 ：id  秘钥 和 code值
            String accessTokenUrl = String.format(
                    baseAccessTokenUrl,
                    ConstantWxUtils.WX_OPEN_APP_ID,
                    ConstantWxUtils.WX_OPEN_APP_SECRET,
                    code
            );
            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
            Gson gson = new Gson();
            HashMap mapAccessToken = gson.fromJson(accessTokenInfo, HashMap.class);
            String access_token = (String) mapAccessToken.get("access_token");
            String openid = (String) mapAccessToken.get("openid");
            UcenterMember member = memberService.getOpenIdMember(openid);
            if(member == null) {//memeber是空，表没有相同微信数据，进行添加

                //3 拿着得到accsess_token 和 openid，再去请求微信提供固定的地址，获取到扫描人信息
                //访问微信的资源服务器，获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                //拼接两个参数
                String userInfoUrl = String.format(
                        baseUserInfoUrl,
                        access_token,
                        openid
                );
                String userInfo = HttpClientUtils.get(userInfoUrl);
                HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
                String nickname = (String)userInfoMap.get("nickname");//昵称
                String headimgurl = (String)userInfoMap.get("headimgurl");//头像
                member = new UcenterMember();
                member.setOpenid(openid);
                member.setNickname(nickname);
                member.setAvatar(headimgurl);
                memberService.save(member);
            }else {
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                //拼接两个参数
                String userInfoUrl = String.format(
                        baseUserInfoUrl,
                        access_token,
                        openid
                );
                String userInfo = HttpClientUtils.get(userInfoUrl);
                HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
                String nickname = (String)userInfoMap.get("nickname");//昵称
                String headimgurl = (String)userInfoMap.get("headimgurl");//头像
                String sqlnickname = member.getNickname();
                String sqlavatar = member.getAvatar();
                if(!nickname.equals(sqlnickname)){
                    member.setNickname(nickname);
                    memberService.updateById(member);
                }
                if(!sqlavatar.equals(headimgurl)){
                    member.setAvatar(headimgurl);
                    memberService.updateById(member);
                }
            }
            //使用jwt根据member对象生成token字符串
            String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
            //最后：返回首页面，通过路径传递token字符串
            return "redirect:http://localhost:3000?token="+jwtToken;
        }
    catch (Exception e){
        throw new GuliException(20001,"登录失败");
    }
    }

            //1 生成微信扫描二维码
            @GetMapping("login")
            public String getWxCode() {
                //固定地址，后面拼接参数
//        String url = "https://open.weixin.qq.com/" +
//                "connect/qrconnect?appid="+ ConstantWxUtils.WX_OPEN_APP_ID+"&response_type=code";

                // 微信开放平台授权baseUrl  %s相当于?代表占位符
                String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                        "?appid=%s" +
                        "&redirect_uri=%s" +
                        "&response_type=code" +
                        "&scope=snsapi_login" +
                        "&state=%s" +
                        "#wechat_redirect";

                //对redirect_url进行URLEncoder编码
                String redirectUrl = ConstantWxUtils.WX_OPEN_REDIRECT_URL;
                try {
                    redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
                }catch(Exception e) {
                }

                //设置%s里面值
                String url = String.format(
                        baseUrl,
                        ConstantWxUtils.WX_OPEN_APP_ID,
                        redirectUrl,
                        "djm"
                );
                //重定向到请求微信地址里面
                return "redirect:"+url;
            }

}

