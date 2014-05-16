package mod.badores.client.rendering;

import mod.badores.BadOres;
import mod.badores.oremanagement.BadOre;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Lukas Tenbrink on 16.05.2014.
 */
public class RenderFleeingBlock extends RendererLivingEntity {
    private ResourceLocation texture;

    public RenderFleeingBlock() {
        super(new ModelFleeingBlock(), 0.75f);

        texture = new ResourceLocation(BadOres.MOD_ID, "textures/entity/fleeingBlockFleesonsite.png");
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity var1) {
        return texture;
    }
}
