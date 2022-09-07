package ca.raindoggames.quickpicktool.inventory;

import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class PlayerInventoryHelper {
	
	public PlayerInventoryHelper() {}
	
	private static int WOOD = 0;
	private static int STONE = 1;
	private static int GOLD = 2;
	private static int IRON = 3;
	private static int DIAMOND = 4;
	private static int NETHERITE = 5;
	
	// Weight which tool should be returned
	// requires silk_touch if save block
	// prioritizes fortune on break block
	// 1. highest level [nether, diamond, iron, gold, stone, wood]
	// 2. named
	// 3. most enchantments
	// tie first best found
	public void selectTool(Inventory inventory, String tool, boolean silkTouch) {
		int material = 0;
		boolean named = false;
		boolean fortune = false;
		int numEnchants = 0;
		int bestIndex = -1;
		for (int i = 0; i < inventory.items.size(); ++i) {
			ItemStack curStack = inventory.items.get(i);
			String stackString = curStack.toString();
			boolean replace = false;
			// Find the best item
			if (stackString.contains(tool)) {
				// skip tool if not silk_touch
				ListTag enchantments = curStack.getEnchantmentTags();
				if (silkTouch && enchantments.toString().indexOf("silk_touch") == -1) {
					continue;
				} else if (!silkTouch && enchantments.toString().indexOf("silk_touch") > -1) {
					continue;
				}
				
				// decide best tool
				if (bestIndex == -1) {
					replace = true;
				} else {
					int curMaterial = this.matchMaterial(stackString);
					if (curMaterial > material) {
						replace = true;
					} else if (curMaterial == material && curStack.getHoverName().getString() != "" && !named) {
						replace = true;
					} else if (curMaterial == material && curStack.getHoverName().getString() != "" && (!silkTouch && enchantments.toString().indexOf("fortune") > -1 && !fortune)) {
						replace = true;
					} else if (curMaterial == material && curStack.getHoverName().getString() != "" && (!silkTouch && enchantments.toString().indexOf("fortune") > -1) && enchantments.size() > numEnchants) {
						replace = true;
					} else if (curMaterial == material && curStack.getHoverName().getString() != "" && !fortune && enchantments.size() > numEnchants) {
						replace = true;
					// Set of cases where tools are not named just enchanted
					} else if (curMaterial == material && !named && (!silkTouch && enchantments.toString().indexOf("fortune") > -1 && !fortune)) {
						replace = true;
					} else if (curMaterial == material && !named && (!silkTouch && enchantments.toString().indexOf("fortune") > -1) && enchantments.size() > numEnchants) {
						replace = true;
					} else if (curMaterial == material && !named && !fortune && enchantments.size() > numEnchants) {
						replace = true;
					}
				}
				
				if (replace) {
					material = this.matchMaterial(stackString);
					named = curStack.getHoverName().getString() != "";
					fortune = enchantments.toString().indexOf("fortune") > -1;
					numEnchants = enchantments.size();
					bestIndex = i;
				}
			}
		}
			
		// Place the best item in your toolbar or select it if there
		if (bestIndex > -1) {
			if (Inventory.isHotbarSlot(bestIndex)) {
				inventory.selected = bestIndex;
			} else {
				// Make this a Mixin method
				//interactionManager.pickFromInventory(bestIndex);
			}
		}
	}
	
	private int matchMaterial(String stackString) {
		if (stackString.contains("wood")) {
			return WOOD;
		} else if (stackString.contains("stone")) {
			return STONE;
		} else if (stackString.contains("gold")) {
			return GOLD;
		} else if (stackString.contains("iron")) {
			return IRON;
		} else if (stackString.contains("diamond")) {
			return DIAMOND;
		} else if (stackString.contains("netherite")) {
			return NETHERITE;
		} else {
			return -1;
		}
	}
}
