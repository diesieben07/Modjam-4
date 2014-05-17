package mod.badores.client.rendering;

import mod.badores.BadOres;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Lukas Tenbrink on 16.05.2014.
 */
public class RenderNosleeptonite extends RendererLivingEntity {
    private ResourceLocation texture;

    public RenderNosleeptonite() {
        super(new ModelNoSleepTonite(), 0.75f);

        texture = new ResourceLocation(BadOres.MOD_ID, "textures/entity/nosleeptonite.png");
    }

	@Override
	protected boolean func_110813_b(EntityLivingBase par1EntityLivingBase) {
		return false;
	}

	@Override
    protected ResourceLocation getEntityTexture(Entity var1) {
        return texture;
    }
}
