package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import mod.badores.achievements.BOAchievementList;
import mod.badores.blocks.BlockBadOre;
import mod.badores.blocks.BlockTickProvider;
import mod.badores.config.BOConfig;
import mod.badores.items.BOOreProduct;
import mod.badores.items.ItemBOIngot;
import mod.badores.oremanagement.*;
import mod.badores.util.I18n;
import mod.badores.util.Sides;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;
import java.util.Random;

/**
 * @author diesieben07
 */
public abstract class AbstractOre implements BadOre {

    private static final String NBT_KEY = "badores.oreCollect";
    protected final Random rand = new Random();

    public static void triggerHarvestAchievement(EntityPlayer player, BadOre ore) {
        String toAdd = ore.getName();

        NBTTagCompound data = player.getEntityData();
        NBTTagCompound persisted;
        if (!data.hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
            data.setTag(EntityPlayer.PERSISTED_NBT_TAG, (persisted = new NBTTagCompound()));
        } else {
            persisted = data.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
        }

        NBTTagList list;
        if (!persisted.hasKey(NBT_KEY)) {
            persisted.setTag(NBT_KEY, (list = new NBTTagList()));
        } else {
            list = persisted.getTagList(NBT_KEY, Constants.NBT.TAG_STRING);
        }

        boolean add = true;
        for (int i = 0, len = list.tagCount(); i < len; ++i) {
            String oreName = list.getStringTagAt(i);
            if (oreName.equals(toAdd)) {
                add = false;
                break;
            }
        }
        if (add) {
            list.appendTag(new NBTTagString(toAdd));
        }

        if (list.tagCount() == BadOres.oreManager.getRequiredCount()) {
            player.triggerAchievement(BOAchievementList.allOres);
        }
    }

    public BlockInfo blockInfo() {
        return BadOres.oreManager.getBlockInfo(this);
    }

    public static void invokeOnMine(BadOre ore, ToolType type, EntityLivingBase living, Block block, World world, int x, int y, int z) {
        if (living instanceof EntityPlayer) {
            ore.onToolMine(type, ((EntityPlayer) living), world, x, y, z, Sides.logical(world));
        }
    }

    public static void invokeOnAttack(BadOre ore, ToolType type, EntityLivingBase living, EntityLivingBase target) {
        if (living instanceof EntityPlayer) {
            ore.onToolEntityAttack(type, ((EntityPlayer) living), target, living.worldObj, Sides.logical(living.worldObj));
        }
    }

    public static void invokeInventoryTick(BadOre ore, OreForm form, ItemStack stack, int slot, World world, Entity player) {
        if (player instanceof EntityPlayer) {
            ore.onInventoryTick(form, stack, slot, ((EntityPlayer) player), world, Sides.logical(world));
        }
    }

    @Override
    public boolean hasTools() {
        return false;
    }

    @Override
    public boolean hasArmor() {
        return false;
    }

    @Override
    public boolean hasIngot() {
        return false;
    }

    @Override
    public boolean hasIngotBlock() {
        return hasIngot();
    }

    @Override
    public boolean dropsIngotDirectly() {
        return false;
    }

    @Override
    public int ingotStackSize() {
        return 64;
    }

    @Override
    public void postProcessItem(BOOreProduct item, OreForm form) {
    }

    @Override
    public float getSmeltingXP() {
        return 0.5f;
    }

    @Override
    public ToolInfo getToolInfo() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArmorInfo getArmorInfo() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side, boolean isIngotBlock) {
    }

    @Override
    public void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side, boolean isIngotBlock) {
    }

    @Override
    public String getIconName() {
        return BadOres.getTextureName(getName() + ".ore");
    }

    @Override
    public String getIngotIconName() {
        return BadOres.MOD_ID + ":" + getName() + ".ingot";
    }

    @Override
    public String getIngotBlockIcon() {
        return BadOres.MOD_ID + ":" + getName() + ".ingotBlock";
    }

    @Override
    public String getDisplayName() {
        return I18n.translateBO(getName());
    }

    @Override
    public String getDisplayName(OreSubName name) {
        return name.subName(I18n.translateBO(getName()));
    }

    @Override
    public String getArmorIconName(ArmorType type) {
        int layer = type.getLayer();
        return BadOres.getTextureName("textures/armor/" + getName() + "." + "layer" + layer + ".png");
    }

    @Override
    public void addDrops(World world, int x, int y, int z, int meta, int fortune, List<ItemStack> drops, boolean isIngotBlock) {
        if (!isIngotBlock) {
            addOreDrops(world, x, y, z, meta, fortune, drops);
        } else {
            drops.add(BlockBadOre.createIngotBlock(this));
        }
    }

    protected void addOreDrops(World world, int x, int y, int z, int meta, int fortune, List<ItemStack> drops) {
        if (dropsIngotDirectly()) {
            drops.add(ItemBOIngot.createIngot(this));
        } else {
            drops.add(BadOres.oreManager.getBlockInfo(this).asStack());
        }
    }

    @Override
    public Entity createDropEntity(World world, double x, double y, double z, ItemStack stack, boolean isIngotBlock) {
        EntityItem entity = new EntityItem(world, x, y, z, stack);
        entity.delayBeforeCanPickup = 10;
        return entity;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (!BadOres.config.isOreGenerationDisabled(this)) {
            int numVeins = veinsPerChunk(random, world, chunkX, chunkZ);

            for (int i = 0; i < numVeins; i++) {
                int veinSize = veinSize();

                if (veinSize > 0) {
                    int min = genMin(random, world, chunkX, chunkZ);
                    int max = genMax(random, world, chunkX, chunkZ);
                    WorldGenerator worldGenMinable = createGenerator(random, world, chunkX, chunkZ);

                    int x = chunkX * 16 + random.nextInt(16);
                    int y = random.nextInt(max - min + 1) + min;
                    int z = chunkZ * 16 + random.nextInt(16);

                    worldGenMinable.generate(world, random, x, y, z);
                }
            }
        }
    }

    protected int veinsPerChunk(Random r, World w, int chunkX, int chunkZ) {
        return r.nextInt(4);
    }

    protected int dimension() {
        return 0;
    }

    protected int veinSize() {
        return 8;
    }

    protected int genMin(Random random, World world, int chunkX, int chunkZ) {
        return 0;
    }

    protected int genMax(Random random, World world, int chunkX, int chunkZ) {
        return 64;
    }

    protected Block replace() {
        switch (dimension()) {
            case 0:
                return Blocks.stone;
            case 1:
                return Blocks.end_stone;
            case -1:
                return Blocks.netherrack;
            default:
                throw new IllegalStateException("Unknown dimension " + dimension() + ", please override replace() instead!");
        }
    }

    private WorldGenMinable generator;

    protected WorldGenMinable createGenerator(Random r, World w, int chunkX, int chunkZ) {
        if (generator == null) {
            BlockInfo block = BadOres.oreManager.getBlockInfo(this);
            generator = new WorldGenMinable(block.block, block.metadata, veinSize(), replace());
        }
        return generator;
    }

    @Override
    public int lightLevel(boolean isIngotBlock) {
        return 0;
    }

    @Override
    public boolean canTick(boolean isIngotBlock) {
        return false;
    }

    @Override
    public int initialTickRate(boolean isIngotBlock) {
        return -1;
    }

    @Override
    public void tick(World world, int x, int y, int z, Random random, Side side, boolean isIngotBlock, BlockTickProvider tickProvider) {
        int tickRate = initialTickRate(isIngotBlock);
        if (tickRate >= 0)
            tickProvider.scheduleTick(tickRate);
    }

    @Override
    public float getHardness(World world, int x, int y, int z, boolean isIngotBlock) {
        return 3.0f;
    }

    @Override
    public float getExplosionResistance(World world, int x, int y, int z, boolean isIngotBlock) {
        return getHardness(world, x, y, z, isIngotBlock) / 5.0f;
    }

    @Override
    public boolean shouldSelectionRayTrace(boolean isIngotBlock) {
        return true;
    }

    @Override
    public void onToolMine(ToolType type, EntityPlayer player, World world, int x, int y, int z, Side side) {
    }

    @Override
    public void onToolEntityAttack(ToolType type, EntityPlayer player, EntityLivingBase target, World world, Side side) {
    }

    @Override
    public void onInventoryTick(OreForm form, ItemStack stack, int slot, EntityPlayer player, World world, Side side) {
    }

    @Override
    public void onArmorTick(ArmorType type, EntityPlayer player, World world, Side side, int slot, ItemStack stack) {
    }

    @Override
    public void onArmorAttacked(ArmorType type, EntityPlayer player, DamageSource damageSource, float amount, World world, Side side) {
    }

    @Override
    public void onContainerTick(OreForm form, Container container, Slot slot, ItemStack stack) {
    }

    @Override
    public boolean onRightClick(World world, int x, int y, int z, ForgeDirection clickSide, EntityPlayer player, boolean isIngotBlock, Side side) {
        return false;
    }

    @Override
    public void onLeftClick(World world, int x, int y, int z, EntityPlayer player, boolean isIngotBlock, Side logical) {
    }

    @Override
    public int harvestLevelRequired(boolean isIngotBlock) {
        return 2;
    }

    @Override
    public String toolRequired(boolean isIngotBlock) {
        return "pickaxe";
    }

    @Override
    public String getDescriptionText() {
        return I18n.translate("badores." + getName() + ".description");
    }

    @Override
    public boolean requiredForAll() {
        return true;
    }
}
