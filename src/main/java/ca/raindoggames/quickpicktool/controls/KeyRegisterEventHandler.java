package ca.raindoggames.quickpicktool.controls;

import static ca.raindoggames.quickpicktool.QuickPickToolMod.LOGGER;

import ca.raindoggames.quickpicktool.QuickPickToolMod;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KeyRegisterEventHandler {
	@SubscribeEvent
	public static void registerKeys(RegisterKeyMappingsEvent e) {
    	KeyMapping[] keyBindings = QuickPickToolMod.keyBindings;
    	LOGGER.info("Registering keys");
    	// Save keyBindings 
        keyBindings = new KeyMapping[2];
        keyBindings[0] = new KeyMapping("key.quickpicktool.break", 66, "category.quickpicktool.utils");
        keyBindings[1] = new KeyMapping("key.quickpicktool.save", 86, "category.quickpicktool.utils"); 
    	for (int i = 0; i < keyBindings.length; i++) {
    		e.register(keyBindings[i]);
    	}
  	}
}
