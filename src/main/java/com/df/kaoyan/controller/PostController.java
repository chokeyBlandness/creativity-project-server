package com.df.kaoyan.controller;

import com.alibaba.fastjson.JSON;
import com.df.kaoyan.dataobject.*;
import com.df.kaoyan.enums.ResultEnum;
import com.df.kaoyan.service.PostService;
import com.df.kaoyan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @PostMapping("/createPost")
    public @ResponseBody
    String createPost(@RequestBody String requestData) {
        Post newPost = JSON.parseObject(requestData, Post.class);
        if (newPost == null) {
            return JSON.toJSONString(ResultEnum.CREATE_POST_FAILED.getMessage());
        } else {
            newPost.setPostId(null);
            newPost.setCreateDate(new Date());
            if (newPost.getUserName() == null) {
                User foundUser = userService.findUserByUserId(newPost.getUserId());
                newPost.setUserName(foundUser.getUserName());
            }
            Post savedPost=postService.saveNewPost(newPost);
            return JSON.toJSONString(savedPost.getPostId());
        }
    }

    @GetMapping("/searchPost/{userId}")
    public @ResponseBody
    String searchPost(@PathVariable(name = "userId")Long userId){
        List<Post> responsePostList;
        if (userId == -1){
            responsePostList = postService.findAll();
        }else {
            responsePostList = postService.findPostListByUserId(userId);
        }
        return JSON.toJSONString(responsePostList);
    }

    @GetMapping("/searchPostDetail/{postId}")
    public @ResponseBody
    String searchPostDetail(@PathVariable(name = "postId")Long postId){
        PostDetail postDetail=new PostDetail();
        postDetail.setPost(postService.findPostByPostId(postId));
        postDetail.setCommentList(postService.findCommentListByPostId(postId));
        return JSON.toJSONString(postDetail);
    }

    @PostMapping("/admire")
    public @ResponseBody
    String admire(@RequestBody String requestData) {
        Long admiringPostId = JSON.parseObject(requestData, Long.class);
        if (admiringPostId == null) {
            return JSON.toJSONString(0);
        }else {
            postService.addAdmireNum(admiringPostId);
            return JSON.toJSONString(postService.findPostByPostId(admiringPostId).getAdmireNum());
        }
    }

    @PostMapping("/deletePost")
    public @ResponseBody
    String deletePost(@RequestBody String requestData) {
        Long deletingPostId = JSON.parseObject(requestData, Long.class);
        if (deletingPostId == null) {
            return JSON.toJSONString(ResultEnum.DELETE_POST_FAILED.getMessage());
        } else {
            postService.deletePostAndRelatedComments(deletingPostId);
            return JSON.toJSONString(ResultEnum.DELETE_POST_SUCCESS.getMessage());
        }
    }

    @PostMapping("/increaseComment")
    public @ResponseBody
    String increaseComment(@RequestBody String requestData){
        Comment newComment = JSON.parseObject(requestData, Comment.class);
        if (newComment == null||newComment.getUserId()!=null||newComment.getPostId()!=null) {
            return JSON.toJSONString(ResultEnum.INCREASE_COMMENT_FAILED.getMessage());
        } else {
            newComment.setCommentId(null);
            if (newComment.getUserName()==null){
                newComment.setUserName(userService.findUserByUserId(newComment.getUserId()).getUserName());
            }
            if (newComment.getCreateDate()==null){
                newComment.setCreateDate(new Date());
            }
            postService.increaseComment(newComment);
            return JSON.toJSONString(ResultEnum.INCREASE_COMMENT_SUCCESS.getMessage());
        }
    }

    @PostMapping("/collectPost")
    public @ResponseBody
    String collectPost(@RequestBody String requestData) {
        Collection newCollection = JSON.parseObject(requestData, Collection.class);
        if (newCollection == null || newCollection.getPostId() == null || newCollection.getUserId() == null) {
            return JSON.toJSONString(ResultEnum.COLLECT_POST_FAILED.getMessage());
        } else {
            return JSON.toJSONString(postService.collectPost(newCollection));
        }
    }

    @GetMapping("/searchCollectionList/{userId}")
    public @ResponseBody
    String searchCollectionList(@PathVariable("userId")Long userId){
        // TODO: 2019/1/15
        return JSON.toJSONString(postService.findCollectionListByUserId(userId));
    }
}
