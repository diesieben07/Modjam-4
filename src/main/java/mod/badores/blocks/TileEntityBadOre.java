package mod.badores.blocks;

import gnu.trove.iterator.TIntIterator;
import gnu.trove.list.array.TIntArrayList;
import mod.badores.util.Sides;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Lukas Tenbrink on 18.05.2014.
 */
public class TileEntityBadOre extends TileEntity implements BlockTickProvider{
    private int ticksUntilEventFire = -1;

    @Override
    public void updateEntity() {
        if (ticksUntilEventFire >= 0) {
            ticksUntilEventFire--;

            if (ticksUntilEventFire < 0)
                fireTick();
        }
    }

    @Override
    public void scheduleTick(int delay) {
        if (!worldObj.isRemote)
            ticksUntilEventFire = delay;
    }

    private void fireTick() {
        BlockBadOre block = (BlockBadOre)getBlockType();
        int meta = getBlockMetadata();

        block.getOre(meta).tick(getWorldObj(), xCoord, yCoord, zCoord, worldObj.rand, Sides.logical(getWorldObj()), BlockBadOre.isIngotBlock(meta), this);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("ticksUntilEventFire", ticksUntilEventFire);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        ticksUntilEventFire = nbtTagCompound.getInteger("ticksUntilEventFire");
    }
}
