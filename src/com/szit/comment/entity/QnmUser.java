package com.szit.comment.entity;

import javax.persistence.Table;

import com.hsit.common.uac.entity.User;

@Table(name="uac_user")
public class QnmUser extends User{
	
	private static final long serialVersionUID = 7765033127365487663L;

	public QnmUser() {
		super();
	}
}
