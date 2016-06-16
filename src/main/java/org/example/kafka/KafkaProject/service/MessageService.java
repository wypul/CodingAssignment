package org.example.kafka.KafkaProject.service;

import java.util.ArrayList;
import java.util.List;

import org.example.kafka.KafkaProject.model.Message;

public class MessageService {

	public List<Message> getAllMessages()
	{
		Message m1 = new Message("vipul", "lamba");
		Message m2 = new Message("vartul","yagyank");
		List<Message> list = new ArrayList<Message>();
		list.add(m1);
		list.add(m2);
		return list;
	}
}
