package mod.badores.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mod.badores.BlockTicker;
import mod.badores.items.ItemBOArmor;
import mod.badores.util.Sides;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;

/**
 * Created by Lukas Tenbrink on 16.05.2014.
 */
public enum ForgeEventHandler {

	INSTANCE;

	@SubscribeEvent
    public void onEntityAttacked(LivingAttackEvent event)
    {
        if (event.entityLiving instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            for (int i = 0; i < 4; i++) {
                ItemStack armor = player.getCurrentArmor(i);
                if (armor != null && armor.getItem() instanceof ItemBOArmor)
                {
                    World world = player.getEntityWorld();
                    ((ItemBOArmor) armor.getItem()).ore.onArmorAttacked(((ItemBOArmor) armor.getItem()).omArmorType, player, event.source, event.ammount, world, Sides.logical(world));
                }
            }
        }
    }

	@SubscribeEvent
	public void onChunkLoad(ChunkEvent.Load event) {
		if (!event.world.isRemote) {
			BlockTicker.loadChunk(event.getChunk());
		}
	}

	@SubscribeEvent
	public void onWorldUnload(WorldEvent.Unload event) {
		BlockTicker.unload(event.world);
	}

}
