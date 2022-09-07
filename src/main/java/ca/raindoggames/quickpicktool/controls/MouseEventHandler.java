package ca.raindoggames.quickpicktool.controls;

import static ca.raindoggames.quickpicktool.QuickPickToolMod.LOGGER;

import ca.raindoggames.quickpicktool.QuickPickToolMod;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent.ClickInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class MouseEventHandler {
	@SubscribeEvent
	public static void onEvent(ClickInputEvent event) {
		KeyMapping[] keyBindings = QuickPickToolMod.keyBindings;
		
		// break
		if (keyBindings[0].isDown()) {
			if (event.isPickBlock()) {
				KeyMapping keyMap = event.getKeyMapping();
				LOGGER.info(keyMap);
				event.setCanceled(true);
			}
		}
		
		// save
		if (keyBindings[1].isDown()) {
			if (event.isPickBlock()) {
				KeyMapping keyMap = event.getKeyMapping();
				LOGGER.info(keyMap);
				event.setCanceled(true);
			}
		}
	}
}
