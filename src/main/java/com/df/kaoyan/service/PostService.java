package com.df.kaoyan.service;

import com.df.kaoyan.dataobject.Collection;
import com.df.kaoyan.dataobject.Comment;
import com.df.kaoyan.dataobject.Post;

import java.util.List;

public interface PostService {
    Post saveNewPost(Post post);

    List<Post> findAll();

    List<Post> findPostListByUserId(Long userId);

    void increaseComment(Comment newComment);

    void deletePostAndRelatedComments(Long postId);

    void addAdmireNum(Long postId);

    String collectPost(Collection newCollection);

    Post findPostByPostId(Long postId);

    List<Comment> findCommentListByPostId(Long postId);

    List<Post> findCollectionPostListByUserId(Long userId);
}
