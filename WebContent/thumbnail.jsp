<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>获取缩略图</title>
</head>
<body>
    <h4>请选择要等比例缩略的图片进行上传</h4>
    <hr/>
    <form name = "upload_form" action="${pageContext.request.contextPath}/thumbnail.action" method="post" enctype="multipart/form-data">
        <input type="file" name="image" id="image"/><br/>
        <input type="submit" value="上传图片"/>
    </form>
    <hr/>
    <a href="${pageContext.request.contextPath}/index.jsp">返回首页</a><br/>
</body>
</html>