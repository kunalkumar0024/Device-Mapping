package com.example.devicemap.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.devicemap.entity.Device;
import com.example.devicemap.entity.DeviceStatus;
import com.example.devicemap.repo.DeviceRepo;
import com.example.devicemap.repo.UserRepo;
@Service
public class DeviceService{

		@Autowired
		private DeviceRepo deviceRepo;
		@Autowired
		private UserRepo userRepo;
		
		DeviceStatus status = new DeviceStatus();
		
	
//====================================================================================================================================================
		
		
		public List<Device> getAllDevice(){
			
			List<Device> device=new ArrayList<>();
			deviceRepo.findAll().forEach(device::add);
			
			return device;
		}
		
		
//====================================================================================================================================================		
		
		
		public List<Device> getDeviceByUsername(String username) {
			
			List<Device> getAllDevice = deviceRepo.findAllById(username);
			
			return getAllDevice;
		}
		
		
//====================================================================================================================================================	
		
		

		public Device getDeviceByDeviceId(String deviceId) {
		
			return deviceRepo.findAllById1(deviceId);
			
			
	}
		
		
		
//====================================================================================================================================================
		
		
		public List<Device> getDeviceByDeviceId1(String deviceId) {
			
			List<Device> getAllDevice = deviceRepo.findAllById2(deviceId);
			
			return getAllDevice;
		}
		
		
//====================================================================================================================================================	
		
		
		public Device addDevice(Device device) {
			 
			return deviceRepo.save(device);
		}
		
		
//====================================================================================================================================================
		
		
		public Device updateDevice(Device device,String username,String deviceId) {
			
		 return deviceRepo.save(device);
		 
		}
		
		
		
//====================================================================================================================================================			

		
		public Device deleteDeviceByid(String username,String deviceId) {
			
			return  deviceRepo.deleteById(username,deviceId);
			
		}
		
		
//====================================================================================================================================================	
	
		
		public boolean isDeviceAlreadyRegistered(String deviceId) {
			
			Device registeredDivice = deviceRepo.findByRegisteredDevice(deviceId);
			
			return registeredDivice != null;	
		}
		
		
//====================================================================================================================================================		

        public boolean isDeviceAlreadyRegisteredByType(String deviceType) {
			
			 Device registeredDiviceByType = deviceRepo.findByRegisteredDevice(deviceType);
			
			 return registeredDiviceByType != null;	
		}
		
		
		
		
//====================================================================================================================================================		
	
        


}












