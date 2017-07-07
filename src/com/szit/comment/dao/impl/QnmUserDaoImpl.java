package com.szit.comment.dao.impl;

import org.springframework.stereotype.Repository;

import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.client.entity.query.AuthorityGroupQuery;
import com.szit.comment.entity.QnmUser;

@Repository
public class QnmUserDaoImpl  extends BaseHibernateDaoImpl<QnmUser, AuthorityGroupQuery>{

}
