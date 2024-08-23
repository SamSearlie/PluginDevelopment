package defiantpvp.searlie.customEnchantments.Consumables;

import defiantpvp.searlie.customEnchantments.enchantmentManager;
import defiantpvp.searlie.customEnchantments.enums.enchantmentApplyType;
import defiantpvp.searlie.customEnchantments.interfaces.enchantmentInterface;
import defiantpvp.searlie.main;
import defiantpvp.searlie.utilities.loreManager;
import defiantpvp.searlie.utilities.numberManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class applyEnchantment implements Listener {

    private static boolean succErr (ItemStack enchantmentRune){
        Random success = new Random();
        int randomInt = 1 + success.nextInt(100 - 1 + 1);

        //Determine success rate
        for(String line : enchantmentRune.getItemMeta().getLore()){
            if(line.contains("Success")){
                int Number = Integer.parseInt(numberManager.extractNumber(line));
                return randomInt <= Number;
            }
        }
        return false;
    }

    private static void applyEnchantment(ItemStack clickedItem, enchantmentInterface enchants, String test){
        loreManager.addItemLore(clickedItem, "This is a test!");

        System.out.println(clickedItem + " " + enchants);
        // Your logic here
        ItemMeta meta = clickedItem.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore == null)
            lore = new ArrayList<>();
        lore.add("§r" + ChatColor.translateAlternateColorCodes('&', main.buildMessage(test)));
        meta.setLore(lore);
        clickedItem.setItemMeta(meta);
        // Optionally cancel the event if you don't want the item to be placed
        // event.setCancelled(true);
    }

    private static boolean hasEnchantment(ItemStack armor, String enchantmentName){
        if(!armor.hasItemMeta()){return  false;}
        ItemMeta itemMeta = armor.getItemMeta();
        String appliedEnchantmentName = numberManager.removeColorCodes(numberManager.removeRomanNumeral(enchantmentName));
        boolean found = false;
        List<String> lore =  armor.getItemMeta().getLore();


        for (int i = 0; i < lore.size(); i++) {
            String line = lore.get(i);
            String armorEnchantName = numberManager.removeColorCodes(numberManager.removeRomanNumeral(line));
            if (appliedEnchantmentName.equals(armorEnchantName)) {
                // Check if same or higher level
                int enchantOnArmorLevel = numberManager.extractAndConvert(line);
                int enchantOnRuneLevel = numberManager.extractAndConvert(enchantmentName);

                if (enchantOnArmorLevel >= enchantOnRuneLevel) {
                    return true;
                } else {
                    // Remove the line from the lore list
                    lore.remove(i);
                    found = true;
                    break; // Exit the loop once the line is found and removed
                }
            }
        }
        if (found) {
            itemMeta.setLore(lore);
            armor.setItemMeta(itemMeta);
        }

        return false;
    }

    public static void canApplyEnchantment(ItemStack enchantmentRune, ItemStack clickedItem, Player player, InventoryClickEvent e){

        String materialName = clickedItem.getType().name();
        String enchantmentDisplayName = enchantmentRune.getItemMeta().getDisplayName();
        String enchantmentName = numberManager.removeColorCodes(numberManager.removeRomanNumeral(enchantmentRune.getItemMeta().getDisplayName()));

        //Loop through enchantments
        for(enchantmentInterface enchantment : enchantmentManager.enchantments.values()) {
            //If Enchantment Name equals enchantment in cursor name
            if (enchantment.getName().equalsIgnoreCase(enchantmentName)) {
                //Loop to check if type values match
                for (enchantmentApplyType type : enchantmentApplyType.values()) {
                    //Confirms type matches
                    if (type.appliesTo(materialName, type) == enchantment.getApplyType()) {
                        //Checks to confirm if item has enchantment Already
                        if(hasEnchantment(clickedItem, enchantmentDisplayName)){
                            e.setCancelled(true);
                            e.getWhoClicked().sendMessage("Already has enchantment");
                            return;
                        }

                        //Success
                        boolean test = succErr(enchantmentRune);
                        //Confirms Success
                        if(test){
                            applyEnchantment(clickedItem, enchantment, enchantmentDisplayName);
                        }
                        if(player.getItemOnCursor().getAmount() == 1){
                            player.setItemOnCursor(null);
                            e.setCancelled(true);
                        }else{
                            player.getItemOnCursor().setAmount((player.getItemOnCursor().getAmount() -1));
                            e.setCancelled(true);
                            System.out.println();
                            return;
                        }
                    }
                    else {
                        System.out.println("Can't be applied to: " + type + " : " + enchantment.getName() + " : " + materialName);
                    }
                }
            }
        }
    }
}