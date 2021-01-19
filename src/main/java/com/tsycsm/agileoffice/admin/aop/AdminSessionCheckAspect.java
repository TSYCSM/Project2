package com.tsycsm.agileoffice.admin.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tsycsm.agileoffice.exception.LoginRequiredException;

public class AdminSessionCheckAspect {
	private static final Logger logger=LoggerFactory.getLogger(AdminSessionCheckAspect.class);	
	
	public Object sessionCheck(ProceedingJoinPoint joinPoint) throws Throwable{
		Object target = joinPoint.getTarget(); //���� ȣ���Ϸ��� �ߴ� ��ü
		logger.debug("���� ȣ���Ϸ��ߴ� ��ü�� "+target);
		String methodName = joinPoint.getSignature().getName();
		logger.debug("���� ȣ���Ϸ��ߴ� �޼���� "+methodName); 
		Object[] args=joinPoint.getArgs(); //���� ȣ���Ϸ��޴� �޼����� �Ű�����
		
		//������ ��û�� ������ ������ �ִ����� �Ǵ��Ͽ�, ������ ����..
		
		
		//������ ��� ���ؼ���  HttpServletRequest�� �ʿ��ϴ�!!
		HttpServletRequest request=null;
		
		for(Object arg : args) {
			logger.debug("�Ű��������� "+arg);
			if(arg instanceof HttpServletRequest) { //request��ü���...
				request=(HttpServletRequest)arg;
			}
		}
		
		//1.������ ���ٸ�?? ���ܸ� �߻���ų���� --> ExceptionHandler�� ���ļ� Ŭ���̾�Ʈ���� ������ ����ó��..
		//2.������ �ִٸ�? �״뵵 ���� ȣ���Ϸ� �ߴ� �޼��� �����������..
		HttpSession session=null;
		session=request.getSession();
		Object result=null;
		
		if(session.getAttribute("admin")==null) {
			throw new LoginRequiredException("�α����� �ʿ��� �����Դϴ�");
		}else {
			//���� ��û�� �帧�� �״�� ����..
			//���� ȣ���Ϸ��ߴ� �޼��带 ��� ȣ��
			result=joinPoint.proceed(); //���⼭ ���ܰ� �߻��ϹǷ� ����ó����������, �׳� throws �սô�!
		}
		return result;
	}
}