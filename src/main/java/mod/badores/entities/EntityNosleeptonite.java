package mod.badores.entities;

import mod.badores.BadOres;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Created by Lukas Tenbrink on 15.05.2014.
 */
public class EntityNosleeptonite extends EntityMob {
	public Block block;
	public int blockMeta;

	public EntityNosleeptonite(World par1World) {
		super(par1World);

		setSize(1.0f, 1.0f);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(2, new EntityAIWander(this, 0.8D));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	public EntityNosleeptonite(World par1World, Block block, int blockMeta) {
		this(par1World);

		this.block = block;
		this.blockMeta = blockMeta;
	}

    @Override
    protected boolean isAIEnabled() {
        return true;
    }

    @Override
    protected boolean isMovementCeased()
    {
        return false;
    }

    @Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4);
	}

    @Override
    protected void dropFewItems(boolean par1, int par2) {
        entityDropItem(new ItemStack(block, 1, blockMeta), 0.5f);
    }

    @Override
	public ItemStack getHeldItem() {
		return null;
	}

	@Override
	public ItemStack getEquipmentInSlot(int var1) {
		return null;
	}

	@Override
	public void setCurrentItemOrArmor(int var1, ItemStack var2) { }

    @Override
	public ItemStack[] getLastActiveItems() {
		return new ItemStack[0];
	}

    @Override
    protected String getLivingSound() {
        return BadOres.MOD_ID + ":mob.nosleeptonite.say";
    }

    @Override
    protected String getHurtSound() {
        return BadOres.MOD_ID + ":mob.nosleeptonite.hit";
    }

    @Override
    protected String getDeathSound() {
        return BadOres.MOD_ID + ":mob.nosleeptonite.death";
    }

    @Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);

		block = (Block) Block.blockRegistry.getObject(par1NBTTagCompound.getString("block"));
		blockMeta = par1NBTTagCompound.getInteger("blockMeta");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setString("block", Block.blockRegistry.getNameForObject(block));
		par1NBTTagCompound.setInteger("blockMeta", blockMeta);
	}
}
