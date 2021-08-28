# Utils
- DiscordWebhookUtils
  - ```public String webhookURL = "Webhook URL";
DiscordWebhook webhook = new DiscordWebhook(webhookURL);
		webhook.addEmbed(new DiscordWebhook.EmbedObject()
				.setDescription("Test"));
		try {
			webhook.execute();
		}
		catch(java.io.IOException e) {
			getLogger().severe(e.getStackTrace().toString());
		}```
