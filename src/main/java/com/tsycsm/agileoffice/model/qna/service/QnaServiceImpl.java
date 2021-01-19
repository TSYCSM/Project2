package com.tsycsm.agileoffice.model.qna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.exception.MailSendException;
import com.tsycsm.agileoffice.model.common.MailSender;
import com.tsycsm.agileoffice.model.domain.Email;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	private MailSender mailSender;

	@Override
	public void send(Email email) throws MailSendException {
		StringBuilder sb = new StringBuilder();
		sb.append("문의 내용 : " + email.getContent());
		sb.append("<br>상호명 : " + email.getName());
		sb.append("<br>메일 주소 : " + email.getEmail_address());
		
		String content = sb.toString();

		mailSender.send("fge503@naver.com", email.getTitle(), content);
	}
}
