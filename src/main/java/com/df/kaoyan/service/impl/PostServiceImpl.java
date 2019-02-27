package com.df.kaoyan.service.impl;

import com.df.kaoyan.dataobject.Collection;
import com.df.kaoyan.dataobject.Comment;
import com.df.kaoyan.dataobject.Post;
import com.df.kaoyan.enums.ResultEnum;
import com.df.kaoyan.repository.CollectionRepository;
import com.df.kaoyan.repository.CommentRepository;
import com.df.kaoyan.repository.PostRepository;
import com.df.kaoyan.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CollectionRepository collectionRepository;

    @Override
    public Post saveNewPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findPostListByUserId(Long userId) {
        return postRepository.findPostsByUserId(userId);
    }

    @Override
    public void addAdmireNum(Long postId) {
        Post foundPost = postRepository.findPostByPostId(postId);
        if (foundPost != null) {
            foundPost.setAdmireNum(foundPost.getAdmireNum()+1);
            postRepository.save(foundPost);
        }
    }

    @Override
    public void deletePostAndRelatedComments(Long postId) {
        postRepository.deleteById(postId);
        commentRepository.deleteCommentsByPostId(postId);
        collectionRepository.deleteCollectionsByPostId(postId);
    }

    @Override
    public void increaseComment(Comment newComment) {
        commentRepository.save(newComment);
    }

    @Override
    public String collectPost(Collection newCollection) {
        Collection foundCollection = collectionRepository
                .findCollectionByPostIdAndUserId(newCollection.getPostId(), newCollection.getUserId());
        if (foundCollection != null) {
            return ResultEnum.COLLECT_POST_COLLECTED.getMessage();
        } else {
            newCollection.setCollectionId(null);
            collectionRepository.save(newCollection);
            return ResultEnum.COLLECT_POST_SUCCESS.getMessage();
        }
    }

    @Override
    public Post findPostByPostId(Long postId) {
        return postRepository.findPostByPostId(postId);
    }

    @Override
    public List<Comment> findCommentListByPostId(Long postId) {
        return commentRepository.findCommentsByPostId(postId);
    }

    @Override
    public List<Collection> findCollectionListByUserId(Long userId) {
        return collectionRepository.findCollectionsByUserId(userId);
    }
}
