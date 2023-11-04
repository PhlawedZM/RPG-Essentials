package net.phlawed.rpge.init;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.render.entity.CreeperEntityRenderer;
import net.minecraft.client.render.entity.HuskEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.phlawed.rpge.mobs.ChampCreeper;
import net.phlawed.rpge.mobs.NPCEntity;

public class ModEntity {

    public static EntityType<ChampCreeper> CHAMPION_CREEPER;
    public static EntityType<NPCEntity> NPC_ENTITY;

    public static void register() {

        CHAMPION_CREEPER = Registry.register(Registries.ENTITY_TYPE, new Identifier("rpge", "champ_creeper"), EntityType.Builder.create(ChampCreeper::new,SpawnGroup.MONSTER).setDimensions(0.6F, 1.7F).maxTrackingRange(8).build("rpge:champ_creeper"));
        FabricDefaultAttributeRegistry.register(CHAMPION_CREEPER, ChampCreeper.createCreeperAttributes());
        EntityRendererRegistry.register(CHAMPION_CREEPER, CreeperEntityRenderer::new);

        NPC_ENTITY = Registry.register(Registries.ENTITY_TYPE, new Identifier("rpge", "npc_entity"), EntityType.Builder.create(NPCEntity::new,SpawnGroup.MONSTER).setDimensions(0.6F, 1.95F).maxTrackingRange(8).build("rpge:npc_entity"));
        FabricDefaultAttributeRegistry.register(NPC_ENTITY, NPCEntity.createAttributes());
        EntityRendererRegistry.register(NPC_ENTITY, ZombieEntityRenderer::new);

    }
}
