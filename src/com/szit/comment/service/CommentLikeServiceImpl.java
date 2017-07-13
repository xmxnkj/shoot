package com.szit.comment.service;

import com.szit.comment.dao.CommentLikeDao;
import com.szit.comment.entity.Comment;
import com.szit.comment.entity.CommentLike;
import com.szit.comment.entity.query.CommentLikeQuery;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsit.common.service.AppBaseServiceImpl;

@Service
public class CommentLikeServiceImpl extends AppBaseServiceImpl<CommentLike, CommentLikeQuery> implements CommentLikeService{
	@Autowired
	private CommentLikeDao dao;

	@Override
	public CommentLikeDao getDao() {
		return dao;
	}
	
	@Autowired
	private CommentService commentService;
	public void addLike(String commentId, String userId){
		Comment comment = commentService.getById(commentId);
		if (comment!=null) {
			CommentLike like = getCommentLike(commentId, 
					userId);
			if (like==null) {
				like = new CommentLike();
				like.setCommentId(commentId);
				like.setUserId(userId);
				like.setReportId(comment.getReportId());
				like.setLikeDate(new Date());
				save(like);
				
				comment.setLikeCount(comment.getLikeCount()+1);
				commentService.saveSimple(comment);
			}
		}
	}
	
	public CommentLike getCommentLike(String commentId, String userId){
		
		if (!StringUtils.isEmpty(commentId) && !StringUtils.isEmpty(userId)) {
			CommentLikeQuery query = new CommentLikeQuery();
			query.setCommentId(commentId);
			query.setUserId(userId);
			return getEntity(query);
		}
		return null;
	}
}
