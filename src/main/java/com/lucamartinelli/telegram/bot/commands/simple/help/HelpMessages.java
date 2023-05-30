package com.lucamartinelli.telegram.bot.commands.simple.help;

public class HelpMessages {
	private HelpMessages() {
		// TODO Auto-generated constructor stub
	}
	
	
	public final static String DEFAULT = "Comando %s non trovato";
	
	
	public final static String TEST = "Uso di debug, questo comando è utilizzato solo nei test dallo <i>sviluppatore</i>";
	
	public final static String START = "Semplice comando di controllo.\n"
			+ "<b>Paramentri</b> nessuno.\n"
			+ "<b>Ritorna</b> un semplice messaggio per indicare che il bot è attivo.";
	
	public final static String PERSONALINFO = "Questo comando recupera le informazioni legate all'utente con cui sta parlando.\n"
			+ "<b>Paramentri</b> nessuno.\n"
			+ "<b>Ritorna</b> un messaggio contenente le informazioni personali dell'utente.";
	
	public final static String HELP = "Mostra la lista dei comandi con una descrizione breve, se specificato il comando nel paramentro"
			+ "ritorna le informazioni dettagliate su quello (Come in questo caso).\n"
			+ "<b>Paramentri</b> 1 <i>(Opzionale)</i> il comando che si vuole approfondire\n"
			+ "<b>Ritorna</b> La lista dei comandi o la descrizione estesa di quello specificato."
			+ "<b>Esempio</b>\n"
			+ "/help help.";
	
	public final static String BOTINFO = "Mostra le info personali del bot.\n"
			+ "<b>Paramentri</b> nessuno.\n"
			+ "<b>Ritorna</b> un semplice messaggio con le informazioni del bot Ellie.";
	
	public final static String ASCIIART = "Ritorna una serie di caratteri che formano un'"
			+ "immagine, viene in gergo chiamata asciiart (https://it.wikipedia.org/wiki/ASCII_art).\n"
			+ "<b>Paramentri</b> nessuno.\n"
			+ "<b>Ritorna</b> l'immagine formata dai caratteri.";
	
	public final static String BATTUTA = "Ritorna una barzelletta casuale nel repertorio di Ellie.\n"
			+ "<b>Paramentri</b> nessuno.\n"
			+ "<b>Ritorna</b> il testo della barzelletta.";
	
	public final static String PERLA = "Ritorna un'aforisma casuale dal reportorio di Ellie.\n"
			+ "<b>Paramentri</b> nessuno.\n"
			+ "<b>Ritorna</b> la perla di saggezza.";
	
	public final static String STICKER = "Ritorna uno sticker casuale.\n"
			+ "<b>Paramentri</b> nessuno.\n"
			+ "<b>Ritorna</b> lo sticker.";
	
	public final static String ORA = "Ritorna l'ora attuale in una delle città disponibili.\n"
			+ "<b>Paramentri</b> nessuno.\n"
			+ "<b>Ritorna</b> prima la lista delle città e poi l'orario.";
	
	public final static String CALC = "Avvia la funzione di calcolatrice e ritorna un messaggio"
			+ " con le operazioni disponibili, per più info scegli un operazione.\n"
			+ "<b>Paramentri</b> nessuno.\n"
			+ "<b>Ritorna</b> la descrizione del comando ed entra in modalità calcolatrice.";
	
	public final static String MATH_SUM = "Somma 2 o più numeri e restituisce il risultato.\n"
			+ "Separa i numeri con lo spazio, usa il carattere punto o virgola per indicare"
			+ " i decimali.\n"
			+ "<b>Paramentri</b> 2 o più <i>(Obbligatori)</i> numeri per la somma.\n"
			+ "<b>Ritorna</b> La somma dei numeri inseriti.\n"
			+ "<b>Esempio</b>\n"
			+ "/+ 2 5 3.2 2.6";
	
	public final static String MATH_DIFF = "Sottrae 2 numeri e restituisce il risultato.\n"
			+ "Separa i numeri con lo spazio, usa il carattere punto o virgola per indicare"
			+ " i decimali.\n"
			+ "<b>Paramentri</b> 2 <i>(Obbligatori)</i> numeri per la differenza.\n"
			+ "<b>Ritorna</b> La differenza dei numeri inseriti.\n"
			+ "<b>Esempio</b>\n"
			+ "/- 5 3.2";
	
	public final static String MATH_PRODUCT = "Moltiplica 2 o più numeri e restituisce il risultato.\n"
			+ "Separa i numeri con lo spazio, usa il carattere punto o virgola per indicare"
			+ " i decimali.\n"
			+ "<b>Paramentri</b> 2 o più <i>(Obbligatori)</i> numeri per la moltiplicazione.\n"
			+ "<b>Ritorna</b> Il prodotto dei numeri inseriti.\n"
			+ "<b>Esempio</b>\n"
			+ "/x 2 5 3.2 2.6";
	
	public final static String MATH_DIV = "divide 2 numeri e restituisce il risultato.\n"
			+ "Separa i numeri con lo spazio, usa il carattere punto o virgola per indicare"
			+ " i decimali.\n"
			+ "<b>Paramentri</b> 2 <i>(Obbligatori)</i> numeri per la divisione.\n"
			+ "<b>Ritorna</b> Il quoziente dei numeri inseriti.\n"
			+ "<b>Esempio</b>\n"
			+ "/: 2 5 3.2 2.6";
	
	public final static String SQRT = "Esegue la radice quadrata di un numero e restituisce il risultato.\n"
			+ "Usa il carattere punto o virgola per indicare i decimali.\n"
			+ "<b>Paramentri</b> 1 <i>(Obbligatorio)</i> numero per la radice quadrata.\n"
			+ "<b>Ritorna</b> Il numero risultante della radice quadrata sul radicando.\n"
			+ "<b>Esempio</b>\n"
			+ "/sqrt 2 5 3.2 2.6";
	
	public final static String RAND = "Genera un numero casuale partendo da 0 fino al limite "
			+ "inserito da parametro, inclusivo di esso.\n"
			+ "<b>Paramentri</b> 1 <i>(Obbligatorio)</i> numero che definisce il limite massimo"
			+ " (inclusivo).\n"
			+ "<b>Ritorna</b> il numero casuale da 0 al limite inserito."
			+ "<b>Esempio</b>\n"
			+ "/rand 6";
	
	public final static String MEDIA = "Calcola la media dei numeri inseriti.\n"
			+ "<b>Paramentri</b> 2 o più <i>(Obbligatori)</i> numeri per calcolare la media .\n"
			+ "<b>Ritorna</b> La media dei numeri inseriti."
			+ "<b>Esempio</b>\n"
			+ "/media 2 5 3.2 2.6";
	
	public final static String LETTERARAND = "Genera casualmente una lettera dell'alfabero da A"
			+ " a Z.\n"
			+ "<b>Paramentri</b> nessuno.\n"
			+ "<b>Ritorna</b> Una lettera casuale da 'A' a 'Z'.";
	
	public final static String MONETA = "Lancia una moneta e restituisce TESTA o CROCE (Attenzione"
			+ ": non considerare l'emoji poiché Telegram non gestisce la casualità, leggi "
			+ "quello che dice il messaggio).\n"
			+ "<b>Paramentri</b> nessuno.\n"
			+ "<b>Ritorna</b> TESTA o CROCE... quasi sempre.";
		
	public final static String DADO = "Lancia un D6 e restituisce il valore.\n"
			+ "<b>Paramentri</b> nessuno.\n"
			+ "<b>Ritorna</b> il valore del D6.";
		
	public final static String LOGOUT = "Pulisce la sessione dell'utente loggato (Ritorni "
			+ "non loggato).\n"
			+ "<b>Paramentri</b> nessuno, verrà richiesta la password nello step successivo.\n"
			+ "<b>Ritorna</b> un messaggio di cortesia per informare l'avvenuto logout.";
	
	public final static String USER = "Avvia la richiesta di login per il ruolo USER"
			+ " (Richiede una password) - la sessione ha una scadenza per inattività.\n"
			+ "<b>Paramentri</b> nessuno, verrà richiesta la password nello step successivo.\n"
			+ "<b>Ritorna</b> Il messaggio se la password è stata accettata o meno.";
	
	public final static String USERHELP = "Mostra la lista dei comandi disponibili per utenti "
			+ "loggati.\n"
			+ "<b>Paramentri</b> nessuno.\n"
			+ "<b>Ritorna</b> la lista dei comandi.";
	
	public final static String MYLADY  = "Esegui l'accesso come MYLADY, richiede password.\n"
			+ "<b>Paramentri</b> nessuno, verrà chiesta la password nello step successivo.\n"
			+ "<b>Ritorna</b> un messaggio di cortesia per indicare se la password è corretta o no.";
	
	public final static String MYLADYHELP = "Mostra la lista dei comandi esclusivi di MYLADY.\n"
			+ "<b>Paramentri</b> nessuno.\n"
			+ "<b>Ritorna</b> la lista dei comandi da MYLADY.";
	
	public final static String MYLADYIMAGE = "Ritorna una foto casuale esclusiva per il ruolo "
			+ "MYLADY.\n"
			+ "<b>Paramentri</b> nessuno.\n"
			+ "<b>Ritorna</b> una foto casuale esclusiva per MYLADY.";
	
	public final static String LOVE = "Mostra una foto casuale e personale dal repertorio"
			+ " di Ellie, disponibile solo per ADMIN o MYLADY.\n"
			+ "<b>Paramentri</b> nessuno.\n"
			+ "<b>Ritorna</b> una foto casuale e personale.";
	
	public final static String ADMIN = "Esegui l'accesso come ADMIN, è necessaria la password.\n"
			+ "<b>Paramentri</b> nessuno, verrà richiesta la password nello step successivo.\n"
			+ "<b>Ritorna</b> un messaggio di cortesia per notificare l'avvenuta login o meno.";
	
	public final static String ADMINHELP = "Mostra la lista dei comandi esclusivi di ADMIN.\n"
			+ "<b>Paramentri</b> nessuno.\n"
			+ "<b>Ritorna</b> la lista dei comandi da ADMIN.";
	
	public final static String SHUTDOWN = "Avvia lo spegnimento di Ellie, disponibile solo per"
			+ "ruoli ADMIN e MYLADY.\n"
			+ "<b>Paramentri</b> nessuno, verrà chiesta conferma in uno step successivo.\n"
			+ "<b>Ritorna</b> .";
	
	public final static String POSTINO = "Invia un messaggio ad un altro utente, richiede "
			+ "l'accesso da USER, MYLADY o ADMIN.\n"
			+ "Ellie aggiungerà un'intestazione al messaggio indicando il mittente.\n"
			+ "<b>Paramentri</b> nessuno, verranno richieste informazioni aggiuntive "
			+ "negli step successivi.\n"
			+ "<b>Ritorna</b> un messaggio di cortesia quando viene recapitato il messaggio "
			+ "all'utente selezionato.";
	
	public final static String SYSTEM = "Invia un messaggio come con /postino, ma 'pulito'. "
			+ "Cioè senza l'intestazione del mittente, quel che scrivi sarà il messaggio.\n"
			+ "Disponibile solo per MYLADY e ADMIN.\n"
			+ "<b>Paramentri</b> nessuno, verranno richieste informazioni aggiuntive "
			+ "negli step successivi.\n"
			+ "<b>Ritorna</b> un messaggio di cortesia quando viene recapitato il messaggio "
			+ "all'utente selezionato.";
	
	public final static String GETLOG = "Carica le ultime righe del log e li spedisce.\n"
			+ "Disponibile solo per ADMIN.\n"
			+ "<b>Paramentri</b> 1 <i>Opzionale</i> 'file' se si vuole il file come txt.\n"
			+ "<b>Ritorna</b> le ultime righe dei log.\n"
			+ "<b>Esempio</b>\n"
			+ "/getlog file";
	
	public final static String GETELLIEINFO = "Comando riservato ad ADMIN, consente di visualizzare"
			+ " informazioni di esecuzione del programma e della sessione attiva su Ellie.\n"
			+ "<b>Paramentri</b> 1 <i>(Opzionale)</i> 'chatcache' se si vuole ricevere "
			+ "il dump della cache delle chat.\n"
			+ "<b>Ritorna</b> Informazioni sulla sessione di Ellie.";
	
	public final static String startrandomevent = ".\n"
			+ "<b>Paramentri</b> .\n"
			+ "<b>Ritorna</b> .";
	
	public final static String METEO = "Controlla e fornisce le info del meteo in una città "
			+ "selezionabile in quelle disponibili (dati sorgenti da ilmeteo.it).\n"
			+ "<b>Paramentri</b> nessuno, verrà richiesta la città nello step successivo.\n"
			+ "<b>Ritorna</b> un messaggio con le informazioni meteorologiche della città "
			+ "selezionata.";
	
	public final static String storia = ".\n"
			+ "<b>Paramentri</b> .\n"
			+ "<b>Ritorna</b> .";
	
	public final static String manual = ".\n"
			+ "<b>Paramentri</b> .\n"
			+ "<b>Ritorna</b> .";
	
	public final static String impiccato = ".\n"
			+ "<b>Paramentri</b> .\n"
			+ "<b>Ritorna</b> .";
	
	public final static String blackjack = ".\n"
			+ "<b>Paramentri</b> .\n"
			+ "<b>Ritorna</b> .";
	
	public final static String battaglianavale = ".\n"
			+ "<b>Paramentri</b> .\n"
			+ "<b>Ritorna</b> .";
	
	public final static String sassocartaforbice = ".\n"
			+ "<b>Paramentri</b> .\n"
			+ "<b>Ritorna</b> .";
	
	public final static String catsimulator = ".\n"
			+ "<b>Paramentri</b> .\n"
			+ "<b>Ritorna</b> .";
	
	public final static String storygame = ".\n"
			+ "<b>Paramentri</b> .\n"
			+ "<b>Ritorna</b> .";
	
	public final static String foto = ".\n"
			+ "<b>Paramentri</b> .\n"
			+ "<b>Ritorna</b> .";
	
	public final static String musica = ".\n"
			+ "<b>Paramentri</b> .\n"
			+ "<b>Ritorna</b> .";
}
