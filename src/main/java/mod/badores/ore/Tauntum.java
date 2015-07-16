package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.blocks.BlockTickProvider;
import mod.badores.util.JavaUtils;
import net.minecraft.entity.*;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @author diesieben07
 */
public class Tauntum extends AbstractOre {

    private static List<String> mobSounds;

    @Override
    protected int veinSize() {
        return 3;
    }

    public static List<String> getAllMobSounds(World world) {
        if (mobSounds == null) {
            mobSounds = new ArrayList<String>();
            HashMap map = EntityList.entityEggs;
            for (Object obj : map.keySet()) {
                Integer i = (Integer) obj;
                Entity entity = EntityList.createEntityByID(i, world);

                if (entity instanceof EntityLiving) {
                    EntityLiving entityLiving = (EntityLiving) entity;

                    String living = BadOresEntityAccessor.getLivingSound(entityLiving);

                    if (living != null)
                        mobSounds.add(living);
                }
            }

            mobSounds.add("creeper.primed");
        }

        return mobSounds;
    }

    @Override
    protected int veinsPerChunk(Random r, World w, int chunkX, int chunkZ) {
        return r.nextInt(100) == 0 ? 1 : 0;
    }

    @Override
    public boolean canTick(boolean isIngotBlock) {
        return true;
    }

    @Override
    public int initialTickRate(boolean isIngotBlock) {
        return rand.nextInt(400) + 40;
    }

    @Override
    public void tick(World world, int x, int y, int z, Random random, Side side, boolean isIngotBlock, BlockTickProvider tickProvider) {
        super.tick(world, x, y, z, random, side, isIngotBlock, tickProvider);

        if (side.isServer()) {
            playRandomSound(world, x, y, z, random);
        }
    }

    private void playRandomSound(World world, double x, double y, double z, Random random) {
        String sound = JavaUtils.selectRandom(random, getAllMobSounds(world));
        world.playSoundEffect(x, y, z, sound, 3.0F, 1.0f);
    }

    @Override
    public String getName() {
        return "tauntum";
    }
}
