package com.zetsuki.dmcweapons.item;

import com.zetsuki.dmcweapons.DMC_Weapons;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    private static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DMC_Weapons.MODID);

    public static final RegistryObject<Item> REBELLION =
            ITEMS.register("rebellion",
                    () -> new Item(new Item.Properties()
                            .setId(ITEMS.key("rebellion"))
                            .stacksTo(1)
                    )
            );

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
