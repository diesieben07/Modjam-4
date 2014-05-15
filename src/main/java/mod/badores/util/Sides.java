package mod.badores.util;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public final class Sides {

	public static Side logical(World world) {
		return world.isRemote ? Side.CLIENT : Side.SERVER;
	}


	private Sides() { }

}
