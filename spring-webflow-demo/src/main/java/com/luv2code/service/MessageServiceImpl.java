package com.luv2code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.dao.MessageDAO;
import com.luv2code.entity.Messages;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageDAO messageDAO;

	@Override
	public List<Messages> getMessages() {
		return messageDAO.getMessages();
	}

	@Override
	public Messages getMessageById(int id) {
		return messageDAO.getMessageById(id);
	}

	@Override
	public void saveMessage(Messages message) {
		messageDAO.saveMessage(message);
	}

	@Override
	public void deleteMessage(int id) {
		messageDAO.deleteMessage(id);
	}

}
