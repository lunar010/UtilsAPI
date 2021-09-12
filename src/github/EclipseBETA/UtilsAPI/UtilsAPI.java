package github.EclipseBETA.UtilsAPI;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class UtilsAPI {

	/*
	 * @author EclipseBETA
	 */

	private static String ip;
	private static String[] ip2;
	
	public static String getColor(int Color) {
		return "¡×" + Color;
	}
	
	public static String getVersion() {
		return "BETA 1.0";
	}
	
	public static boolean Test() {
		return true;
	}
	
	public static String getIPPort(Player p) {
		if(p.isOnline()){
            ip = p.getPlayer().getAddress().toString().replaceAll("/", "");
        }
		return ip;
	}
	
	public static String getIPOnly(Player p) {
		if(p.isOnline()) {
			ip2 = p.getPlayer().getAddress().toString().replaceAll("/", "").split(":");
		}
		return ip2[0];
	}
	
	public static boolean checkOnline(Player p) {
		if (!p.isOnline()) {
			return false;
		}
		return true;
	}
	
	public static int getRandom(int lower, int upper) {
        Random random = new Random();
        return random.nextInt((upper - lower) + 1) + lower;
    }
	
	public static UUID getUUID(Player p) {
		return p.getUniqueId();
	}
	
	public static String getCustomName(Player p) {
		return p.getCustomName();
	}
	
	public static String getName(Player p) {
		return p.getDisplayName();
	}
	
	public static void setHealth(Player p, int Health) {
		p.setHealth(Health / 2);
	}
	
	public static void setFlySpeed(Player p, int Speed) {
		p.setFlySpeed(Speed);
	}
	
	public static void AllowFly(Player p, boolean AllowFly) {
		p.setAllowFlight(AllowFly);
	}


	public enum ServerVersion {

	    v1_8(18),
	    v1_9(19),
	    v1_10(110),
	    v1_11(111),
	    v1_12(112),
	    v1_13(113),
	    v1_14(114),
	    v1_15(115),
	    v1_16(116),
	    v1_17(117);
	
	    private static final ServerVersion currentVersion;
	    private static final String bukkitVersion;
	    private static final boolean legacy;
	
	    static {
	        bukkitVersion = Bukkit.getBukkitVersion().split("-")[0];
	        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	        String[] sections = version.split("_");;
	        currentVersion = ServerVersion.valueOf(sections[0] + "_" + sections[1]);
	        legacy = isLessThan(ServerVersion.v1_13);
	    }
	
	    private final int code;
	
	    ServerVersion(int code){
	        this.code = code;
	    }
	
	
	    public static boolean isAtLeast(ServerVersion serverVersion){
	        return currentVersion.code >= serverVersion.code;
	    }
	
	    public static boolean isLessThan(ServerVersion serverVersion){
	        return currentVersion.code < serverVersion.code;
	    }
	
	    public static boolean isEquals(ServerVersion serverVersion){
	        return currentVersion.code == serverVersion.code;
	    }
	
	    public static boolean isLegacy(){
	        return legacy;
	    }
	
	    public static String getBukkitVersion(){
	        return bukkitVersion;
	    }
	
	    public static ServerVersion[] getByOrder(){
	        ServerVersion[] versions = Arrays.copyOfRange(values(), 0, currentVersion.ordinal() + 1);
	
	        for(int i = 0; i < versions.length / 2; i++){
	            ServerVersion temp = versions[i];
	            versions[i] = versions[versions.length - i - 1];
	            versions[versions.length - i - 1] = temp;
	        }
	
	        return versions;
	    }

}
	
	public static String getLocation(String location){
        try {
            if (location == null || location.isEmpty())
                return null;

            String[] sections = location.split(",");

            double x = Double.parseDouble(sections[1]);
            double y = Double.parseDouble(sections[2]);
            double z = Double.parseDouble(sections[3]);
            float yaw = sections.length > 5 ? Float.parseFloat(sections[4]) : 0;
            float pitch = sections.length > 4 ? Float.parseFloat(sections[5]) : 0;

            return sections[0] + " " + x + " " + y + " " + z + " " + yaw + " " + pitch;
        }catch(Exception ex){
            throw ex;
        }
    }

    public static String getLocation(Location location){
        return location == null ? "" : location.getWorld().getName() + "," + location.getX() + "," + location.getY() + "," + location.getZ() + "," + location.getYaw() + "," + location.getPitch();
    }

    public static Location getRelative(Location location, BlockFace face){
        return location.clone().add(face.getModX(), face.getModY(), face.getModZ());
    }

    public static Location getBlockLocation(Location location){
        return new Location(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
	
	public static class DiscordWebhook {
		private final String url;
	    private String content;
	    private String username;
	    private String avatarUrl;
	    private boolean tts;
	    private List<EmbedObject> embeds = new ArrayList<>();

	    public DiscordWebhook(String url) {
	        this.url = url;
	    }

	    public void setContent(String content) {
	        this.content = content;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public void setAvatarUrl(String avatarUrl) {
	        this.avatarUrl = avatarUrl;
	    }

	    public void setTts(boolean tts) {
	        this.tts = tts;
	    }

	    public void addEmbed(EmbedObject embed) {
	        this.embeds.add(embed);
	    }

	    public void execute() throws IOException {
	        if (this.content == null && this.embeds.isEmpty()) {
	            throw new IllegalArgumentException("Set content or add at least one EmbedObject");
	        }

	        JSONObject json = new JSONObject();

	        json.put("content", this.content);
	        json.put("username", this.username);
	        json.put("avatar_url", this.avatarUrl);
	        json.put("tts", this.tts);

	        if (!this.embeds.isEmpty()) {
	            List<JSONObject> embedObjects = new ArrayList<>();

	            for (EmbedObject embed : this.embeds) {
	                JSONObject jsonEmbed = new JSONObject();

	                jsonEmbed.put("title", embed.getTitle());
	                jsonEmbed.put("description", embed.getDescription());
	                jsonEmbed.put("url", embed.getUrl());

	                if (embed.getColor() != null) {
	                    Color color = embed.getColor();
	                    int rgb = color.getRed();
	                    rgb = (rgb << 8) + color.getGreen();
	                    rgb = (rgb << 8) + color.getBlue();

	                    jsonEmbed.put("color", rgb);
	                }

	                EmbedObject.Footer footer = embed.getFooter();
	                EmbedObject.Image image = embed.getImage();
	                EmbedObject.Thumbnail thumbnail = embed.getThumbnail();
	                EmbedObject.Author author = embed.getAuthor();
	                List<EmbedObject.Field> fields = embed.getFields();

	                if (footer != null) {
	                    JSONObject jsonFooter = new JSONObject();

	                    jsonFooter.put("text", footer.getText());
	                    jsonFooter.put("icon_url", footer.getIconUrl());
	                    jsonEmbed.put("footer", jsonFooter);
	                }

	                if (image != null) {
	                    JSONObject jsonImage = new JSONObject();

	                    jsonImage.put("url", image.getUrl());
	                    jsonEmbed.put("image", jsonImage);
	                }

	                if (thumbnail != null) {
	                    JSONObject jsonThumbnail = new JSONObject();

	                    jsonThumbnail.put("url", thumbnail.getUrl());
	                    jsonEmbed.put("thumbnail", jsonThumbnail);
	                }

	                if (author != null) {
	                    JSONObject jsonAuthor = new JSONObject();

	                    jsonAuthor.put("name", author.getName());
	                    jsonAuthor.put("url", author.getUrl());
	                    jsonAuthor.put("icon_url", author.getIconUrl());
	                    jsonEmbed.put("author", jsonAuthor);
	                }

	                List<JSONObject> jsonFields = new ArrayList<>();
	                for (EmbedObject.Field field : fields) {
	                    JSONObject jsonField = new JSONObject();

	                    jsonField.put("name", field.getName());
	                    jsonField.put("value", field.getValue());
	                    jsonField.put("inline", field.isInline());

	                    jsonFields.add(jsonField);
	                }

	                jsonEmbed.put("fields", jsonFields.toArray());
	                embedObjects.add(jsonEmbed);
	            }

	            json.put("embeds", embedObjects.toArray());
	        }

	        URL url = new URL(this.url);
	        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
	        connection.addRequestProperty("Content-Type", "application/json");
	        connection.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Gelox_");
	        connection.setDoOutput(true);
	        connection.setRequestMethod("POST");

	        OutputStream stream = connection.getOutputStream();
	        stream.write(json.toString().getBytes());
	        stream.flush();
	        stream.close();

	        connection.getInputStream().close(); //I'm not sure why but it doesn't work without getting the InputStream
	        connection.disconnect();
	    }

	    public class EmbedObject {
	        private String title;
	        private String description;
	        private String url;
	        private Color color;

	        private Footer footer;
	        private Thumbnail thumbnail;
	        private Image image;
	        private Author author;
	        private List<Field> fields = new ArrayList<>();

	        public String getTitle() {
	            return title;
	        }

	        public String getDescription() {
	            return description;
	        }

	        public String getUrl() {
	            return url;
	        }

	        public Color getColor() {
	            return color;
	        }

	        public Footer getFooter() {
	            return footer;
	        }

	        public Thumbnail getThumbnail() {
	            return thumbnail;
	        }

	        public Image getImage() {
	            return image;
	        }

	        public Author getAuthor() {
	            return author;
	        }

	        public List<Field> getFields() {
	            return fields;
	        }

	        public EmbedObject setTitle(String title) {
	            this.title = title;
	            return this;
	        }

	        public EmbedObject setDescription(String description) {
	            this.description = description;
	            return this;
	        }

	        public EmbedObject setUrl(String url) {
	            this.url = url;
	            return this;
	        }

	        public EmbedObject setColor(Color color) {
	            this.color = color;
	            return this;
	        }

	        public EmbedObject setFooter(String text, String icon) {
	            this.footer = new Footer(text, icon);
	            return this;
	        }

	        public EmbedObject setThumbnail(String url) {
	            this.thumbnail = new Thumbnail(url);
	            return this;
	        }

	        public EmbedObject setImage(String url) {
	            this.image = new Image(url);
	            return this;
	        }

	        public EmbedObject setAuthor(String name, String url, String icon) {
	            this.author = new Author(name, url, icon);
	            return this;
	        }

	        public EmbedObject addField(String name, String value, boolean inline) {
	            this.fields.add(new Field(name, value, inline));
	            return this;
	        }

	        private class Footer {
	            private String text;
	            private String iconUrl;

	            private Footer(String text, String iconUrl) {
	                this.text = text;
	                this.iconUrl = iconUrl;
	            }

	            private String getText() {
	                return text;
	            }

	            private String getIconUrl() {
	                return iconUrl;
	            }
	        }

	        private class Thumbnail {
	            private String url;

	            private Thumbnail(String url) {
	                this.url = url;
	            }

	            private String getUrl() {
	                return url;
	            }
	        }

	        private class Image {
	            private String url;

	            private Image(String url) {
	                this.url = url;
	            }

	            private String getUrl() {
	                return url;
	            }
	        }

	        private class Author {
	            private String name;
	            private String url;
	            private String iconUrl;

	            private Author(String name, String url, String iconUrl) {
	                this.name = name;
	                this.url = url;
	                this.iconUrl = iconUrl;
	            }

	            private String getName() {
	                return name;
	            }

	            private String getUrl() {
	                return url;
	            }

	            private String getIconUrl() {
	                return iconUrl;
	            }
	        }

	        private class Field {
	            private String name;
	            private String value;
	            private boolean inline;

	            private Field(String name, String value, boolean inline) {
	                this.name = name;
	                this.value = value;
	                this.inline = inline;
	            }

	            private String getName() {
	                return name;
	            }

	            private String getValue() {
	                return value;
	            }

	            private boolean isInline() {
	                return inline;
	            }
	        }
	    }

	    private class JSONObject {

	        private final HashMap<String, Object> map = new HashMap<>();

	        void put(String key, Object value) {
	            if (value != null) {
	                map.put(key, value);
	            }
	        }

	        @Override
	        public String toString() {
	            StringBuilder builder = new StringBuilder();
	            Set<Map.Entry<String, Object>> entrySet = map.entrySet();
	            builder.append("{");

	            int i = 0;
	            for (Map.Entry<String, Object> entry : entrySet) {
	                Object val = entry.getValue();
	                builder.append(quote(entry.getKey())).append(":");

	                if (val instanceof String) {
	                    builder.append(quote(String.valueOf(val)));
	                } else if (val instanceof Integer) {
	                    builder.append(Integer.valueOf(String.valueOf(val)));
	                } else if (val instanceof Boolean) {
	                    builder.append(val);
	                } else if (val instanceof JSONObject) {
	                    builder.append(val.toString());
	                } else if (val.getClass().isArray()) {
	                    builder.append("[");
	                    int len = Array.getLength(val);
	                    for (int j = 0; j < len; j++) {
	                        builder.append(Array.get(val, j).toString()).append(j != len - 1 ? "," : "");
	                    }
	                    builder.append("]");
	                }

	                builder.append(++i == entrySet.size() ? "}" : ",");
	            }

	            return builder.toString();
	        }

	        private String quote(String string) {
	            return "\"" + string + "\"";
	        }
	    }
	}
	
}
