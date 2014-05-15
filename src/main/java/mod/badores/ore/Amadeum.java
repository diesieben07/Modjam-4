package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.oremanagement.BlockInfo;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author diesieben07
 */
public class Amadeum extends AbstractOre {

    @Override
    public int initialTickRate() {
        return 20;
    }

    @Override
    public void tick(World world, int x, int y, int z, BlockInfo blockInfo, Random random, Side side) {
        super.tick(world, x, y, z, blockInfo, random, side);

        if (side.isServer())
        {
            byte noteType = (byte)random.nextInt(5);
            int note = random.nextInt(25);

            float f = (float)Math.pow(2.0D, (double)(note - 12) / 12.0D);
            String s = "harp";

            if (noteType == 1)
                s = "bd";
            else if (noteType == 2)
                s = "snare";
            else if (noteType == 3)
                s = "hat";
            else if (noteType == 4)
                s = "bassattack";

            world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "note." + s, 3.0F, f);
//            world.spawnParticle("note", (double)x + 0.5D, (double)y + 1.2D, (double)z + 0.5D, (double)note / 24.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public String getName() {
        return "amadeum";
    }
}
