package com.lucamartinelli.telegram.bot.vo;

import java.io.Serializable;
import java.util.Objects;

import com.lucamartinelli.telegram.bot.commands.BotCommand;

public class ChatSession implements Serializable{
	
	private static final long serialVersionUID = 5291262152358516594L;
	
	
	private Long userid;
	private String lastMessageText;
	private Integer lastMessageTime;
	private String username;
	private boolean commandFlowIncomplete;
	private BotCommand processingCommand;
	private boolean commandMultiUser;
	private LoggedRolesEnum loggedinRole;
	
	public ChatSession() {
		this.userid = null;
		this.commandFlowIncomplete = false;
		this.processingCommand = null;
		this.loggedinRole = null;
		this.commandMultiUser = false;
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
	public LoggedRolesEnum getLoggedinRole() {
		return loggedinRole;
	}
	public void setLoggedinRole(LoggedRolesEnum loggedinRole) {
		this.loggedinRole = loggedinRole;
	}
	public boolean isCommandMultiUser() {
		return commandMultiUser;
	}
	public void setCommandMultiUser(boolean commandMultiUser) {
		this.commandMultiUser = commandMultiUser;
	}


	@Override
	public int hashCode() {
		return Objects.hash(commandFlowIncomplete, commandMultiUser, lastMessageText, lastMessageTime, loggedinRole,
				processingCommand, userid, username);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatSession other = (ChatSession) obj;
		return commandFlowIncomplete == other.commandFlowIncomplete && commandMultiUser == other.commandMultiUser
				&& Objects.equals(lastMessageText, other.lastMessageText)
				&& Objects.equals(lastMessageTime, other.lastMessageTime) && loggedinRole == other.loggedinRole
				&& Objects.equals(processingCommand, other.processingCommand) && Objects.equals(userid, other.userid)
				&& Objects.equals(username, other.username);
	}


	@Override
	public String toString() {
		return "ChatSession [userid=" + userid + ", lastMessageText=" + lastMessageText + ", lastMessageTime="
				+ lastMessageTime + ", username=" + username + ", commandFlowIncomplete=" + commandFlowIncomplete
				+ ", processingCommand=" + processingCommand + ", commandMultiUser=" + commandMultiUser
				+ ", loggedinRole=" + loggedinRole + "]";
	}
	
	
}
