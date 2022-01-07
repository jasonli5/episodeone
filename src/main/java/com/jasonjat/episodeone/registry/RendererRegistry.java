package com.jasonjat.episodeone.registry;

import com.jasonjat.episodeone.entity.renderer.*;
import com.jasonjat.episodeone.util.ModUtil;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.util.Identifier;

public class RendererRegistry {

    public static void register() {
        EntityRendererRegistry.register(EntityRegistry.COPPER_GOLEM_ENTITY, CopperGolemEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.PURPLE_GOLEM_ENTITY, PurpleGolemEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.SPONGEBOB_BOSS_ENTITY, SpongebobBossRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.CIRCLE_BLAST_ENTITY, CircleBlastEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.PIZZA_BOX_ENTITY, PizzaBoxEntityRenderer::new);
    }
}