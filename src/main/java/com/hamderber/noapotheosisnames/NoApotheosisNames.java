package com.hamderber.noapotheosisnames;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(NoApotheosisNames.MODID)
public class NoApotheosisNames
{
    public static final String MODID = "noapotheosisnames";
    public static final Logger LOGGER = LogUtils.getLogger();
    
    public NoApotheosisNames(IEventBus modEventBus, ModContainer modContainer)
    {
    	LOGGER.info(MODID + " loaded!");
    }
}
