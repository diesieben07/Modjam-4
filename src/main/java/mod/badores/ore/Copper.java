package mod.badores.ore;

import mod.badores.oremanagement.ArmorInfo;
import mod.badores.oremanagement.ToolInfo;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author diesieben07
 */
public class Copper extends AbstractOre {

    @Override
    protected int genMin(Random random, World world, int chunkX, int chunkZ) {
        return 20;
    }

    @Override
    protected int genMax(Random random, World world, int chunkX, int chunkZ) {
        return 100;
    }

    @Override
    protected int veinSize() {
        return 7;
    }

    @Override
    public boolean hasIngot() {
        return true;
    }

    @Override
	public boolean hasTools() {
		return true;
	}

	@Override
	public ToolInfo getToolInfo() {
		return new ToolInfo(1, 200, 5.0F, 1.0F, 15);
	}

	@Override
	public boolean hasArmor() {
		return true;
	}

	@Override
	public ArmorInfo getArmorInfo() {
		return new ArmorInfo(8, new int[]{1, 4, 3, 1}, 15);
	}

	@Override
	public String getName() {
		return "copper";
	}
}
