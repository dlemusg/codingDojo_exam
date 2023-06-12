package com.example.LoginRegister.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.LoginRegister.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	

}
