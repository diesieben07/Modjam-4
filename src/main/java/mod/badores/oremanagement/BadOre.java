package mod.badores.oremanagement;

import cpw.mods.fml.relauncher.Side;
import mod.badores.items.BOOreProduct;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.List;
import java.util.Random;

/**
 * @author diesieben07
 */
public interface BadOre {

	boolean hasTools();

	boolean hasArmor();

	boolean hasIngot();

	boolean dropsIngotDirectly();

    int ingotStackSize();

	ToolInfo getToolInfo();

	ArmorInfo getArmorInfo();

	void postProcessItem(BOOreProduct item, OreForm form);

	void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side);

	void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side);

	void onToolMine(ToolType type, EntityPlayer player, World world, int x, int y, int z, Side side);

	void onToolEntityAttack(ToolType type, EntityPlayer player, EntityLivingBase target, World world, Side side);

	void onArmorTick(ArmorType type, EntityPlayer player, World world, Side side, int slot, ItemStack stack);

	void onInventoryTick(OreForm form, ItemStack stack, int slot, EntityPlayer player, World world, Side side);

    void onArmorAttacked(ArmorType type, EntityPlayer player, DamageSource damageSource, float amount, World world, Side side);

	void onContainerTick(OreForm form, Container container, Slot slot, ItemStack stack);

    String getName();

	String getIconName();

	String getIngotIconName();

	String getArmorIconName(ArmorType type);

	void addDroppedItemsToList(World world, int x, int y, int z, int meta, int fortune, List<ItemStack> drops);

	Entity createDropEntity(World world, double x, double y, double z, ItemStack stack);

	void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider);

	int lightLevel();

	int initialTickRate();

	void tick(World world, int x, int y, int z, Random random, Side side);

	float getHardness(World world, int x, int y, int z);

	String getDisplayName();

	String getDisplayName(OreSubName name);

	float getExplosionResistance(World world, int x, int y, int z);

	boolean shouldSelectionRayTrace();

	float getSmeltingXP();

    int harvestLevelRequired();

    String toolRequired();

	String getDescriptionText();
}
