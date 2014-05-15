package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Random;

/**
 * @author diesieben07
 */
public class IncrediblyRarium extends AbstractOre {

    @Override
    public int getVeinSize(BiomeGenBase biomeGenBase, Random random) {
        return 1;
    }

    @Override
    public int getGeneratedVeinsInBiome(BiomeGenBase biomeGenBase, Random random) {
        return random.nextInt(1000 * 1000);
    }

    @Override
	public String getName() {
		return "incrediblyRarium";
	}
}
