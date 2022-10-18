package com.example.devicemap.entity;


import java.time.LocalDateTime;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
//@RedisHash("Menu")
@Table("devicemap")
public class Device {

	@PrimaryKeyColumn(name = "username",type = PrimaryKeyType.PARTITIONED)
	private String username;
	
	@PrimaryKeyColumn(name = "device_id",type = PrimaryKeyType.CLUSTERED) 
	private String deviceId;
    
	@Column("device_type")
	private String deviceType;
	
	@Column("device_model")
	private String deviceModel;
	 
	@Column("created_date")
	private LocalDateTime createDate;
	
	@Column("updated_date")
	private LocalDateTime updatedDate;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "DeviceMap [username=" + username + ", deviceId=" + deviceId + ", deviceType=" + deviceType
				+ ", deviceModel=" + deviceModel + ", createDate=" + createDate + ", updatedDate=" + updatedDate + "]";
	}

	public Device(String username, String deviceId, String deviceType, String deviceModel, LocalDateTime createDate,
			LocalDateTime updatedDate) {
		super();
		this.username = username;
		this.deviceId = deviceId;
		this.deviceType = deviceType;
		this.deviceModel = deviceModel;
		this.createDate = createDate;
		this.updatedDate = updatedDate;
	}

	public Device() {
		super();
	}


	
	
}
