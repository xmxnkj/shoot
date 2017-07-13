package com.szit.comment.service;

import java.util.List;

import com.hsit.common.service.AppBaseService;
import com.szit.comment.entity.Comment;
import com.szit.comment.entity.query.CommentQuery;

public interface CommentService extends AppBaseService<Comment, CommentQuery> {
	public void dealLikeState(List<Comment> comments, String userId);
}
