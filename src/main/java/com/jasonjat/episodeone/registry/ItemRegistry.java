package com.jasonjat.episodeone.registry;

import com.jasonjat.episodeone.item.armor.CopperArmorMaterial;
import com.jasonjat.episodeone.item.tool.CopperAxe;
import com.jasonjat.episodeone.item.tool.CopperPickaxe;
import com.jasonjat.episodeone.util.ModUtil;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {

    public static final ArmorMaterial COPPER_ARMOR_MATERIAL = new CopperArmorMaterial();
    public static final Item COPPER_HELMET = new ArmorItem(COPPER_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item COPPER_CHESTPLATE = new ArmorItem(COPPER_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item COPPER_LEGGINGS = new ArmorItem(COPPER_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item COPPER_BOOTS = new ArmorItem(COPPER_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));

    public static final Item COPPER_AXE = new CopperAxe();
    public static final Item COPPER_PICKAXE = new CopperPickaxe();

    public static void register() {
        Registry.register(Registry.ITEM, ModUtil.id("copper_helmet"), COPPER_HELMET);
        Registry.register(Registry.ITEM, ModUtil.id("copper_chestplate"), COPPER_CHESTPLATE);
        Registry.register(Registry.ITEM, ModUtil.id("copper_leggings"), COPPER_LEGGINGS);
        Registry.register(Registry.ITEM, ModUtil.id("copper_boots"), COPPER_BOOTS);
        Registry.register(Registry.ITEM, ModUtil.id("copper_axe"), COPPER_AXE);
        Registry.register(Registry.ITEM, ModUtil.id("copper_pickaxe"), COPPER_PICKAXE);

    }
}
