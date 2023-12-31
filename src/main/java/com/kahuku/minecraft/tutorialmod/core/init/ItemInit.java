package com.kahuku.minecraft.tutorialmod.core.init;

import com.kahuku.minecraft.tutorialmod.common.item.PasterItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import com.kahuku.minecraft.tutorialmod.TutorialMod;

public final class ItemInit {
    private ItemInit() {}

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MODID);

    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item",
            () -> new Item(new Item.Properties().tab(TutorialMod.TUTORIAL_TAB)));

    public static final RegistryObject<ForgeSpawnEggItem> EXAMPLE_ENTITY_SPAWN_EGG = ITEMS.register("example_entity_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.EXAMPLE_ENTITY, 0x6d6e6e, 0x03fcfc, new Item.Properties().tab(TutorialMod.TUTORIAL_TAB).stacksTo(16)));

    public static final RegistryObject<PasterItem> PASTER = ITEMS.register("paster",
            () -> new PasterItem(new Item.Properties().tab(TutorialMod.TUTORIAL_TAB)));

    // Block items
    public static final RegistryObject<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block",
            () -> new BlockItem(BlockInit.EXAMPLE_BLOCK.get(),
                    new Item.Properties().tab(TutorialMod.TUTORIAL_TAB)));
}
