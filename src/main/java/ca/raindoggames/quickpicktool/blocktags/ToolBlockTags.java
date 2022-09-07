package ca.raindoggames.quickpicktool.blocktags;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ToolBlockTags {
	public static final TagKey<Block> SILK_TOUCH_MINEABLE = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("quickpicktool", "mineable/silk_touch"));
	public static final TagKey<Block> SHEAR_MINEABLE = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("quickpicktool", "mineable/shear"));
}
