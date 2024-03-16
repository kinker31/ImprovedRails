package kinker31.ImprovedRails;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.material.ArmorMaterial;
import net.minecraft.core.item.material.ToolMaterial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.ArmorHelper;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.ItemHelper;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.helper.recipeBuilders.RecipeBuilderBlastFurnace;
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
	public static ToolMaterial olivineAlloyTool = new ToolMaterial()
		.setMiningLevel(4)
		.setDurability(4096)
		.setDamage(4)
		.setEfficiency(4f, 2f);
	public static ArmorMaterial olivineAlloyArmor = ArmorHelper
		.createArmorMaterial(MOD_ID, "olivinealloy", 2048, 100f, 100f, 100f, 100f);
	public static Block olivineAlloyBlock = new BlockBuilder(MOD_ID)
		.setSideTextures("olivinealloyblockside.png")
		.setTopTexture("olivinealloyblocktop.png")
		.setBottomTexture("olivinealloyblockbottom.png")
		.setHardness(5f)
		.setResistance(2000f)
		.addTags(BlockTags.MINEABLE_BY_PICKAXE)
		.build(new Block("block.olivineAlloy", blockID++, Material.metal));

	public static Item rawOlivineAlloy = ItemHelper
		.createItem(MOD_ID, new Item("raw.olivinealloy", itemID++), "raw_olivine_alloy.png");
	public static Item ingotOlivineAlloy = ItemHelper
		.createItem(MOD_ID, new Item("ingot.olivinealloy", itemID++), "olivine_alloy.png");
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
		/* Olivine Alloy Process:
		1. 4 Iron on the corners, 4 Gold on the cardinals, and a Block of Olivine.
		2. Cook the result of Step 1 on a blast furnace.
		3. Profit, or in this case, risk and reward!
		 */
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				"IGI",
				"GOG",
				"IGI")
			.addInput('I', Item.ingotIron)
			.addInput('G', Item.ingotGold)
			.addInput('O', Block.blockOlivine)
			.create("raw_olivine_alloy", rawOlivineAlloy.getDefaultStack());
		RecipeBuilder.BlastFurnace(MOD_ID)
			.setInput(rawOlivineAlloy)
			.create("olivine_alloy", ingotOlivineAlloy.getDefaultStack());

		RecipeBuilder.Shaped(MOD_ID)
			.setShape(
				"OOO",
				"OOO",
				"OOO")
			.addInput('O', ingotOlivineAlloy)
			.create("block_of_olivine_alloy", olivineAlloyBlock.getDefaultStack());
	}
}
