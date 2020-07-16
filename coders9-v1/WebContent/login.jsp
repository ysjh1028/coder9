<%@page import="com.sample.vo.User"%>
<%@page import="com.sample.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String userEmail = request.getParameter("email");
	String password = request.getParameter("pwd");
	
	UserDao userDao = new UserDao();
	User user = userDao.getUserByEmail(userEmail);
	
	if(user == null) {	// 유저 정보가 없음
		response.sendRedirect("loginform.jsp?error=fail");
		return;
	}
	if(!user.getPassword().equals(password)) {	// 비밀번호가 일치하지 않음
		response.sendRedirect("loginform.jsp?error=fail");
		return;
	}
	
	session.setAttribute("LOGINED_USER_EMAIL", user.getEmail());
	session.setAttribute("LOGINED_USER_NAME", user.getName());
	session.setAttribute("LOGINED_YN", "Y");
	
	response.sendRedirect("../home.jsp");
%>