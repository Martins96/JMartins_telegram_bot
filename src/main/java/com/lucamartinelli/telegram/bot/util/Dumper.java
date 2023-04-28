package com.lucamartinelli.telegram.bot.util;

import java.time.Instant;
import java.time.ZoneId;

import com.pengrad.telegrambot.model.Update;

public class Dumper {

	public static String dump(final Update update) {
		final StringBuffer sb = new StringBuffer("Update:");
		if (update == null) {
			sb.append(" NULL");
			return sb.toString();
		}
		
		
		sb.append("\nupdateId: " + update.updateId())
			.append("\n  ChatMemeber:");
		if (update.chatMember() == null)
			sb.append(" NULL");
		else {
			sb.append("\n    Date: " + update.chatMember().date())
			  .append("\n    User:");
			if (update.chatMember().from() == null)
				sb.append(" NULL");
			else
				sb.append("\n      Username:   " + update.chatMember().from().username())
				  .append("\n      ID:         " + update.chatMember().from().id())
				  .append("\n      FirstName:  " + update.chatMember().from().firstName())
				  .append("\n      LastName:   " + update.chatMember().from().lastName())
				  .append("\n      IsBot:      " + update.chatMember().from().isBot())
				  .append("\n      IsPremium:  " + update.chatMember().from().isPremium());
			sb.append("\n    Chat:");
			if (update.chatMember().chat() == null)
				sb.append(" NULL");
			else
				sb.append("\n      Bio:        " + update.chatMember().chat().bio())
	  			  .append("\n      Description:" + update.chatMember().chat().description())
				  .append("\n      ID:         " + update.chatMember().chat().id())
				  .append("\n      Title:      " + update.chatMember().chat().title())
				  .append("\n      FirstName:  " + update.chatMember().chat().firstName())
				  .append("\n      LastName:   " + update.chatMember().chat().lastName());
		}
		
		sb.append("\n  EditedMessage:");
		if (update.editedMessage() == null)
			sb.append(" NULL");
		else {
			sb.append("\n    Text:       " + update.editedMessage().text())
			  .append("\n    EditedDate: " + update.editedMessage().editDate())
			  .append("\n    MessageID:  " + update.editedMessage().messageId())
			  .append("\n    Chat:       ");
			if (update.editedMessage().chat() == null)
				sb.append(" NULL");
			else
				sb.append("\n      Username: " + update.editedMessage().chat().username())
				  .append("\n      ID:       " + update.editedMessage().chat().id());
		}
		sb.append("\n  Message:");
		if (update.message() == null)
			sb.append(" NULL");
		else {
			sb.append("\n    MessageID:  " + update.message().messageId())
			  .append("\n    Text:       " + update.message().text())
			  .append("\n    Date:       ");
			if (update.message().date() == null)
				sb.append(" NULL");
			else
				sb.append(Instant.ofEpochSecond(update.message().date()).atZone(ZoneId.of("Europe/Rome")).toString());
			sb.append("\n    Forward:    " + update.message().forwardSenderName());
			
			sb.append("\n    Sticker:");
			if (update.message().sticker() == null)
				sb.append(" NULL");
			else {
				sb.append("\n      Emoji:      " + update.message().sticker().emoji())
				  .append("\n      FileId:     " + update.message().sticker().fileId())
				  .append("\n      CustomEmoji:" + update.message().sticker().customEmojiId())
				  .append("\n      isAnimated: " + update.message().sticker().isAnimated())
				  .append("\n      SetName:    " + update.message().sticker().setName());
			}
			
			
			sb.append("\n    User:");
			if (update.message().from() == null)
				sb.append(" NULL");
			else
				sb.append("\n      Username:   " + update.message().from().username())
				  .append("\n      ID:         " + update.message().from().id())
				  .append("\n      FirstName:  " + update.message().from().firstName())
				  .append("\n      LastName:   " + update.message().from().lastName())
				  .append("\n      IsBot:      " + update.message().from().isBot())
				  .append("\n      IsPremium:  " + update.message().from().isPremium());
		}
		
		
		
		
		
		return sb.toString();
	}

}
