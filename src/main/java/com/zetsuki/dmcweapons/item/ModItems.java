package com.zetsuki.dmcweapons.item;

import com.zetsuki.dmcweapons.DMC_Weapons;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
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
                            .sword(new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
                                    2031, 9.0F, 5.0F, 15,
                                    ItemTags.NETHERITE_TOOL_MATERIALS),3.0F, -2.4F)
                            .fireResistant()
                    )
            );

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
