package com.hamderber.noapotheosisnames.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.hamderber.noapotheosisnames.NoApotheosisNames;

import dev.shadowsoffire.apotheosis.util.NameHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;

@Mixin(value = NameHelper.class, remap = false)
public class NameHelperMixin {
	@Shadow
    @Mutable
    @Final
    public static String suffixFormat;

    @Shadow
    @Mutable
    @Final
    public static String ownershipFormat;

    @Shadow
    @Mutable
    @Final
    public static String chainFormat;

    static {
        suffixFormat = "%s%s";
        ownershipFormat = "%s";
        chainFormat = "%s%s";
    }
    
    @Overwrite
    public static String nameFromParts(RandomSource random) {
        return "";
    }
    
	@Overwrite
	public static String setEntityName(RandomSource rand, Mob entity) {
        return entity.getDisplayName().getString();
    }
	
	@Overwrite
    public static Component setItemName(RandomSource random, ItemStack stack) {
        return stack.getItem().getName(stack);
    }
}
