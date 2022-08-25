package com.DemoAccount.account.Extension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.DemoAccount.account.Repository.MessageRepository;


@Component
public class HandleMessage {
	
	@Autowired
	private MessageRepository messageRepository;
	
	public String existLicenceNumber() {
		return messageRepository.findMessageById(1);
	}
	
	public String duplicateAccountType() {
		return messageRepository.findMessageById(2);
	}

}
