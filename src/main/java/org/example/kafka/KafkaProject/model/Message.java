package org.example.kafka.KafkaProject.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {

	private String winner;
	private String loser;
	private Date date;
	
	public Message() {
		
	}
	public Message(String winner, String loser) {
		super();
		this.winner = winner;
		this.loser = loser;
		this.date = new Date();
	}
	public Message(String winner, String loser, Date date) {
		super();
		this.winner = winner;
		this.loser = loser;
		this.date = date;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public String getLoser() {
		return loser;
	}
	public void setLoser(String loser) {
		this.loser = loser;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
