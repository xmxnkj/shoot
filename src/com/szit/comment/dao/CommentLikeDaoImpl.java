package com.szit.comment.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.QueryParam.ParamCompareType;
import com.hsit.common.dao.hibernate.HibernateDao;
import com.szit.comment.entity.CommentLike;
import com.szit.comment.entity.query.CommentLikeQuery;

@Repository
public class CommentLikeDaoImpl extends HibernateDao<CommentLike, CommentLikeQuery> implements CommentLikeDao{

	@Override
	public List<QueryParam> buildQueryParams(CommentLikeQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		
		if (query != null) {
			if (!StringUtils.isEmpty(query.getReportId())) {
				qps.add(new QueryParam("reportId", query.getReportId()));
			}
			if (!StringUtils.isEmpty(query.getCommentId())) {
				qps.add(new QueryParam("commentId", query.getCommentId()));
			}
			if (!StringUtils.isEmpty(query.getUserId())) {
				qps.add(new QueryParam("userId", query.getUserId()));
			}
			if (query.getCommentIds()!=null && query.getCommentIds().size()>0) {
				QueryParam qp = new QueryParam();
				StringBuilder sql = new StringBuilder("commentId IN ('")
						.append(query.getCommentIds().get(0)).append("'");
				for(int i=1; i<query.getCommentIds().size(); i++){
					sql.append(", '").append(query.getCommentIds().get(i)).append("'");
				}
				sql.append(")");
				qp.setName(sql.toString());
				qp.setCompareType(ParamCompareType.AssignExpression);
				qps.add(qp);
			}
		}
		
		
		return qps;
	}
}
