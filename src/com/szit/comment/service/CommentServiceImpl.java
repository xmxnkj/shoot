package com.szit.comment.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsit.common.service.AppBaseServiceImpl;
import com.hsit.common.utils.EntityUtils;
import com.szit.comment.dao.CommentDao;
import com.szit.comment.entity.Comment;
import com.szit.comment.entity.CommentLike;
import com.szit.comment.entity.query.CommentLikeQuery;
import com.szit.comment.entity.query.CommentQuery;

@Service
public class CommentServiceImpl extends AppBaseServiceImpl<Comment, CommentQuery> implements CommentService{
	@Autowired
	private CommentDao dao;

	@Override
	public CommentDao getDao() {
		return dao;
	}
	
	@Autowired
	private CommentLikeService commentLikeService;
	public void dealLikeState(List<Comment> comments, String userId){
		if(comments!=null && comments.size()>0 && !StringUtils.isEmpty(userId)){
			List<String> ids = EntityUtils.getEntityIdLists(comments);
			CommentLikeQuery query = new CommentLikeQuery();
			query.setCommentIds(ids);
			query.setUserId(userId);
			List<CommentLike> commentLikes = commentLikeService.getEntities(query);
			for (Comment comment : comments) {
				for(int i=0; i<commentLikes.size(); i++){
					if (comment.getId().equalsIgnoreCase(commentLikes.get(i).getCommentId())) {
						comment.setLiked(true);
						commentLikes.remove(i);
						break;
					}
				}
			}
		}
	}
	
}
