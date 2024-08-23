package defiantpvp.searlie.eventHandler;

import defiantpvp.searlie.customEnchantments.Consumables.applyEnchant;
import defiantpvp.searlie.customEnchantments.Consumables.applyEnchantment;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static defiantpvp.searlie.customEnchantments.Consumables.slotCrystals.checkSlotCrystal;

public class inventoryManager implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(!(event.getWhoClicked() instanceof Player) || event.getCursor() == null || event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR){return;}

        ItemStack cursorItem = event.getCursor(); // Item on the cursor
        ItemStack clickedItem = event.getCurrentItem(); // Item in the slot that was clicked

        //Rune apply Checks
        if(cursorItem.getType() == Material.EMERALD && cursorItem.hasItemMeta()){
            System.out.println("Is Emerald and Has Meta");
            applyEnchant.applyEnchantmentManager(event);
           //applyEnchantment.canApplyEnchantment(cursorItem, clickedItem, (Player) event.getWhoClicked(), event);
        }

        //Other Check if has Slot Crystal
        if(event.getCurrentItem() != null && isArmorPiece(event.getCurrentItem())){
            System.out.println("Clicked Armor piece");
            checkSlotCrystal(event.getCurrentItem());
        }

    }


    // Helper method to check if the item is an armor piece
    private boolean isArmorPiece(ItemStack item) {
        Material type = item.getType();
        switch (type) {
            case DIAMOND_HELMET:
            case DIAMOND_CHESTPLATE:
            case DIAMOND_LEGGINGS:
            case DIAMOND_BOOTS:
            case IRON_HELMET:
            case IRON_CHESTPLATE:
            case IRON_LEGGINGS:
            case IRON_BOOTS:
            case CHAINMAIL_HELMET:
            case CHAINMAIL_CHESTPLATE:
            case CHAINMAIL_LEGGINGS:
            case CHAINMAIL_BOOTS:
            case LEATHER_HELMET:
            case LEATHER_CHESTPLATE:
            case LEATHER_LEGGINGS:
            case LEATHER_BOOTS:
                return true;
            default:
                return false;
        }
    }
}
