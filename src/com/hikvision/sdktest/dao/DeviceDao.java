package com.hikvision.sdktest.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.hikvision.sdktest.domain.Device;
import com.hikvision.sdktest.domain.PageBean;

import cn.itcast.jdbc.TxQueryRunner;

/**
 * <p>
 * Title: DeviceDao<／p>
 * <p>
 * Description: <／p>
 * 
 * @author quzhe
 * 
 *         2018年3月7日
 */
public class DeviceDao {

	private QueryRunner qr = new TxQueryRunner();

	public void add(Device d) {
		try {
			String sql = "INSERT INTO DEVICE VALUES (?,?,?,?,?,?,?,?,?,?)";

			Object[] params = { d.getDeviceId(), d.getType(), d.getModel(), d.getIp(), d.getPort(), d.getUserName(),
					d.getPassword(), d.getLanguage(), d.getPosition(), d.getComment() };

			qr.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(String deviceId) {
		try {
			String sql = "delete from device where deviceid=?";

			qr.update(sql, deviceId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void edit(Device d) {
		try {
			String sql = "update device set deviceid=?,type=?,model=?,ip=?,port=?,username=?,password=?,language=?,position=?,comment=? where deviceid=?";
			Object[] params = { d.getDeviceId(), d.getType(), d.getModel(), d.getIp(), d.getPort(), d.getUserName(),
					d.getPassword(), d.getLanguage(), d.getPosition(), d.getComment(), d.getDeviceId() };
			qr.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Device findById(String deviceId) {
		try {

			String sql = "select * from device where deviceid = ?";
			return qr.query(sql, new BeanHandler<Device>(Device.class), deviceId);
		} catch (Exception e) {
			System.out.println("1");
			throw new RuntimeException(e);
			
		}
	}

	// /**
	// * 按设备类型查找
	// * @param type
	// * @return
	// */
	// public List<Device> findByType(String type){
	//
	// }
	//
	// /**
	// * 按设备型号查找
	// * @param model
	// * @return
	// */
	// public List<Device> findByModel(String model){
	//
	// }

	public PageBean<Device> query(Device device, int pc, int pr) {

		try {
			PageBean<Device> pb = new PageBean<>();
			pb.setPc(pc);
			pb.setPr(pr);

			StringBuilder cntSql = new StringBuilder("select count(*) from device ");
			StringBuilder whereSql = new StringBuilder(" where 1=1 ");
			List<Object> params = new ArrayList<>();

			String ip = device.getIp();
			if (ip != null && !ip.trim().isEmpty()) {
				whereSql.append("and ip = ?");
				params.add(ip);
			}

			String type = device.getType();
			if (type != null && !type.trim().isEmpty()) {
				whereSql.append("and type = ?");
				params.add(type);
			}

			String model = device.getModel();
			if (model != null && !model.trim().isEmpty()) {
				whereSql.append("and model like ?");
				params.add("%" + model + "%");
			}

			String comment = device.getComment();
			if (comment != null && !comment.trim().isEmpty()) {
				whereSql.append("and comment like ?");
				params.add("%" + comment + "%");
			}

			Number num = qr.query(cntSql.append(whereSql).toString(), new ScalarHandler<>(), params.toArray());

			int tr = num.intValue();
			pb.setTr(tr);

			StringBuilder sql = new StringBuilder("select * from device ");
			StringBuilder lmitSql = new StringBuilder(" limit ?,?");

			params.add((pc - 1) * pr);
			params.add(pr);

			List<Device> beanList = qr.query(sql.append(whereSql).append(lmitSql).toString(),
					new BeanListHandler<Device>(Device.class), params.toArray());
			pb.setBeanList(beanList);

			return pb;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public PageBean<Device> findAll(int pc, int pr) {
		try {
			/*
			 * 1.他需要创建pageBean对象pb 2.设置pb的pc和pr 3.得到tr，设置给pb 4.得到beanList设置给pb
			 * 最后返回给pb
			 */
			PageBean<Device> pb = new PageBean<>();
			pb.setPc(pc);
			pb.setPr(pr);

			String sql = "select count(*) from device";
			Number number = (Number) qr.query(sql, new ScalarHandler<>());

			int tr = number.intValue();
			pb.setTr(tr);

			// sql="select * from device order by name limit ?,?";
			sql = "select * from device limit ?,?";
			Object[] params = { (pc - 1) * pr, pr };
			List<Device> beanList = qr.query(sql, new BeanListHandler<>(Device.class), params);

			pb.setBeanList(beanList);

			return pb;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
