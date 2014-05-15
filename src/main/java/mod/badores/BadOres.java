package mod.badores;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mod.badores.ore.OreManager;
import mod.badores.ore.Polite;

/**
 * @author diesieben07
 */
@Mod(modid = BadOres.MOD_ID, name = "BadOres", version = BadOres.VERSION)
public class BadOres {

	static final String MOD_ID = "badores";
	static final String VERSION = "0.1";

	@SidedProxy(clientSide = "mod.badores.client.ClientProxy", serverSide = "mod.badores.server.ServerProxy")
	public static BOProxy proxy;

	public static OreManager oreManager;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		oreManager = new OreManager();
		oreManager.registerOre(new Polite());

		oreManager.createBlocks();

		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

}
