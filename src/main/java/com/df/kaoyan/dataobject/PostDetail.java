package com.df.kaoyan.dataobject;

import java.util.List;

public class PostDetail {
    Post post;
    List<Comment> commentList;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
