<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量处理添加单个文字水印</title>
</head>
<body>
    <h4>批量处理添加单个文字水印</h4>
    <h4>上传图片</h4>
    <hr/>
    <form name = "form1" action="${pageContext.request.contextPath}/batchTextWaterMark.action" method="post" enctype="multipart/form-data">
        <input type="file" name="image"/><br/>
        <input type="file" name="image"/><br/>
        <input type="file" name="image"/><br/>
        <input type="file" name="image"/><br/>
        <input type="file" name="image"/><br/>
        <input type="submit" value="上传需要添加文字水印的图片"/>
    </form>
    <hr/>
    <a href="${pageContext.request.contextPath}/index.jsp">返回首页</a><br/>
</body>
</html>