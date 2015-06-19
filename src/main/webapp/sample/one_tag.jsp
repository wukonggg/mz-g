<%--
  Desc:
  User: wukong(wukonggg@139.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="one" uri="/WEB-INF/tld/one.tld"  %>

<%
    pageContext.setAttribute("key", "value");
%>
<html>
<head>
    <title>${one:objectToString(key)}</title>
</head>
<body>

</body>
</html>
