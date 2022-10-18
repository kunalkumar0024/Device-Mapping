package com.example.devicemap.repo;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.devicemap.entity.Device;
@Repository
public interface DeviceRepo extends CassandraRepository<Device, String>{

	
	@Query("select * from devicemap where username=:username")
	List<Device> findAllById(String username);

	@Query("delete from devicemap where username=:username and device_id=:deviceId")
	Device deleteById(String username, String deviceId);

	@Query("select * from devicemap where device_id=:deviceId ")
	Device findByRegisteredDevice(String deviceId);

	@Query("select * from devicemap where device_id=:deviceId ")
	Device findAllById1(String deviceId);
	
	@Query("select * from devicemap where device_id=:deviceId ")
	List<Device> findAllById2(String deviceId);
 
	@Query("select username from devicemap where username=:username")
	Device findByUsername(String username);

	@Query("select deviceType from devicemap where device_type=:deviceType ")
	Device findByDeviceType(String deviceType);



}
