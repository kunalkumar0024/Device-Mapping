# **Device-Mapping**

## **System Description:**

The system is designed to manage a collection of devices and users, with a strict rule that once a device is assigned to a user, it cannot be reassigned to another user. This ensures exclusive device ownership for each user.

## **Technology used:**
**Spring Boot:** Developed RESTful APIs to perform desired operations.

## **Database Used:**

**🗄️ Cassandra (Local):** The data is stored in a local Cassandra database, chosen for its scalability and distributed architecture. The data modeling follows Cassandra’s best practices for denormalization and efficient querying.

## **🛠️ Supported Operations:**

## **Device Operations:**

**🔧 Add New Device:** This operation involves inserting a new device record into the devices table, specifying its unique identifier (device_id)

**🗑️ Delete Existing Device:** This operation removes a device record from the devices table based on its unique identifier (device_id).

**🔍 Find Device Using DeviceId:** Retrieve detailed information about a device by querying the devices table using the unique device_id.

**🔗 Assign Device to Existing User:** This operation involves inserting a record into the database to associate a device with a user. If the device is already assigned to a user, the system should prevent reassignment.

**📜 Find List of Assigned Devices to Specific User:** Query the respective table in database to obtain a list of device identifiers associated with a specific user.

## **User Operations:**

**🆕 Add New User:** Insert a new user record into the users table with a unique identifier (user_id)

**🗑️ Delete Existing User:** Remove a user record from the users table based on the unique user_id.

**✏️ Update User Data by UserId:** Modify existing user details in the users table based on the unique user_id.

**📋 Find All Users:** Retrieve a complete list of all user records from the users table.

**🔎 Find User by UserId:** Query the users table to obtain detailed information about a user using their unique user_id.


## Getting Started

### Prerequisites

1. **Install Cassandra**: Ensure Cassandra is installed and running locally. Follow [Cassandra Installation Guide](https://cassandra.apache.org/doc/latest/getting_started/installing.html) for setup instructions.

2. **Java and Maven**: Ensure you have Java 11 or higher and Maven installed.

### Build and Run

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/kunalkumar0024/Device-Mapping.git

2. Navigate to the Project Directory:
   ```bash
   cd Device-Mapping

3. Build the Project:
   ```bash
   ./mvnw clean install

4. Run the Application:
   ```bash
   ./mvnw spring-boot:run

Access API Documentation: Detailed API endpoints and usage instructions can be found in this README file.

## **API Endpoints**
**Devices**

**.   POST**  - Add a new device.
   ```bash 
   /addDevice
   ```


**.   DELETE**  - Delete an existing device.
   ```bash
   /deleteDevice/{username}/{deviceId}
   ```

**.   GET**   - Find a device by ID.
```bash
/findAllRegisteredDevicesById/{deviceId}
```

**.   GET**  - Find all device associated with user
```bash 
/findAllRegisteredDevicesByUsername/{username}
```

**.   GET**  - Find list of registered devices
```bash
/findAllRegisteredDevices
``` 

**.   PUT**  - Update Device information
```bash
/updateDevice/{username}/{deviceId}
```


**Users**

**.   POST**  - Add a new user.
```bash
/addUser
``` 

**.   PUT**  - Update user data.
```bash
/updateUser/{userId}
```

**.   DELETE**  - Delete an existing user.
```bash
/deleteUser/{userId}
``` 

**.   GET**  - Find all users.
```bash
/findAllRegisteredUsers
``` 

**.   GET**  - Find a user by ID.
```bash
/getUserById/{userId}
``` 
