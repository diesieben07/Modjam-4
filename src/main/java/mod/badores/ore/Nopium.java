package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.oremanagement.ArmorInfo;
import mod.badores.oremanagement.ArmorType;
import mod.badores.oremanagement.OreForm;
import mod.badores.oremanagement.ToolInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Nopium extends AbstractOre {

    @Override
    public boolean hasTools() {
        return true;
    }

    @Override
    public boolean hasArmor() {
        return true;
    }

    @Override
    public boolean hasIngot() {
        return true;
    }

    @Override
    public ToolInfo getToolInfo() {
        return new ToolInfo(2, 800, 0.5F, 1.0F, 0);
    }

    @Override
    public ArmorInfo getArmorInfo() {
        return new ArmorInfo(15, new int[]{2, 6, 5, 2}, 0);
    }

    @Override
    public void onInventoryTick(OreForm form, ItemStack stack, int slot, EntityPlayer player, World world, Side side) {
        dropRandomly(stack, slot, player, side);
    }

    @Override
    public void onArmorTick(ArmorType type, EntityPlayer player, World world, Side side, int slot, ItemStack stack) {
        dropRandomly(stack, slot, player, side);
    }

    private void dropRandomly(ItemStack stack, int slot, EntityPlayer player, Side side) {
        if (side.isServer() && rand.nextInt(200) == 0) {
            player.func_146097_a(stack, false, true);
            player.inventory.setInventorySlotContents(slot, null);
        }
    }

    @Override
    public String getName() {
        return "nopium";
    }
}
