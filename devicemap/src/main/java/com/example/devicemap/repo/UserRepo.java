package com.example.devicemap.repo;

import java.util.Optional;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.devicemap.entity.User;
@Repository
public interface UserRepo extends CassandraRepository<User, String>{

	@Query("Select * from user where username=:username allow filtring")
	User getByRegisteredUser();

	@Query("Select * from user where username=:username allow filtring")
	User getUsername();

	@Query("select username from user where username=:username allow filtering")
	Optional<User> findByUsername(String username);

	
	

	

}
