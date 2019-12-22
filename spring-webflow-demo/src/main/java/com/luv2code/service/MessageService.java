package com.luv2code.service;

import java.util.List;

import com.luv2code.entity.Messages;

public interface MessageService {

	List<Messages> getMessages();
	
	Messages getMessageById(int id);
	
	void saveMessage(Messages message);
	
	void deleteMessage(int id);
}
