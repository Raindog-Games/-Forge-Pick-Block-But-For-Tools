package ca.raindoggames.quickpicktool;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("quickpicktool")
public class QuickPickToolMod
{
    // Directly reference a slf4j logger
	public static final String MODID = "quickpicktool";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static KeyMapping[] keyBindings = {};

    public QuickPickToolMod()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
    	// Save keyBindings 
        keyBindings = new KeyMapping[2];
        keyBindings[0] = new KeyMapping("key.quickpicktool.break", 66, "category.quickpicktool.utils");
        keyBindings[1] = new KeyMapping("key.quickpicktool.save", 86, "category.quickpicktool.utils"); 
        for (int i = 0; i < keyBindings.length; i++) {
        	ClientRegistry.registerKeyBinding(keyBindings[i]);
        }
    }
}
