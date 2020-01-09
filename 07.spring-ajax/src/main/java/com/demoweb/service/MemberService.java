package com.demoweb.service;

import java.util.List;

import com.demoweb.dao.MemberDao;
import com.demoweb.vo.Member;

public class MemberService {
	
	private MemberDao memberDao = new MemberDao();
	
	public Member searchMemberById(String memberId) {
		
		return memberDao.selectMemberById(memberId);
	
	}

	public List<String> searchIdList(String id) {
		
		return memberDao.getMemberIdListByKey(id);
		
	}
	
	


}











