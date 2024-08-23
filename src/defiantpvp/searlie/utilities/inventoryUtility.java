package defiantpvp.searlie.utilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class inventoryUtility {

    public static boolean inventorySlotsAvailable(Player player, int slotsRequired){
        Inventory inventory = player.getInventory();
        ItemStack[] contents = inventory.getContents();
        int availableSlots = 0;
        // Iterate through the inventory slots
        for (ItemStack item : contents) {
            if (item == null || item.getType() == Material.AIR) {
                // Found an empty slot or a slot with space for more items
                availableSlots++;
                if (availableSlots >= 3) {
                    return true; // Found at least 3 available slots
                }
            }
        }
        // Not enough available slots found
        return false;
    }
}
