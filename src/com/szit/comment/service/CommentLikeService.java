package com.szit.comment.service;

import com.hsit.common.service.AppBaseService;
import com.szit.comment.entity.CommentLike;
import com.szit.comment.entity.query.CommentLikeQuery;

public interface CommentLikeService extends AppBaseService<CommentLike, CommentLikeQuery>{
	public CommentLike getCommentLike(String commentId, String userId);
	
	public void addLike(String commentId, String userId);
}
