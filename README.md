# API
- Get API Version
  - ```java
  	UtilsAPI.getVersion(); // get version of UtilsAPI
- Test UtilsAPI
  - ```java
  	UtilsAPI.Test(); // return true when UtilsAPI works
# Tested Version
- 1.16.5 (spigot)
- 1.12.2 (spigot)
# Utils
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
- VersionUtil
  - ```java
  	UtilsAPI.ServerVersion.getBukkitVersion(); // get versioin
	UtilsAPI.ServerVersion.getByOrder(); // get version by order
	if (UtilsAPI.ServerVersion.isAtLeast(UtilsAPI.ServerVersion.v1_10)) {
		// at least
	} else if (UtilsAPI.ServerVersion.isEquals(UtilsAPI.ServerVersion.v1_11)) {
		// is equal
	} else if (UtilsAPI.ServerVersion.isLessThan(UtilsAPI.ServerVersion.v1_12)) {
		// less than
	}
- IPUtil
  - ```java
  	UtilsAPI.getIPOnly(e.getPlayer()); // get player's ip only
	UtilsAPI.getIPPort(e.getPlayer()); // get player's ip:port
- UUIDUtil
  - ```java
  	UtilsAPI.getUUID(e.getPlayer()); // get uuid
- RandomeUtil
  - ```java
  	UtilsAPI.getRandom(0, 100); // get randome int between 0 and 100
- ColorUtil
  - ```java
  	UtilsAPI.getColor(0); // return §0
- LocationUtil
  - ```java
  	UtilsAPI.getBlockLocation(null); // on beta
	UtilsAPI.getRelative(null, null);
	UtilsAPI.getLocation(null);
- OnlineUtil
  - ```java
  	UtilsAPI.checkOnline(e.getPlayer()); // check online (true = online / false = offline)
- NameUtil
  - ```java
  	UtilsAPI.getName(e.getPlayer()); // get payer's display name
  	UtilsAPI.getCustomName(e.getPlayer()); // get player's custom name
- HealthUtil
  - ```java
  	UtilsAPI.setHealth(e.getPlayer(), 20); // set player's health
- FlyUtil
  - ```java
  	UtilsAPI.AllowFly(e.getPlayer(), true); // allow player fly
  	UtilsAPI.setFlySpeed(e.getPlayer(), 10); // set player's fly speed
