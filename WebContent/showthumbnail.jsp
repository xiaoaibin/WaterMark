<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>缩略前后对比</title>
</head>
<body>
    <h4>图片信息</h4>
    <hr/>
    <table width="100%">
        <tr>
            <td width="50%" align="center">
                <img src='${pageContext.request.contextPath}<s:property value="picInfo.imageURL"/>'>
            </td>
            <td width="50%" align="center">
                <img src='${pageContext.request.contextPath}<s:property value="picInfo.logoImageURL"/>'>
            </td>
        </tr>
    </table>
    <hr/>
    <a href="${pageContext.request.contextPath}/index.jsp">返回首页</a><br/>
</body>
</html>