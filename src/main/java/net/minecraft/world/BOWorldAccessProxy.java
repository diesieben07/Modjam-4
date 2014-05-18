package net.minecraft.world;

/**
 * @author diesieben07
 */
public class BOWorldAccessProxy {

	public static boolean chunkExists(World world, int chunkX, int chunkZ) {
		return world.chunkExists(chunkX, chunkZ);
	}

}
