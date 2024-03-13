package kinker31.ImprovedRails;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.item.material.ToolMaterial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.ConfigHandler;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

import java.util.Properties;


public class ImprovedRailsMain implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "Improved Rails";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static int blockID;
	public static int itemID;
	public static int baseMinecartSpeed;
	public static int furnaceMinecartBonus;
	public static boolean OlivineFortune;
	public static int pylonRange;
	static{
		Properties p = new Properties();
		p.setProperty("start_block_ID", "1001");
		p.setProperty("start_item_ID", "128001");
		p.setProperty("base_cart_speed", "1");
		p.setProperty("furnace_bonus", "1");
		p.setProperty("olivine_alloy_bonus", "true");
		p.setProperty("pylon_base_range", "8");
		ConfigHandler c = new ConfigHandler(MOD_ID,p);

		blockID = c.getInt("start_block_id");
		itemID = c.getInt("start_item_ID");
		baseMinecartSpeed = c.getInt("base_cart_speed");
		furnaceMinecartBonus = c.getInt("furnace_bonus");
		OlivineFortune = c.getBoolean("olivine_alloy_bonus");
		pylonRange = c.getInt("pylon_base_range");

		c.updateConfig();
	}
	public static ToolMaterial olivineAlloyTool = new ToolMaterial().setMiningLevel(4);
    @Override
    public void onInitialize() {
		LOGGER.info("Improved Rails are a go!");

    }

	@Override
	public void beforeGameStart() {

	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void onRecipesReady() {

	}
}
