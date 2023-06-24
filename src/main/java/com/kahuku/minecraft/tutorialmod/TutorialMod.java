package com.kahuku.minecraft.tutorialmod;

import com.kahuku.minecraft.tutorialmod.core.init.BlockInit;
import com.kahuku.minecraft.tutorialmod.core.init.EntityInit;
import com.kahuku.minecraft.tutorialmod.core.init.ItemInit;
import com.kahuku.minecraft.tutorialmod.core.init.SoundInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TutorialMod.MODID)
public class TutorialMod {
    public static final String MODID = "tutorialmod";

    public TutorialMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        SoundInit.SOUNDS.register(bus);
        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
        EntityInit.ENTITIES.register(bus);
    }

    public static final CreativeModeTab TUTORIAL_TAB = new CreativeModeTab(MODID) { //itemGroup.tutorialMod
        @Override
        public ItemStack makeIcon() {
            return ItemInit.EXAMPLE_ITEM.get().getDefaultInstance();
        }
    };
}
