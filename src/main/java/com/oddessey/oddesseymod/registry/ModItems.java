package com.example.odysseymod.registry;

import com.example.odysseymod.OdysseyMod;
import com.example.odysseymod.item.SirensLyreItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModItems {

    // creates item registry
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, OdysseyMod.MODID);

    // register the Siren's Lyre
    public static final RegistryObject<Item> SIRENS_LYRE = ITEMS.register(
            "sirens_lyre",
            () -> new SirensLyreItem(new Item.Properties().stacksTo(1))
    );

    // register items with the mod event bus
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
