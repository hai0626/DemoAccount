package com.DemoAccount.account.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.DemoAccount.account.Model.ErrorMessage;



@Repository
public interface MessageRepository extends JpaRepository<ErrorMessage, Integer> {

	@Query("SELECT m.Description FROM ErrorMessage m WHERE m.id = :id")
	String findMessageById( int id);

}
