<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Title</title>
</head>
<body>
	<h3 align="center">修改设备</h3>
	<form action="<c:url value='/DeviceServlet'/>" method="post">
		<input type="hidden" name="method" value="edit" /> <input
			type="hidden" name="deviceId" value="${device.deviceId}" />
		<table border="0" align="center" width="40%"
			style="margin-left: 100px">
			<tr>
				<td width="100px">设备类型</td>
				<td width="40%"><input type="text" name="type"
					value="${device.type}" /></td>
				<td align="left"><label id="nameError" class="error">&nbsp;</label>
				</td>
			</tr>
			<tr>
				<td width="100px">设备型号</td>
				<td width="40%"><input type="text" name="model"
					value="${device.model}" /></td>
				<td align="left"><label id="nameError" class="error">&nbsp;</label>
				</td>
			</tr>
			<tr>
				<td width="100px">IP</td>
				<td width="40%"><input type="text" name="ip"
					value="${device.ip}" /></td>
				<td align="left"><label id="nameError" class="error">&nbsp;</label>
				</td>
			</tr>
			<tr>
				<td width="100px">端口号</td>
				<td width="40%"><input type="text" name="port"
					value="${device.port}" /></td>
				<td align="left"><label id="nameError" class="error">&nbsp;</label>
				</td>
			</tr>
			<tr>
				<td width="100px">用户名</td>
				<td width="40%"><input type="text" name="username"
					value="${device.userName}" /></td>
				<td align="left"><label id="nameError" class="error">&nbsp;</label>
				</td>
			</tr>
			<tr>
				<td width="100px">密码</td>
				<td width="40%"><input type="text" name="password"
					value="${device.password}" /></td>
				<td align="left"><label id="nameError" class="error">&nbsp;</label>
				</td>
			</tr>
			<tr>
				<td>语言</td>
				<td><input type="radio" name="language" value="CN" id="CN"
					<c:if test="${device.language eq 'CN'}"/> checked="checked" /> <label
					for="CN">CN</label> <input type="radio" name="language" value="EN"
					id="EN" <c:if test="${device.language eq 'EN'}"/> checked="checked" />
					<label for="EN">EN</label></td>
				<td><label id="genderError" class="error">&nbsp;</label></td>
			</tr>
			<tr>
				<td>位置</td>
				<td><select name="position">
						<option value="">==请选择设备所在位置==</option>
						<option value="p_1">第一排</option>
						<option value="p_2">第二排</option>
						<option value="p_3">第三排</option>
						<option value="p_4">第四排</option>
						<option value="p_5">第五排</option>
				</select></td>
			</tr>
			<tr>
				<td>备注</td>
				<td><textarea rows="5" cols="30" name="comment">${device.comment}</textarea></td>
				<td><label id="descriptionError" class="error">&nbsp;</label></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="submit" value="修改设备" /> <input
					type="reset" name="reset" /></td>
			</tr>
		</table>
	</form>


</body>
</html>
