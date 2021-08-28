package github.EclipseBETA.UtilsAPI;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	static Main instance;
	UtilsAPI utilsapi = new UtilsAPI();

	@Override
	public void onLoad() {
		instance = this;
	}

	@Override
	public void onEnable() {
		
	} 
	
}
