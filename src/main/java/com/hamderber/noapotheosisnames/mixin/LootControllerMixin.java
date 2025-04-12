package com.hamderber.noapotheosisnames.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
//import org.spongepowered.asm.mixin.injection.Inject;

import dev.shadowsoffire.apotheosis.Apoth.Components;
import dev.shadowsoffire.apotheosis.affix.AffixHelper;
import dev.shadowsoffire.apotheosis.affix.ItemAffixes;
import dev.shadowsoffire.apotheosis.loot.LootCategory;
import dev.shadowsoffire.apotheosis.loot.LootController;
import dev.shadowsoffire.apotheosis.loot.LootRarity;
import dev.shadowsoffire.apotheosis.loot.LootRule;
import dev.shadowsoffire.apotheosis.tiers.GenContext;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;

@Mixin(value = LootController.class, remap = false)
public class LootControllerMixin {
	@Overwrite
	public static ItemStack createLootItem(ItemStack stack, LootCategory cat, LootRarity rarity, GenContext ctx) {
        stack.set(Components.AFFIXES, ItemAffixes.EMPTY);
        AffixHelper.setRarity(stack, rarity);

        for (LootRule rule : rarity.getRules(cat)) {
            rule.execute(stack, rarity, ctx);
        }
        
        MutableComponent name = stack.getItem().getName(stack).copy().withStyle(Style.EMPTY.withColor(rarity.color()));
        AffixHelper.setName(stack, name);
        
        // Setting the Affix name and then clearning it visually is critical for Apotheosis to spawn bosses/invaders
        
        Component defaultName = stack.getItem().getName(stack);
        Component coloredName = defaultName.copy().withStyle(Style.EMPTY.withColor(rarity.color()));
        stack.set(DataComponents.ITEM_NAME, coloredName);
        
        return stack;
    }
}
