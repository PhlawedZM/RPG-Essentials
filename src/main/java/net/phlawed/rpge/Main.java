package net.phlawed.rpge;


import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.phlawed.rpge.config.Config;
import net.phlawed.rpge.gui.SkillsGUI;
import net.phlawed.rpge.gui.SkillsScreen;
import net.phlawed.rpge.enchantments.Smelting;
import net.phlawed.rpge.init.*;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class Main implements ModInitializer {

	public static Enchantment SMELTING = new Smelting(Enchantment.Rarity.RARE, EnchantmentTarget.DIGGER, new EquipmentSlot[] {EquipmentSlot.MAINHAND});

	@Override
	public void onInitialize() {

		try {
			Config.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		ModItems.register();
		ModBlockEntity.register();
		ModBlocks.register();
		ModEntity.register();
		ModAttributes.register();
		ModData.register();

		Registry.register(Registries.ENCHANTMENT, new Identifier("rpge", "smelting"), SMELTING);

		Registry.register(Registries.ITEM, new Identifier("rpge","loot_barrel"), new BlockItem(ModBlocks.LOOT_BARREL, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier("rpge","common_chest"), new BlockItem(ModBlocks.COMMON_CHEST, new FabricItemSettings()));


		KeyBinding screen_bind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.rpge.screen", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, "key.category.rpge"));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (screen_bind.wasPressed()) {
				MinecraftClient.getInstance().setScreen(new SkillsScreen(new SkillsGUI()));
			}
		});

	}

}
