package mod.badores.worldgen;

import cpw.mods.fml.common.IWorldGenerator;
import mod.badores.oremanagement.BadOre;
import mod.badores.oremanagement.OreManager;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

/**
 * Created by Lukas Tenbrink on 15.05.2014.
 */
public class WorldGeneratorBadOres implements IWorldGenerator {
    private OreManager oreManager;

    public WorldGeneratorBadOres(OreManager oreManager) {
        this.oreManager = oreManager;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        for (BadOre ore : oreManager.getAllOres())
        {
            ore.generate(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        }
    }
}
