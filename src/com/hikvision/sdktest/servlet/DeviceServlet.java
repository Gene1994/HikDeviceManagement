package com.hikvision.sdktest.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hikvision.sdktest.domain.Device;
import com.hikvision.sdktest.domain.PageBean;
import com.hikvision.sdktest.service.DeviceService;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

/**
 * <p>Title: DeviceServlet<／p>
 * <p>Description: <／p>
 * @author quzhe
 * 
 * 2018年3月12日
 */
public class DeviceServlet extends BaseServlet{
	private DeviceService deviceService = new DeviceService();

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Device device = CommonUtils.toBean(request.getParameterMap(), Device.class);
        device.setDeviceId(CommonUtils.uuid());

        deviceService.add(device);

        request.setAttribute("msg", "恭喜，成功添加客户");

        return "/msg.jsp";
    }
    
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         *1.获取页面传递的pc
         * 2.给定pr的值
         * 3.使用pc和pr调用service方法，得到pageBean，保存到request域
         * 4.转发到list.jsp
         */
        /*
         * 1.得到pc
         *   如果pc参数不存在，说明pc＝1
         *   如果pc参数存在，需要转换成int类型
         */
         int pc = getPc(request);

         int pr = 20;//给定pr的值，每页10行纪录

         PageBean<Device> pb = deviceService.findAll(pc, pr);
         pb.setUrl(getUrl(request));

         request.setAttribute("pb", pb);

         return "f:/list.jsp";
     }
    
    private int getPc(HttpServletRequest request) {
        String value = request.getParameter("pc");
        if (value == null || value.trim().isEmpty()) {
            return 1;
        }
        return Integer.parseInt(value);
    }
    
    //根据ID获取指定待修改设备
    public String preEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deviceId = request.getParameter("deviceId");
        Device device = deviceService.findById(deviceId);

        request.setAttribute("device", device);

        return "/edit.jsp";
    }

    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Device device = CommonUtils.toBean(request.getParameterMap(), Device.class);

        deviceService.edit(device);

        request.setAttribute("msg", "恭喜，编辑客户成功");
        return "/msg.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String deviceId = request.getParameter("deviceId");

        deviceService.delete(deviceId);

        request.setAttribute("msg", "恭喜，删除客户成功");

        return "/msg.jsp";
    }
    
    public String query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Device device = CommonUtils.toBean(request.getParameterMap(), Device.class);

//        System.out.println(getUrl(request));
        device = encoding(device);

        int pc = getPc(request);
        int pr = 20;

        PageBean<Device> pb = deviceService.query(device, pc, pr);

        pb.setUrl(getUrl(request));

        request.setAttribute("pb", pb);
        return "/list.jsp";

    }

    private Device encoding(Device device) throws UnsupportedEncodingException {
        String type = device.getType();
        String model = device.getModel();
        String ip = device.getIp();
        String port = device.getPort();
        String userName = device.getUserName();
        String password = device.getPassword();
        String language = device.getLanguage();
        String position = device.getPosition();
        String comment = device.getComment();

        if (type != null && !type.trim().isEmpty()) {
        	type = new String(type.getBytes("ISO-8859-1"), "utf-8");
            device.setType(type);
        }
        if (model != null && !model.trim().isEmpty()) {
        	model = new String(model.getBytes("ISO-8859-1"), "utf-8");
            device.setModel(model);
        }
        if (ip != null && !ip.trim().isEmpty()) {
            ip = new String(ip.getBytes("ISO-8859-1"), "utf-8");
            device.setIp(ip);
        }
        if (port != null && !port.trim().isEmpty()) {
            port = new String(port.getBytes("ISO-8859-1"), "utf-8");
            device.setPort(port);
        }
        if (userName != null && !userName.trim().isEmpty()) {
            userName = new String(userName.getBytes("ISO-8859-1"), "utf-8");
            device.setUserName(userName);
        }
        if (password != null && !password.trim().isEmpty()) {
            password = new String(password.getBytes("ISO-8859-1"), "utf-8");
            device.setPassword(password);
        }
        if (language != null && !language.trim().isEmpty()) {
            language = new String(language.getBytes("ISO-8859-1"), "utf-8");
            device.setLanguage(language);
        }
        if (position != null && !position.trim().isEmpty()) {
            position = new String(position.getBytes("ISO-8859-1"), "utf-8");
            device.setPosition(position);
        }
        if (comment != null && !comment.trim().isEmpty()) {
            comment = new String(comment.getBytes("ISO-8859-1"), "utf-8");
            device.setComment(comment);
        }
        return device;
    }
    
    private String getUrl(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String queryString = request.getQueryString();

        if (queryString.contains("&pc=")) {
            int index = queryString.lastIndexOf("&pc=");
            queryString = queryString.substring(0, index);
        }

        return contextPath + servletPath + "?" + queryString;
    }
}
