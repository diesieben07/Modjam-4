package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author diesieben07
 */
public abstract class AbstractOre implements BadOre {

    protected final Random rand = new Random();

    @Override
    public void onMined(EntityPlayer miner, World world, int x, int y, int z, Side side) { }

    @Override
    public String getIconName() {
        return BadOres.MOD_ID + ":badOre_" + getName();
    }
}
