package defiantpvp.searlie.customEnchantments.Consumables;

import defiantpvp.searlie.customEnchantments.enchantmentManager;
import defiantpvp.searlie.customEnchantments.enums.enchantmentApplyType;
import defiantpvp.searlie.customEnchantments.interfaces.enchantmentInterface;
import defiantpvp.searlie.utilities.loreManager;
import defiantpvp.searlie.utilities.numberManager;
import defiantpvp.searlie.utilities.soundEffects;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import java.util.Random;

public class applyEnchant {
    /*
        <- Has Meta ->
        <- Has Enchantment Already ->
        <- Has enough enchantment Slots ->


     */


    //applyManager(Main)
    public static void applyEnchantmentManager(InventoryClickEvent event) {
        //Event details
        event.setCancelled(true);

        //Variables
        ItemStack armorClicked = event.getCurrentItem();
        ItemStack enchantmentRune = event.getCursor();
        Player player = (Player) event.getWhoClicked();
        String rawEnchantmentName = numberManager.removeColorCodes(numberManager.removeRomanNumeral(enchantmentRune.getItemMeta().getDisplayName()));
        String loreLevel = "";
        boolean canApply = canApplyEnchantment(armorClicked, enchantmentRune);
        boolean successApply = succErr(event.getCursor());
        int hasEnchantment = -1;
        boolean enchantmentLevelHigher = false;


        if (!(canApply)){
            // Send Player Message
            ((Player) event.getWhoClicked()).getPlayer().sendMessage("Unable to apply this enchantment to this equipment piece");
        return;
        }


        if(armorClicked.hasItemMeta()){
            hasEnchantment = hasEnchantmentAlready(armorClicked, rawEnchantmentName);
        }

        if(hasEnchantment >=0){
            loreLevel = armorClicked.getItemMeta().getLore().get(hasEnchantment);
            enchantmentLevelHigher = hasEnchantmentHigher(loreLevel, enchantmentRune.getItemMeta().getDisplayName());
        }

        //Is Already same or higher level
        if (enchantmentLevelHigher) {
            //Message player advising that it already has lore
            event.getWhoClicked().sendMessage("Already has equivalent or higher level enchantment");
            soundEffects.playerSoundEffectPlay((Player) event.getWhoClicked(), Sound.NOTE_BASS);
            return;
        }

        //Item has maxed amount of enchantments
        if (loreManager.hasMaxedEnchantments(armorClicked) && !(hasEnchantment >= 0)){
            event.getWhoClicked().sendMessage("Armor already has the maximum amount of enchantments");
            soundEffects.playerSoundEffectPlay((Player) event.getWhoClicked(), Sound.NOTE_BASS);
            return;
        }

        //Success Error, Destroy
        if(!successApply){
            enchantApplyFailed(player);
            return;
        }

        //Doesnt have Enchantment Lore
        if (!(armorClicked.hasItemMeta())) {
            System.out.println("Armor has no item meta");
            applyEnchantment(armorClicked, enchantmentRune.getItemMeta().getDisplayName(), (Player) event.getWhoClicked());
            removeEnchantmentRune(((Player) event.getWhoClicked()).getPlayer());
            return;
        } else if (armorClicked.hasItemMeta() && hasEnchantment >=0) {
            System.out.println("Item has Item meta and enchantment already");
            //Remove old Lore
            loreManager.removeItemLore(armorClicked, hasEnchantment);
            //Add new Lore
            applyEnchantment(armorClicked, enchantmentRune.getItemMeta().getDisplayName(), (Player) event.getWhoClicked());
            //Remove Enchantment Rune
            removeEnchantmentRune(((Player) event.getWhoClicked()).getPlayer());
        } else {
            System.out.println(hasEnchantment);
            System.out.println("Has Item Meta but no enchantment");
            //Add new Lore
            applyEnchantment(armorClicked, enchantmentRune.getItemMeta().getDisplayName(), (Player) event.getWhoClicked());
            //Remove Enchantment Rune
            removeEnchantmentRune(((Player) event.getWhoClicked()).getPlayer());
        }
    }

    //Success, Error, Destroy
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

    //canApply
    private static boolean canApplyEnchantment(ItemStack armor, ItemStack enchantmentRune) {
        String enchantmentName = numberManager.removeColorCodes(numberManager.removeRomanNumeral(enchantmentRune.getItemMeta().getDisplayName()));
        for (enchantmentInterface enchantment : enchantmentManager.enchantments.values()) {
            //Confirms enchantment to name
            if (enchantment.getName().equalsIgnoreCase(enchantmentName)) {
                //Confirms type match
                for (enchantmentApplyType type : enchantmentApplyType.values()) {
                    if (type.appliesTo(armor.getType().name(), type) == enchantment.getApplyType()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Has Enchantment Lore
    private static int hasEnchantmentAlready(ItemStack armor, String enchantmentName) {
        if (armor == null || !armor.hasItemMeta() || armor.getItemMeta().getLore() == null) {
            return -1; // No lore to search through
        }

        // Loop through each line of lore to find the enchantment
        for (int i = 0; i < armor.getItemMeta().getLore().size(); i++) {
            String loreLine = armor.getItemMeta().getLore().get(i);
            if (loreLine.contains(enchantmentName)) {
                System.out.println(i); // Print the index where the enchantment is found
                return i; // Return the index of the enchantment line
            }
        }
        return -1;
    }

    //Determine if level is higher or lower
    private static boolean hasEnchantmentHigher(String loreLevel, String enchantmentRune) {
        return numberManager.extractAndConvert(loreLevel) >= numberManager.extractAndConvert(enchantmentRune);
    }

    //Apply enchantment to item
    private static void applyEnchantment(ItemStack armor, String enchantmentName, Player player){
        System.out.println("Applying enchantment!");
        loreManager.addItemLore(armor, enchantmentName);
        soundEffects.playerSoundEffectPlay(player, Sound.LEVEL_UP);
        removeEnchantmentRune(player.getPlayer());
    }

    //Remove Item
    private static void removeEnchantmentRune(Player player){
        if(player == null) {return;}
        if(player.getItemOnCursor().getAmount() > 1){
            player.getItemOnCursor().setAmount(player.getItemOnCursor().getAmount() - 1);
        }else{
            player.setItemOnCursor(null);
        }
    }

    //Determine Success Error Destroy
    private static void enchantApplyFailed(Player player){
        soundEffects.playerSoundEffectPlay(player, Sound.NOTE_SNARE_DRUM);
        player.sendMessage("Enchantment has failed!");
        removeEnchantmentRune((Player) player);
    }
}
