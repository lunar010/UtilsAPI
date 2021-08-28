# Utils
- GetVersion
  - ```java
  	UtilsAPI.getVersion(); // get version of UtilsAPI
- TestUtilsAPI
  - ```java
  	UtilsAPI.Test(); // return true when UtilsAPI works
- IPUtil
  - ```java
  	UtilsAPI.getIPOnly(e.getPlayer()); // get player's ip only
	UtilsAPI.getIPPort(e.getPlayer()); // get player's ip:port
- OnlineUtil
  - ```java
  	UtilsAPI.checkOnline(e.getPlayer()); // check online (true = online / false = offline)
- UUIDUtil
  - ```java
  	UtilsAPI.getUUID(e.getPlayer()); // get uuid
- RandomeUtil
  - ```java
  	UtilsAPI.getRandom(0, 100); // get randome int between 0 and 100
- ColorUtil
  - ```java
  	UtilsAPI.getColor(0); // return §0
- DiscordWebhookUtil
  - ```java
  	public String webhookURL = "Webhook URL";
	UtilsAPI.DiscordWebhook webhook = new UtilsAPI.DiscordWebhook(webhookURL);
	webhook.addEmbed(new UtilsAPI.DiscordWebhook.EmbedObject()
		.setDescription("Test"));
	try {
		webhook.execute();
	}
	catch(java.io.IOException e) {
		getLogger().severe(e.getStackTrace().toString());
	}
