<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
</head>
<body>
    <h3 align="center">高级搜索</h3>
    <form action="<c:url value="/DeviceServlet"/>" method="get">
        <input type="hidden" name="method" value="query">
        <table border="0" align="center" width="40%" style="margin-left: 100px">
         <!--  
            <tr>
           
                <td>设备类型</td>
                <td>
                    <select name="type">
                        <option value="">==请选择设备类型==</option>
                        <option value="male">PC</option>
                        <option value="female">DVR/NVR</option>
                        <option value="female">DVS</option>
    					<option value="female">IPC</option>
    					<option value="female">IP球</option>
    					<option value="female">NVR</option>
    					<option value="female">UTC</option>
    					<option value="female">半球PTZ</option>
    					<option value="female">报警主机</option>
    					<option value="female">车站-DVR</option>
    					<option value="female">动环监控报警主机</option>
    					<option value="female">多屏控制器</option>
    					<option value="female">合码器</option>
    					<option value="female">会议视频终端</option>
    					<option value="female">交通IPC</option>
    					<option value="female">解码器</option>
    					<option value="female">矩阵</option>
    					<option value="female">门禁一体机</option>
    					<option value="female">IPC</option>
    					<option value="female">IPC</option>
    					<option value="female">IPC</option>
    					<option value="female">IPC</option>
    					<option value="female">IPC</option>
    					<option value="female">IPC</option>
    					<option value="female">IPC</option>
    					<option value="female">IPC</option>
    					<option value="female">IPC</option>
    					<option value="female">IPC</option>
                    </select>
                </td>
               
            </tr>
             -->
              <tr>
                <td width="100px">设备类型</td>
                <td width="40%">
                    <input type="text" name="type">
                </td>
            </tr>
            <tr>
                <td width="100px">设备型号</td>
                <td width="40%">
                    <input type="text" name="model">
                </td>
            </tr>
            <tr>
                <td width="100px">IP</td>
                <td width="40%">
                    <input type="text" name="ip">
                </td>
            </tr>
            <tr>
                <td width="100px">备注</td>
                <td width="40%">
                    <input type="text" name="comment">
                </td>
            </tr>
            
            <tr>
                <td></td>
                <td>

                    <input type="submit" value="搜索"/>
                    <input type="reset" value="重置"/>
                </td>
            </tr>

        </table>
    </form>

</body>
</html>
