<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM Emp: Home</title></head>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
    <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
        <td><h3>IBM Emp: Home</h3><font color=red>( MVC )</font></td>
    </tr>
</table>

<p>This is the Home page for IBM Emp: Home</p>

<h3>��Ƭd��:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
    <font color='red'>�Эץ��H�U���~:
        <ul>
            <c:forEach var="message" items="${errorMsgs}">
                <li>${message}</li>
            </c:forEach>
        </ul>
    </font>
</c:if>

<ul>
    <li>
        <a href='<%=request.getContextPath()%>/frontend/diary/photo/listAllPhoto4.jsp'>List
        </a> all listAllPhoto4.
    </li>
    <li>
       ${mem_no}
       ${mem_name}
       <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/PhotoServlet.do">
            <input type="hidden" name="action" value="getMemBaby_For_Display">
            <input type="hidden" name="mem_no" value="${mem_no}">
            <input type="submit" value="�e�X">
        </FORM>
    </li>

    <li>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/PhotoServlet.do">
            <b>��J�|���s�� (�pM0000001):</b>
            <input type="text" name="mem_no">
            <input type="submit" value="�e�X">
            <input type="hidden" name="action" value="getMemBaby_For_Display">
        </FORM>
    </li>


    <li>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/PhotoServlet.do">
            <b>��J�p�Ľs�� (�pBABY0001):</b>
            <input type="text" name="baby_no">
            <input type="submit" value="�e�X">
            <input type="hidden" name="action" value="getbaby_For_Display">
        </FORM>
    </li>

    <li>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/PhotoServlet.do">
            <b>��J�|���s�� (�pM0000001):</b>
            <input type="text" name="baby_no">
            <input type="submit" value="�e�X">
            <input type="hidden" name="action" value="getbaby_For_Display">
        </FORM>
    </li>

    <li>
        request.getContextPath() = <%=request.getContextPath()%>
    </li>


    <h3><font color=orange>�����޲z</font></h3>


    <li><a href='<%=request.getContextPath()%>/dept/listAllDept.jsp'>List</a> all Depts.</li>
</ul>

</body>

</html>
