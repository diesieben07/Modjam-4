package mod.badores.ore;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import scala.tools.nsc.doc.model.ModelFactory;

import java.util.List;

/**
 * @author diesieben07
 */
public class Ghostium extends AbstractOre {

	@Override
	public Entity createDropEntity(World world, double x, double y, double z, ItemStack stack) {
		EntityItem e = new EntityItem(world, x, y, z, stack);
		e.delayBeforeCanPickup = Integer.MAX_VALUE;
		System.out.println(e);
		return e;
	}

	@Override
	public String getName() {
		return "ghostium";
	}
}
