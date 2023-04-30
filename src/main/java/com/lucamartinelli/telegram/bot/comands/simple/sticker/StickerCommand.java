package com.lucamartinelli.telegram.bot.comands.simple.sticker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.lucamartinelli.telegram.bot.comands.BotCommand;
import com.lucamartinelli.telegram.bot.vo.ChatSession;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendSticker;

public class StickerCommand extends BotCommand {

	private final Random random;
	private final List<String> stickersSet;
	
	public StickerCommand(ChatSession chatSession, Update update) {
		super(chatSession, update);
		this.random = new Random();
		this.stickersSet = new ArrayList<>();
		loadStickers();
	}

	@Override
	protected int run() {
		final int randomIndex = random.nextInt(this.stickersSet.size());
		log.debugf("Sending sticker in the %d position", randomIndex);
		final String sticker = this.stickersSet.get(randomIndex);
		log.debugf("Stiker ID: %s", sticker);
		ellie.execute(new SendSticker(chatID, sticker));
		return 0;
	}
	
	
	
	
	
	private void loadStickers() {
		this.stickersSet.add("BQADBAAD7gAD2dcnASLoEU_rmppYAg");
		this.stickersSet.add("BQADBQAD8gAD6QrIA8yQjbIGeR6rAg");
		this.stickersSet.add("BQADBQAEAQAC6QrIA_fbx7d_f4cjAg");
		this.stickersSet.add("BQADBQADGAEAAukKyAOhBlPpuCGV_AI");
		this.stickersSet.add("BQADBQADBgEAAukKyANcSAABrSLaGqcC");
		this.stickersSet.add("BQADBQADJAEAAukKyAPR4zXzJvghrwI");
		this.stickersSet.add("BQADBQADgAEAAukKyAPHm11LojIaRgI");
		this.stickersSet.add("BQADBQADhgEAAukKyAO7pa2cO91V4QI");
		this.stickersSet.add("BQADBQADlAEAAukKyAPivF2FRfkGFwI");
		this.stickersSet.add("BQADBQADkgEAAukKyAME4kAmQgfbrAI");
		this.stickersSet.add("BQADBQADkAEAAukKyAMEtCrx38plRgI");
		this.stickersSet.add("BQADBQADlgEAAukKyAN1vRzdONe0DgI");
		this.stickersSet.add("BQADBAAD2wADK6lrAaFe4OKLI8ZFAg");
		this.stickersSet.add("BQADAQADYQ0AAtpxZgcY9P774--RvwI");
		this.stickersSet.add("BQADAQADZQ0AAtpxZgcQeTYOql6tBwI");
		this.stickersSet.add("BQADAQADfw0AAtpxZgeFIQr_TOT7LAI");
		this.stickersSet.add("BQADAQADew0AAtpxZge7ro9cmFTG9gI");
		this.stickersSet.add("BQADAQADgw0AAtpxZgfxophVv6sK0wI");
		this.stickersSet.add("BQADAQADiQ0AAtpxZgcSrBd02PojDgI");
		this.stickersSet.add("BQADAQADiw0AAtpxZgfCcZ3YJM2mOwI");
		this.stickersSet.add("BQADAQADkQ0AAtpxZgcaqLoIWoMcXwI");
		this.stickersSet.add("BQADAQADow0AAtpxZgcOpxZBY3kyLQI");
		this.stickersSet.add("BQADAQADoQ0AAtpxZgdUxOXI0ntwDgI");
		this.stickersSet.add("BQADAQADrQ0AAtpxZgeTlrYr2iKGGQI");
		this.stickersSet.add("BQADAQADrw0AAtpxZgdfQRGmNamwaQI");
		this.stickersSet.add("BQADBAAD1AIAAkBS9wErp1K1jyDSPgI");
		this.stickersSet.add("BQADBAAD2gIAAkBS9wGE4xGq91pgfQI");
		this.stickersSet.add("BQADBAAD3gIAAkBS9wHZGrB8UUF4JgI");
		this.stickersSet.add("BQADBAAD4AIAAkBS9wGM3ooGqz_wqwI");
		this.stickersSet.add("BQADBAAD1gIAAkBS9wHG6UFYOJJ1WwI");
		this.stickersSet.add("BQADAgADVwADHPyyBIqUWrLFb8tJAg");
		this.stickersSet.add("BQADAgADYgADHPyyBJVafmE_lDGXAg");
		this.stickersSet.add("BQADAgADZAADHPyyBOSgQa6u3uOHAg");
		this.stickersSet.add("BQADAgADawADHPyyBI1iZngvNhe5Ag");
		this.stickersSet.add("BQADAgADdQADHPyyBD6ueNMFuesNAg");
		this.stickersSet.add("BQADAgADOwoAAlOx9wPG1r1eOaCFXAI");
		this.stickersSet.add("BQADAgADQQoAAlOx9wMqGJCy3T3ccQI");
		this.stickersSet.add("BQADAgADWQoAAlOx9wP21B0YhogX2wI");
		this.stickersSet.add("BQADAgADTQoAAlOx9wMVHStWBIPhUAI");
		this.stickersSet.add("BQADAgADywQAAlD_jgMPXL2-jflSpgI");
		this.stickersSet.add("BQADAgAD-gQAAlD_jgOfs5zRuuca2wI");
		this.stickersSet.add("BQADAgAEBQACUP-OAxSaHTa355sWAg");
		this.stickersSet.add("BQADAgADnAcAAlD_jgM7wcGyFuZLZgI");
		this.stickersSet.add("BQADAgADpAcAAlD_jgP-6RPIbPr3EAI");
		this.stickersSet.add("BQADAgADXwAD9XK2ATCMAyCvkwABpQI");
		this.stickersSet.add("BQADAgADZwAD9XK2AWHNKU2RXeWYAg");
		this.stickersSet.add("BQADAgADaQAD9XK2AT3CQzskuCdXAg");
		this.stickersSet.add("BQADAgADdwAD9XK2AYUTGCn4QQZjAg");
		this.stickersSet.add("BQADAgADkQAD9XK2ATU23ug4oTGaAg");
		this.stickersSet.add("BQADBAADQwEAAhA1aAABepIeDEFqgTAC");
		this.stickersSet.add("BQADBAADSQEAAhA1aAABPPN9JxQ5llIC");
		this.stickersSet.add("BQADBAADTQEAAhA1aAABZNXSsVaBVtgC");
		this.stickersSet.add("BQADBAADRQEAAhA1aAABPK8271I3qZYC");
		this.stickersSet.add("BQADBAADSwEAAhA1aAABKP8v9CC8bh4C");
		this.stickersSet.add("BQADBAADOQEAAl1PhAFrGIDpha_IPwI");
		this.stickersSet.add("BQADBAADOwEAAl1PhAGW7Z7l2Sq0-QI");
		this.stickersSet.add("BQADBAADeQEAAl1PhAGLuwZNlAnLjAI");
		this.stickersSet.add("BQADBAADsgEAAl1PhAERiBkAAdjzO0MC");
		this.stickersSet.add("BQADBAAD1gEAAl1PhAGyp-XywDPzGwI");
		this.stickersSet.add("BQADBAADPAIAAl1PhAHukPVPLqMXGwI");
		this.stickersSet.add("BQADBAADhQIAAl1PhAHfvamYNqUfhAI");
		this.stickersSet.add("BQADBAAD1wIAAl1PhAFnyB_mAso_tQI");
		this.stickersSet.add("BQADAgADuwMAAjq5FQLtCYM50dXJZwI");
		this.stickersSet.add("BQADAgADvQMAAjq5FQKDKb_G-aawSAI");
		this.stickersSet.add("BQADAgAD2wMAAjq5FQLvz4A6VMrzbAI");
		this.stickersSet.add("BQADAgAD9QMAAjq5FQJj6cB5_8YQdwI");
		this.stickersSet.add("BQADAgADYwQAAjq5FQIlx3lrKf3_OwI");
		this.stickersSet.add("BQADBAADBgEAAoglHgaitXmZbpgVYwI");
		this.stickersSet.add("BQADBAADNQEAAoglHgZezD5F81c07AI");
		this.stickersSet.add("BQADBAADIwAD8mn8AowUszP1WWC6Ag");
		this.stickersSet.add("BQADBAADKwAD8mn8AvbnNRa7VYPzAg");
		this.stickersSet.add("BQADBAADOwAD8mn8ArQ5HHg0X6bIAg");
		this.stickersSet.add("BQADBAADTQAD8mn8ArEcw8AuENRHAg");
		this.stickersSet.add("BQADBAADcwAD8mn8AiSdKCJ84dLuAg");
		this.stickersSet.add("BQADAgAD9gEAAmqovAHbu2fDRwUazQI");
		this.stickersSet.add("BQADAgAD9gEAAmqovAHbu2fDRwUazQI");
		this.stickersSet.add("BQADAgAD-gEAAmqovAHejSsTY6pMwgI");
		this.stickersSet.add("BQADAQADJCQAAtpxZgdmhymDwaLFqQI");
		this.stickersSet.add("BQADAQADKCQAAtpxZgeQOmBPS3HuiQI");
		this.stickersSet.add("BQADAQADggEAAs2YnAEMxh7Ipgaq1gI");
		this.stickersSet.add("BQADAQADYgADkqHuCe2pMviRqfVfAg");
		this.stickersSet.add("BQADAQADbgADkqHuCUb4-bv7l3arAg");
		this.stickersSet.add("BQADAQADjgADkqHuCUWdeXusAlusAg");
		this.stickersSet.add("BQADAQADBwEAApKh7glfsRgVMu-u9gI");
		this.stickersSet.add("BQADBAADEgADgd1pAAE_e-0mZ5V9oAI");
		this.stickersSet.add("BQADBAADGgADgd1pAAGvNem4KXjA1AI");
		this.stickersSet.add("BQADBAADHgADgd1pAAEvM7KtzKV_QQI");
		this.stickersSet.add("BQADBAADMQADgd1pAAHFqtbabvfrQwI");
		this.stickersSet.add("BQADAgADaAADCuDjC9c_XAlhj58zAg");
		this.stickersSet.add("BQADAgADcgADCuDjC-jcn7dxchEtAg");
		this.stickersSet.add("BQADAgADeAADCuDjC6DgKp6jVwABiQI");
		this.stickersSet.add("BQADAgADigADCuDjC4jRcVNsfqs7Ag");
		this.stickersSet.add("BQADBAADGgADIvv9BfNbjBhDTzY2Ag");
		this.stickersSet.add("BQADBAADNAADIvv9BYbYkBLjBMhPAg");
		this.stickersSet.add("BQADBAADPgADIvv9BbWQGNybWorlAg");
		this.stickersSet.add("BQADBAADagADIvv9BQG_3bDKLYEjAg");
		this.stickersSet.add("BQADBAADfwADIvv9BUkE7ax7g_sWAg");
		this.stickersSet.add("BQADBAADiQADIvv9BeOvnJ_fdoriAg");
		this.stickersSet.add("BQADAQAD4DcAAtpxZgf_DUM0EFZrrQI");
		this.stickersSet.add("BQADAQAD5DcAAtpxZgeLWikvH4iZtAI");
		this.stickersSet.add("BQADAQADCDgAAtpxZgedM39JoDMxZgI");
		this.stickersSet.add("BQADBAAD3QADvHSnAZgyFgvQ2e7aAg");
		this.stickersSet.add("BQADBAADKQEAArx0pwGckd2RIR9e2QI");
		this.stickersSet.add("BQADBAAD6QADvHSnATXSjHK1QU5ZAg");
		this.stickersSet.add("BQADBAAD4QADvHSnAbN4Momc988cAg");
		this.stickersSet.add("BQADBAADAgEAArx0pwHxHOUm5q9-CQI");
		this.stickersSet.add("BQADBAADSwEAArx0pwETVQKhgLyzGwI");
		this.stickersSet.add("BQADBQADTwADd9N_ARt_ESBnxMSdAg");
		this.stickersSet.add("BQADBQADUQADd9N_AbtrwvfyZnDGAg");
		this.stickersSet.add("BQADBQADXwADd9N_AetBZVIK8GtdAg");
		this.stickersSet.add("BQADBQADcAADd9N_AS_NP8kTdEv3Ag");
		this.stickersSet.add("BQADBAAD2wADcKvVBMiG8hx-91aKAg");
		this.stickersSet.add("BQADBAAD5wADcKvVBIXtW0lbpbz_Ag");
		this.stickersSet.add("BQADBAAD8wADcKvVBA02WyF52gABeQI");
		this.stickersSet.add("BQADBAADCwEAAnCr1QTdku9acbiUnAI");
		this.stickersSet.add("BQADBAADfgEAAnCr1QT9Cc4zlN6d6QI");
		this.stickersSet.add("BQADAgADdQADUhThCjDTZAoB9vaZAg");
		this.stickersSet.add("BQADAgADkwADUhThCqNvXk4Zm3WnAg");
		this.stickersSet.add("BQADAgADrgADUhThCnAuDTW07m6AAg");
		this.stickersSet.add("BQADAgADvwADUhThClbGQ4WoILi3Ag");
		this.stickersSet.add("BQADBQAD7wADR5XbAUyOwGk4w8NUAg");
		this.stickersSet.add("BQADBQAD-gADR5XbAevDB_1lf9XCAg");
		this.stickersSet.add("BQADBQAEAQACR5XbAY3a7-LahBlfAg");
		this.stickersSet.add("BQADBQADEAEAAkeV2wEG3d6WNiqtRgI");
		this.stickersSet.add("BQADBQADGAEAAkeV2wGfLxbeAXiNwAI");
		this.stickersSet.add("BQADAgADSgADnNbnCpcZRs41H_lnAg");
		this.stickersSet.add("BQADAgADSAADnNbnCrgCanzqbDzvAg");
		this.stickersSet.add("BQADAgADTAADnNbnCp9iJzzWsxQmAg");
		this.stickersSet.add("BQADAwADPAADpYrrAAEaElCVtq2EPgI");
		this.stickersSet.add("BQADAwADJgADpYrrAAFYIol7Hr5YSAI");
		this.stickersSet.add("BQADAwADTAADpYrrAAFpuax8zNF88AI");
		this.stickersSet.add("BQADAwADSgADpYrrAAHbCv0TLsAb7gI");
		this.stickersSet.add("BQADBAAD4wADtUGvBvzoxImSR4ECAg");
		this.stickersSet.add("BQADBAAD8wADtUGvBot1Qp_6YmbUAg");
		this.stickersSet.add("BQADBAADBAEAArVBrwbFOIgKgFOwsQI");
		this.stickersSet.add("BQADAQADUwADByXfDYpUoao-ElysAg");
		this.stickersSet.add("BQADAQADXQADByXfDfaUz1aqJPciAg");
		this.stickersSet.add("BQADAQADYwADByXfDWPQD3Vb8CyNAg");
		this.stickersSet.add("BQADAQADVQADByXfDbWy3s8dspUTAg");
		this.stickersSet.add("BQADAQADYQADByXfDbkhf9imOWXMAg");
		this.stickersSet.add("BQADAQADjQADByXfDQeeDVGosf6BAg");
		this.stickersSet.add("BQADAQADkQADByXfDfi6ystbRieDAg");
		this.stickersSet.add("BQADAgADVQEAApb6EgVlavUUeCpBUQI");
		this.stickersSet.add("BQADAgADbQEAApb6EgWKBmr2NlmerAI");
		this.stickersSet.add("BQADAgADVQEAApb6EgVlavUUeCpBUQI");
		this.stickersSet.add("BQADAgADwAEAApb6EgX-lTt2Kk7pxQI");
		this.stickersSet.add("BQADAgADxgEAApb6EgXmZiGuKDGElQI");
		this.stickersSet.add("BQADAgADZgADCuDjC0vHsWW4mTy7Ag");
	}

}
