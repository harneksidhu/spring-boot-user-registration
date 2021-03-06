package com.user.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.registration.model.User;


public interface UserRepository extends JpaRepository<User, Long>{
	
	User findOneByUserNameAndPassword(String userName, String password);
	User findOneByUserName(String userName);

}
