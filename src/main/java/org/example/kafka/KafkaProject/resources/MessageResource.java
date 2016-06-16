package org.example.kafka.KafkaProject.resources;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.example.kafka.KafkaProject.model.KafkaConsumer;
import org.example.kafka.KafkaProject.model.Message;
import org.example.kafka.KafkaProject.service.MessageService;
import org.json.JSONException;
import org.json.JSONObject;

@Path("messages")
public class MessageResource {

	MessageService messageService = new MessageService();
	private List<Message> list = new ArrayList<Message>();
	
	/*public List<String> getMessages()
	{
		String str="";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("/home/vipul/workspaceluna/KafkaProject/output.txt"));
			System.out.println("Called from api");
			while ((str = br.readLine()) != null) {
				System.out.println(str);
				list.add(str);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}*/
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages1()
	{
		String str="";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("/home/vipul/workspaceluna/KafkaProject/output.txt"));
			System.out.println("Called from api");
			while ((str = br.readLine()) != null) {
				try {
					JSONObject obj = new JSONObject(str);
					Message message = new Message(obj.getString("winner"), obj.getString("loser"));
					list.add(message);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
