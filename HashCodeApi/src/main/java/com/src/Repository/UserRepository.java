package com.src.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.src.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

}
