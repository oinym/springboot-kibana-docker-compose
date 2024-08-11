package com.example.accessingdatamysql.jparepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.accessingdatamysql.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
