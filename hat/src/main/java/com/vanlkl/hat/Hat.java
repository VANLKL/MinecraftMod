package com.vanlkl.hat;

import com.vanlkl.hat.command.HatCommand;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hat implements ModInitializer {
	public static final String MOD_ID = "hat";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("嗨嗨嗨!!!");
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> HatCommand.register(dispatcher));
	}
}