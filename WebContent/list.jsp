<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>设备列表</title>
</head>
<body>
	<h3 align="center">设备列表</h3>
	<table border="1" width="70%" align="center">
		<tr>
			<th>设备ID</th>
			<th>设备类型</th>
			<th>设备型号</th>
			<th>IP</th>
			<th>端口号</th>
			<th>用户名</th>
			<th>密码</th>
			<th>语言</th>
			<th>位置</th>
			<th>备注</th>
		</tr>
		<c:forEach items="${pb.beanList}" var="device">
			<tr>
				<td>${device.deviceId}</td>
				<td>${device.type}</td>
				<td>${device.model}</td>
				<td>${device.ip}</td>
				<td>${device.port}</td>
				<td>${device.userName}</td>
				<td>${device.password}</td>
				<td>${device.language}</td>
				<td>${device.position}</td>
				<td>${device.comment}</td>
				<td><a
					href="<c:url value='/DeviceServlet?method=preEdit&deviceId=${device.deviceId}'/> ">编辑</a>
					<a
					href="<c:url value='/DeviceServlet?method=delete&deviceId=${device.deviceId}'/> ">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<center>
		第${pb.pc}页/共${pb.tp}页 <a href="${pb.url}&pc=1">首页</a>
		<c:if test="${pb.pc>1}">
			<a href="${pb.url}&pc=${pb.pc-1}">上一页</a>
		</c:if>

		<c:choose>
			<c:when test="${pb.tp<=10}">
				<c:set var="begin" value="1" />
				<c:set var="end" value="${pb.tp}" />
			</c:when>
			<c:otherwise>
				<c:set var="begin" value="${pb.pc-5}" />
				<c:set var="end" value="${pb.pc+4}" />
				<%--头溢出--%>
				<c:if test="${begin<1}">
					<c:set var="begin" value="1" />
					<c:set var="end" value="10" />
				</c:if>
				<%--尾溢出--%>
				<c:if test="${end>pb.tp}">
					<c:set var="end" value="${pb.tp}" />
					<c:set var="begin" value="${pb.tp-9}" />
				</c:if>
			</c:otherwise>
		</c:choose>

		<%--循环遍历页码列表--%>
		<c:forEach var="i" begin="${begin}" end="${end}">
			<c:choose>
				<c:when test="${i eq pb.pc}">
                [${i}]
            </c:when>
				<c:otherwise>
					<a href="${pb.url}&pc=${i}">[${i}]</a>
				</c:otherwise>
			</c:choose>

		</c:forEach>


		<c:if test="${pb.pc<pb.tp}">
			<a href="${pb.url}&pc=${pb.pc+1}">下一页</a>
		</c:if>
		<a href="${pb.url}&pc=${pb.tp}">尾页</a>

	</center>

</body>
</html>
