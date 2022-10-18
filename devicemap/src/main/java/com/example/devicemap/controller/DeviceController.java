package com.example.devicemap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.devicemap.entity.Device;
import com.example.devicemap.entity.DeviceStatus;
import com.example.devicemap.entity.User;
import com.example.devicemap.repo.DeviceRepo;
import com.example.devicemap.repo.UserRepo;
import com.example.devicemap.service.DeviceService;

@RestController
public class DeviceController {

	@Autowired
	public DeviceService deviceService;
	
	@Autowired
	private DeviceRepo deviceRepo;
	
	@Autowired
	private UserRepo userRepo;	
	
	
	DeviceStatus status = new DeviceStatus();
	
//====================================================================================================================================================	
	
	
	@GetMapping("/findAllRegisteredDevices")
	public ResponseEntity<List<Device>> getAllDevice(){
		try {
		      List<Device> allDevice = deviceService.getAllDevice();
		
		      if (allDevice.isEmpty()) {
		    	  
		       return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		     }
		       return new ResponseEntity<>(allDevice,HttpStatus.OK);
		
		}catch(Exception e) {
			  
			   return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		     }
		}
	
	
//===================================================================================================================================================
	
	
	@GetMapping("/findAllRegisteredDevicesByUsername/{username}")
	@Cacheable
	public ResponseEntity<List<Device>> getDeviceByUser(@PathVariable("username") String username) {
		try {
		      List<Device> getDeviceByUser = deviceService.getDeviceByUsername(username);
		      
		      return new ResponseEntity<>(getDeviceByUser,HttpStatus.OK);
		}catch(Exception e) {
			
			  return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

//====================================================================================================================================================	
	
	
	@PutMapping("/updateDevice/{username}/{deviceId}")
	public ResponseEntity<Object> updateDevice(@RequestBody Device device,@PathVariable("username") String username, @PathVariable("deviceId") String deviceId) {
	
	    try {
	    	if (deviceService.isDeviceAlreadyRegistered(deviceId) ) {
		     deviceService.updateDevice(device,username,deviceId);
		     
		      status.setStatusCode("0");
	    	  status.setStatusDesc("DEVICE UPDATED SUCCESSFULLY");
	    	  
		      return new ResponseEntity<>(status,HttpStatus.OK);
	    	}
	    }catch(Exception e) {
	    	
	    	  status.setStatusCode("-1");
	    	  status.setStatusDesc("ERROR IN UPDATING DEVICE. PLEASE TRY AGAIN WITH VALID CREDENTIALS !");
	    	  
		      return new ResponseEntity<>(status,HttpStatus.BAD_REQUEST);
	    }
	    status.setStatusCode("-1");
  	  status.setStatusDesc("ERROR IN UPDATING DEVICE. PLEASE TRY AGAIN WITH VALID CREDENTIALS !");
  	  
	      return new ResponseEntity<>(status,HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	
//====================================================================================================================================================	
		
	
	@GetMapping("/findAllRegisteredDevicesById/{deviceId}")
	public ResponseEntity<Object> getDeviceById(@PathVariable("deviceId") String deviceId) {
		try {
			
			if(deviceService.isDeviceAlreadyRegistered(deviceId)) {
		      Device getDeviceById = deviceService.getDeviceByDeviceId(deviceId);
		      
		      status.setStatusCode("0");
	    	  status.setStatusDesc("USER FOUND WITH THIS DIVICE ID. |||||   DETAILS :==> "+getDeviceById);
	    	  
              return new ResponseEntity<>(status,HttpStatus.OK);
			}
		}catch(Exception e) {
			
			  status.setStatusCode("-1");
	    	  status.setStatusDesc("INTERNAL SERVER ERROR ! PLEASE TRY AGAIN AFTER FEW MINUTES");
	    	  
			  return new ResponseEntity<>(status,HttpStatus.BAD_REQUEST);
		}
			  status.setStatusCode("-1");
			  status.setStatusDesc("USER NOT FOUND ! PLEASE TRY AGAIN AFTER FEW MINUTES");
  	  
		  return new ResponseEntity<>(status,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	
//===================================================================================================================================================	

	
	

	
	 @PostMapping("/addDevice")
	    public ResponseEntity<Object> addDevice(@RequestBody Device device) {
		    try {
		    	List<Device> registeredDevices = deviceRepo.findAllById(device.getUsername());
				
				Optional<User> user = userRepo.findByUsername(device.getUsername());
				
			    		
//		    Checks if user is already available in user table or not and registered device should not be more than 5 for that user and checks for duplicate device type	
			if (user.isPresent() && registeredDevices.size() < 5 && (deviceService.isDeviceAlreadyRegisteredByType(device.getDeviceType())==false) && (deviceService.isDeviceAlreadyRegistered(device.getDeviceId())==false) ) {
	
				
			      deviceService.addDevice(device);
			      
			      status.setStatusCode("0");
		    	  status.setStatusDesc("DEVICE MAPPED SUCCESSFULLY");
		    	  
			      return new ResponseEntity<>(status,HttpStatus.CREATED);
				}
		    
		    }catch (Exception e) {
		    	
		    	  status.setStatusCode("-1");
		    	  status.setStatusDesc("BAD REQUEST ! TRY AGAIN WITH VALID CREDENTIALS !");
		    	  
	    	      return new ResponseEntity<>(status,HttpStatus.BAD_REQUEST);
		    	 
	        }
		      status.setStatusCode("-1");
	    	  status.setStatusDesc("DEVICE CAN'T BE MAPPED !!  Please try aagin with valid credentials !");
	    	  
  	      return new ResponseEntity<>(status,HttpStatus.INTERNAL_SERVER_ERROR);
	    	 
		    
	}
	
			
	
//====================================================================================================================================================	

	
	
	@DeleteMapping("/deleteDevice/{username}/{deviceId}")
	public ResponseEntity<Object> deleteDevice(@PathVariable("username") String username,@PathVariable("deviceId") String deviceId) {
		try {
			List<Device> deleteDeviceById = deviceService.getDeviceByDeviceId1(deviceId);
			
			if (deviceService.isDeviceAlreadyRegistered(deviceId)==false) {
				
				status.setStatusCode("-1");
		    	  status.setStatusDesc("USER NOT FOUND !");
		    	  
			      return new ResponseEntity<>(status,HttpStatus.NOT_FOUND);
			}
			else{
				
		      deviceService.deleteDeviceByid(username,deviceId);
		      
		      status.setStatusCode("0");
	    	  status.setStatusDesc("DEVICE DELETED SUCCESSFULLY");
	    	  
		      return new ResponseEntity<>(status,HttpStatus.OK);
			}
	    }catch(Exception e) {
	    	
	    	  status.setStatusCode("-1");
	    	  status.setStatusDesc("DEVICE CAN'T BE DELETED");
	    	  
		      return new ResponseEntity<>(status,HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
//====================================================================================================================================================	

	
}











