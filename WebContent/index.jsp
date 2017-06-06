<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片上传主页</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/textmark.jsp">添加单个文字水印</a><br/>
    <a href="${pageContext.request.contextPath}/multitextmark.jsp">添加多个文字水印</a><br/>
    <a href="${pageContext.request.contextPath}/imagemark.jsp">添加单个图片水印</a><br/>
    <a href="${pageContext.request.contextPath}/multiimagemark.jsp">添加多个图片水印</a><br/>
    <a href="${pageContext.request.contextPath}/batchtextmark.jsp">批量处理添加单个文字水印</a><br/>
    <a href="${pageContext.request.contextPath}/batchmultitextmark.jsp">批量处理添加多个文字水印</a><br/>
    <a href="${pageContext.request.contextPath}/batchimagemark.jsp">批量处理添加单个图片水印</a><br/>
    <a href="${pageContext.request.contextPath}/batchmultiimagemark.jsp">批量处理添加多个图片水印</a><br/>
</body>
</html>