1、项目描述

（1）
在线教育系统，分为前台网站系统和后台运营平台，B2C模式。
前台用户系统包括课程、讲师、问答、文章几大大部分，使用了微服务技术架构，前后端分离开发。
后端的主要技术架构是：SpringBoot + SpringCloud + MyBatis-Plus + HttpClient + MySQL + Maven+EasyExcel+ nginx
前端的架构是：Node.js + Vue.js +element-ui+NUXT+ECharts

其他涉及到的中间件包括Redis、阿里云OSS、阿里云视频点播
业务中使用了ECharts做图表展示，使用EasyExcel完成分类批量添加、注册分布式单点登录使用了JWT

（2）
项目前后端分离开发，后端采用SpringCloud微服务架构，持久层用的是MyBatis-Plus，微服务分库设计，使用Swagger生成接口文档
接入了阿里云视频点播、阿里云OSS。
系统分为前台用户系统和后台管理系统两部分。
前台用户系统包括：首页、课程、名师、问答、文章。
后台管理系统包括：讲师管理、课程分类管理、课程管理、统计分析、Banner管理、订单管理、权限管理等功能。




2.测试要求：

首页和视频详情页qps单机qps要求 2000+
经常用每秒查询率来衡量域名系统服务器的机器的性能，其即为QPS
QPS = 并发量 / 平均响应时间





3.前后端联调经常遇到的问题：

      1、请求方式post、get
      2、json、x-wwww-form-urlencoded混乱的错误
      3、后台必要的参数，前端省略了
      4、数据类型不匹配
      5、空指针异常
      6、分布式系统中分布式id生成器生成的id 长度过大（19个字符长度的整数），js无法解析（js智能解析16个长度：2的53次幂）
          id策略改成 ID_WORKER_STR

前后端分离项目中的跨域问题是如何解决的：
后端服务器配置：我们的项目中是通过Spring注解解决跨域的 @CrossOrigin
也可以使用nginx反向代理、httpClient、网关





4.分布式系统的id生成策略

https://www.cnblogs.com/haoxinyue/p/5208136.html




5.前端渲染和后端渲染

前端渲染是返回json给前端，通过javascript将数据绑定到页面上
后端渲染是在服务器端将页面生成直接发送给服务器，有利于SEO的优化





6.系统架构图

![架构图](https://images.gitee.com/uploads/images/2021/1104/224357_96572d19_9084954.png "屏幕截图.png")


