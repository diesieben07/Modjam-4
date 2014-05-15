package mod.badores;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

/**
 * @author diesieben07
 */
public interface BOProxy {

	void preInit(FMLPreInitializationEvent event, Configuration config);

	void init(FMLInitializationEvent event);

	void postInit(FMLPostInitializationEvent event);

}
