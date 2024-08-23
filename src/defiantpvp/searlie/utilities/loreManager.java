package defiantpvp.searlie.utilities;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class loreManager {
    //Add Lore
    public static void addItemLore(ItemStack item, String updateString){
        if(item == null || updateString.isEmpty()){return;}
        // Get the ItemMeta from the ItemStack
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) {
            return;
        }

        // Initialize or get existing lore
        List<String> lore = itemMeta.hasLore() ? itemMeta.getLore() : new ArrayList<>();

        // Add the new lore at the top
        lore.add(0, updateString); // Add to the start of the list

        // Set the updated lore to the ItemMeta and apply it to the ItemStack
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
    }

    //Remove Lore
    public static void removeItemLore(ItemStack item, int loreToRemove){
        System.out.println("Removing Line");
        if(item == null){return;}
        System.out.println("Item is not null");
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) {

            return;
        }
        System.out.println("Has Item Meta");
        // Initialize or get existing lore
        List<String> lore = itemMeta.hasLore() ? itemMeta.getLore() : new ArrayList<>();

        // Add the new lore at the top
        lore.remove(loreToRemove); // Add to the start of the list

        // Set the updated lore to the ItemMeta and apply it to the ItemStack
        System.out.println(lore);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
    }

    //Update Lore
    public static void updateItemLore(ItemStack item, int loreToUpdate, String updateString){}

    //Has Maxed Enchantments
    public static boolean hasMaxedEnchantments(ItemStack item){
        int maxEnchants = 10;
        int i = 1;
        if(item == null){return false;}
        // Get the ItemMeta from the ItemStack
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) {
            return false;
        }

        // Initialize or get existing lore
        List<String> lore = itemMeta.hasLore() ? itemMeta.getLore() : new ArrayList<>();
        System.out.println(lore.size());
        for(i = 1; i < lore.size(); i++ ){
            System.out.println(lore.get(i));
            if(i == maxEnchants){
                System.out.println("Returning TRUE!");
                return true;}
        }
        return false;
    }



    //Update Slot Crystal's
    public static void updateSlotCrystal(ItemStack item, int amountChanged){

        Integer slotAmount = null;
        int loreLineChecked = 0;

        for(String loreLine : item.getItemMeta().getLore()){
            if(loreLine.contains("Slot Crystal")){
                slotAmount = Integer.parseInt(numberManager.extractNumber(loreLine));
                break;
            }
            loreLineChecked++;
        }

        if(slotAmount != null){
            removeItemLore(item, loreLineChecked);
        }
        List<String> lore = item.getItemMeta().hasLore() ? item.getItemMeta().getLore() :  new ArrayList<>();
        ItemMeta meta = item.getItemMeta();
        lore.add("Slot Crystal " + slotAmount); // Add to the start of the list
        meta.setLore(lore);
        item.setItemMeta(meta);
        System.out.println("Updated Slot Crystal for this item!");
    }

    //add Slot Crystal's
    public static void addSlotCrystal(ItemStack item){
        System.out.println("Add Slot Crytal to this item!");
    }

}
