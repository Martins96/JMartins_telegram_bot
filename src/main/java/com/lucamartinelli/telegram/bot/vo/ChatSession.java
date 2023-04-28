package com.lucamartinelli.telegram.bot.vo;

import java.io.Serializable;

import com.lucamartinelli.telegram.bot.comands.BotCommand;

public class ChatSession implements Serializable{
	
	private static final long serialVersionUID = 5291262152358516594L;
	
	
	private Long userid;
	private String lastMessageText;
	private Integer lastMessageTime;
	private String username;
	private boolean commandFlowIncomplete;
	private BotCommand processingCommand;
	
	public ChatSession() {
		this.userid = null;
		this.commandFlowIncomplete = false;
		this.processingCommand = null;
	}
	
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getLastMessageText() {
		return lastMessageText;
	}
	public void setLastMessageText(String lastMessageText) {
		this.lastMessageText = lastMessageText;
	}
	public Integer getLastMessageTime() {
		return lastMessageTime;
	}
	public void setLastMessageTime(Integer lastMessageTime) {
		this.lastMessageTime = lastMessageTime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isCommandFlowIncomplete() {
		return commandFlowIncomplete;
	}
	public void setCommandFlowIncomplete(boolean commandFlowIncomplete) {
		this.commandFlowIncomplete = commandFlowIncomplete;
	}
	public BotCommand getProcessingCommand() {
		return processingCommand;
	}
	public void setProcessingCommand(BotCommand processingCommand) {
		this.processingCommand = processingCommand;
	}
	public void resetIncompleteCommand() {
		this.commandFlowIncomplete = false;
		this.processingCommand = null;
	}
	
	
	
	
	
}
