<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量处理单个文字水印添加效果展示</title>
</head>
<body>
    <h4>批量处理单个文字水印添加效果展示</h4>
    <table>
        <s:iterator value="picInfo">
            <tr>
	            <td width="50%"><img src='${pageContext.request.contextPath }<s:property value="imageURL"/>' width="350" /></td>
	            <td width="50%"><img src='${pageContext.request.contextPath }<s:property value="logoImageURL"/>' width="350" /></td>
            </tr>
        </s:iterator>
        
    </table>
    <hr/>
    <a href="${pageContext.request.contextPath}/index.jsp">返回首页</a><br/>
</body>
</html>