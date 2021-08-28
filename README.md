# Utils
- DiscordWebhookUtil
  - ```public String webhookURL = "Webhook URL";
	UtilsAPI.DiscordWebhook webhook = new UtilsAPI.DiscordWebhook(webhookURL);
	webhook.addEmbed(new UtilsAPI.DiscordWebhook.EmbedObject()
		.setDescription("Test"));
	try {
		webhook.execute();
	}
	catch(java.io.IOException e) {
		getLogger().severe(e.getStackTrace().toString());
	}
- RandomeUtil
  - ```UtilsAPI.getRandom(0, 100);
  	test
