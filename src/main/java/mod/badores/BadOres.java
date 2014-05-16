package mod.badores;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import mod.badores.entities.EntityFleeingBlock;
import mod.badores.event.BOEventHandler;
import mod.badores.event.TickEvents;
import mod.badores.items.ItemBOIngot;
import mod.badores.network.PacketRandomTranslation;
import mod.badores.ore.*;
import mod.badores.oremanagement.BlockInfo;
import mod.badores.oremanagement.OreManager;
import mod.badores.worldgen.WorldGeneratorBadOres;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Logger;

/**
 * @author diesieben07
 */
@Mod(modid = BadOres.MOD_ID, name = "BadOres", version = BadOres.VERSION)
public class BadOres {

	public static final String MOD_ID = "badores";
	public static final String VERSION = "0.1";

	@SidedProxy(clientSide = "mod.badores.client.BOClientProxy", serverSide = "mod.badores.server.BOServerProxy")
	public static BOProxy proxy;

	public static Logger logger;

	public static boolean devEnv = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");

	public static boolean gameBreakingFeatures;

	public static CreativeTabs creativeTab = new CreativeTabs("badores") {
		@Override
		public ItemStack getIconItemStack() {
			BlockInfo blockInfo = oreManager.getBlockInfo(oreManager.getOreByName("polite"));
			return blockInfo.asStack();
		}

		@Override
		public Item getTabIconItem() {
			return ingot;
		}
	};

	public static OreManager oreManager;

	public static ItemBOIngot ingot;

	public static Item marmiteBread;

	public static SimpleNetworkWrapper network;
    public static BOEventHandler eventHandler;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();

		gameBreakingFeatures = config.get("balancing", "enableGameBreakingFeatures", true).getBoolean(true); // TODO implement

        eventHandler = new BOEventHandler();
        eventHandler.register();

		oreManager = new OreManager();

		oreManager.registerOre(new Polite());
		oreManager.registerOre(new Wannafite());
		oreManager.registerOre(new Breakium());
		oreManager.registerOre(new Crashium());
		oreManager.registerOre(new Stonium());
		oreManager.registerOre(new Crappium());
		oreManager.registerOre(new Enderite());
		oreManager.registerOre(new Website());
		oreManager.registerOre(new Lite());
		oreManager.registerOre(new Misleadium());
		oreManager.registerOre(new Ghostium());
		oreManager.registerOre(new Amadeum());
		oreManager.registerOre(new IncrediblyRarium());
		oreManager.registerOre(new Unobtainium());
		oreManager.registerOre(new Copper());
		oreManager.registerOre(new Uselessium());
		oreManager.registerOre(new Killium());
		oreManager.registerOre(new Movium());
		oreManager.registerOre(new Balancium());
		oreManager.registerOre(new Explodeitmite());
		oreManager.registerOre(new Marmite());
		oreManager.registerOre(new Shiftium());
		oreManager.registerOre(new Smite());
		oreManager.registerOre(new Wantarite());
		oreManager.registerOre(new Idlikeabite());
		oreManager.registerOre(new Meteorite());
		oreManager.registerOre(new Streetscum());
		oreManager.registerOre(new Fleesonsite());
		oreManager.registerOre(new Nopium());
        oreManager.registerOre(new Zombieunite());
        oreManager.registerOre(new Paintitwhite());
        oreManager.registerOre(new Iwontfite());
        oreManager.registerOre(new Tauntum());

		ingot = new ItemBOIngot();
		GameRegistry.registerItem(ingot, "ingot");

		oreManager.createGameElements();

		marmiteBread = new ItemFood(6, 0.8F, false).setUnlocalizedName("marmiteBread").setTextureName(MOD_ID + ":marmiteBread");
		GameRegistry.registerItem(marmiteBread, "marmiteBread", MOD_ID);

		EntityRegistry.registerModEntity(EntityFleeingBlock.class, "fleeingBlock", 0, this, 64, 3, true);

		proxy.preInit(event, config);
		config.save();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerWorldGenerator(new WorldGeneratorBadOres(oreManager), 100);

		network = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);
		network.registerMessage(PacketRandomTranslation.Handle.class, PacketRandomTranslation.class, 0, Side.CLIENT);

		FMLCommonHandler.instance().bus().register(TickEvents.INSTANCE);

		proxy.init(event);

		addCrafting();
	}

	private void addCrafting() {
		ItemStack marmiteIngot = new ItemStack(ingot);
		ItemBOIngot.setOre(marmiteIngot, oreManager.getOreByName("marmite"));
		GameRegistry.addShapelessRecipe(new ItemStack(marmiteBread), Items.bread, marmiteIngot);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

	public static String getTextureName(String name) {
		return MOD_ID + ":" + name;
	}
}
