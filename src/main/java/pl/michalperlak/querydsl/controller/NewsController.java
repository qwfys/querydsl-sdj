///*
// * www.qwfys.com Inc.
// * Copyright (c) 2004- 2020 All Rights Reserved.
// */
//
//package pl.michalperlak.querydsl.controller;
//
//import com.xtwj.dph.api.ops.ai.NewsReadAi;
//import com.xtwj.dph.api.ops.ao.NewsAo;
//import com.xtwj.dph.api.ops.ao.NewsExtAo;
//import com.xtwj.dph.biz.user.bi.NewsReadBi;
//import com.xtwj.dph.biz.user.bo.NewsBo;
//import com.xtwj.dph.biz.user.service.spec.NewsService;
//import com.xtwj.dph.common.base.response.ResponseObject;
//import com.xtwj.dph.common.base.response.ResponseObjectHelp;
//import com.xtwj.dph.common.biz.bo.PageBo;
//import com.xtwj.dph.common.mvc.ao.PageAo;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.apache.dubbo.config.annotation.Reference;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Api(tags = "新闻管理")
//@RestController
//public class NewsController {
//
//    @Autowired
//    private NewsService newsService;
//
//    @ApiOperation(value = "获取新闻列表", httpMethod = "POST")
//    @PostMapping("news/list")
//    public ResponseObject<PageAo<NewsExtAo>> list(NewsReadAi ai) {
//        NewsReadBi bi = new NewsReadBi();
//        BeanUtils.copyProperties(ai, bi);
//        PageBo<NewsBo> pageBo = newsService.findAll(bi);
//        List<NewsBo> bos = pageBo.getList();
//        if (!CollectionUtils.isEmpty(bos)) {
//            List<NewsExtAo> aos = bos.stream().map(bo -> {
//                NewsExtAo ao = new NewsExtAo();
//                BeanUtils.copyProperties(bo, ao);
//                return ao;
//            }).collect(Collectors.toList());
//            return ResponseObjectHelp.genSuccessObjectResult(PageAo.of(aos, pageBo.getTotal()));
//        } else {
//            return ResponseObjectHelp.genErrorObjectResult("没有查询到相关数据");
//        }
//    }
//
//    @ApiOperation(value = "查看新闻", httpMethod = "GET")
//    @GetMapping("news/detail")
//    public ResponseObject<NewsExtAo> detail(@RequestParam Long id) {
//        return Optional.ofNullable(newsService.findById(id))
//                .map(bo -> {
//                    NewsExtAo ao = new NewsExtAo();
//                    BeanUtils.copyProperties(bo, ao);
//                    return ResponseObjectHelp.genSuccessObjectResult(ao);
//                })
//                .orElse(ResponseObjectHelp.genErrorObjectResult("没有查到相关数据"));
//        //return null;
//    }
//
//    @ApiOperation(value = "添加新闻", httpMethod = "POST")
//    @PostMapping("news/add")
//    public ResponseObject<String> add(NewsAo ao) {
//        ao.setCreatedTime(LocalDateTime.now());
//        NewsBo bo = new NewsBo();
//        BeanUtils.copyProperties(ao, bo);
//        newsService.save(bo);
//        return ResponseObjectHelp.genSuccessObjectResult("添加成功。");
//    }
//
//    @ApiOperation(value = "更新新闻", httpMethod = "POST")
//    @PostMapping("news/update")
//    public ResponseObject<String> update(NewsAo ao) {
//        if (newsService.existsById(ao.getId())) {
//            NewsBo bo = new NewsBo();
//            BeanUtils.copyProperties(ao, bo);
//            newsService.save(bo);
//            return ResponseObjectHelp.genSuccessObjectResult("更新成功。");
//        } else {
//            return ResponseObjectHelp.genErrorObjectResult("数据不存在");
//        }
//    }
//
//    @ApiOperation(value = "删除新闻", httpMethod = "POST")
//    @PostMapping("news/remove")
//    public ResponseObject<String> add(Long id) {
//        if (newsService.existsById(id)) {
//            newsService.remove(id);
//            return ResponseObjectHelp.genSuccessObjectResult("删除成功。");
//        } else {
//            return ResponseObjectHelp.genErrorObjectResult("数据不存在");
//        }
//    }
//}
