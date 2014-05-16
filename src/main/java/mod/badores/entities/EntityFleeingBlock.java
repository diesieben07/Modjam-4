package mod.badores.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Created by Lukas Tenbrink on 15.05.2014.
 */
public class EntityFleeingBlock extends EntityCreature {
	public Block block;
	public int blockMeta;

	public EntityFleeingBlock(World par1World) {
		super(par1World);

		setSize(1.0f, 1.0f);
        this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 1.33D));
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0F, 0.8D, 1.33D));
		this.tasks.addTask(10, new EntityAIWander(this, 0.8D));
	}

	public EntityFleeingBlock(World par1World, Block block, int blockMeta) {
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (worldObj.getClosestPlayer(posX, posY, posZ, 10.0) == null) {
			if (!worldObj.isRemote) {
				worldObj.setBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), block, blockMeta, 3);
				setDead();
			}
		}
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
	public void setCurrentItemOrArmor(int var1, ItemStack var2) {

	}

    @Override
	public ItemStack[] getLastActiveItems() {
		return new ItemStack[0];
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
