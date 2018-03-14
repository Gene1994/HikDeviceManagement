package com.hikvision.sdktest.service;

import com.hikvision.sdktest.dao.DeviceDao;
import com.hikvision.sdktest.domain.Device;
import com.hikvision.sdktest.domain.PageBean;



/**
 * <p>Title: DeviceService<／p>
 * <p>Description: <／p>
 * @author quzhe
 * 
 * 2018年3月7日
 */
public class DeviceService {
	DeviceDao deviceDao = new DeviceDao();
	
	public void add(Device device){
		deviceDao.add(device);
	}
	
	public PageBean<Device> findAll(int pc, int pr){
		return deviceDao.findAll(pc, pr);
	}
	
	public Device findById(String deviceiId){
		return deviceDao.findById(deviceiId);
	}
	
	public PageBean<Device> query(Device device,int pc,int pr){
		return deviceDao.query(device, pc, pr);
	}
	
	public void edit(Device device){
		deviceDao.edit(device);
	}
	
	public void delete(String deviceId){
		deviceDao.delete(deviceId);
	}
}
