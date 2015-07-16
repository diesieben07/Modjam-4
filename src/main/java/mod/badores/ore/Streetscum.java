package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.util.FakePlayerDetection;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diesieben07
 */
public class Streetscum extends AbstractOre {

    @Override
    public void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side, boolean isIngotBlock) {
        if (FakePlayerDetection.isFakePlayer(miner)) return;
        if (side.isServer()) {
            List<Integer> availableItems = new ArrayList<Integer>();
            for (int i = 0; i < miner.inventory.getSizeInventory(); i++) {
                if (miner.inventory.getStackInSlot(i) != null)
                    availableItems.add(i);
            }

            int remove = rand.nextInt(availableItems.size() / 3 + 1);
            for (int i = 0; i < remove; i++) {
                int removeSlot = availableItems.remove(rand.nextInt(availableItems.size()));
                miner.inventory.setInventorySlotContents(removeSlot, null);
            }
        }
    }

    @Override
    public String getName() {
        return "streetscum";
    }
}
