package defiantpvp.searlie.customEnchantments.Consumables;

import defiantpvp.searlie.customEnchantments.enchantmentManager;
import defiantpvp.searlie.customEnchantments.enums.enchantmentRarity;
import defiantpvp.searlie.customEnchantments.interfaces.enchantmentInterface;
import defiantpvp.searlie.main;
import defiantpvp.searlie.utilities.inventoryUtility;
import defiantpvp.searlie.utilities.particleManager;
import defiantpvp.searlie.utilities.numberManager;
import defiantpvp.searlie.utilities.soundEffects;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class runePouch implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){

        Player player = e.getPlayer();
        if(player == null){
        return;
        }
        //Complete checks

        if(e.getItem() != null && e.getItem().getType() == Material.GLASS_BOTTLE){

            if(!(inventoryUtility.inventorySlotsAvailable(player, 3))){
                System.out.println("Player doesn't have inventory space");
                player.sendMessage("Inventory full clear 3 item slots!");
                return;
            }

            //Checking if Glass bottle
            ItemMeta meta = e.getItem().getItemMeta();
            if (meta != null && meta.hasDisplayName() && meta.hasLore()) {
                String displayName = e.getItem().getItemMeta().getDisplayName();
                List<String> lore = meta.getLore();
                for(enchantmentRarity rarity : enchantmentRarity.values()){
                    System.out.println(displayName.toLowerCase() + " " + lore);
                    if (displayName.toLowerCase().contains(rarity.name().toLowerCase()) && displayName.toLowerCase().contains("pouch")) {
                        for(String loreLine : lore){
                            if(loreLine.contains(rarity.getDescription())){
                                if(player.getItemInHand().getAmount() - 1 <= 0){
                                    player.setItemInHand(null);
                                }else{
                                    player.getItemInHand().setAmount(player.getItemInHand().getAmount() -1);
                                }
                                soundEffects.playerSoundEffectPlay(player, Sound.LEVEL_UP);
                                Location loc = player.getLocation();
                                particleManager.spawnParticle(loc, "FIREWORKS_SPARK", .75f, 0.75f, 0.75f, 0.2f, 40);
                                rollEnchantments(player, rarity);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }


    private void giveEnchantment(Player player, enchantmentInterface enchantment, int level, int succ, int fail){

        String displayName = enchantment.getRarity().getColorCode() + enchantment.getName() + " " + numberManager.intToRoman(level);
        ItemStack item = new ItemStack(Material.EMERALD); // You can change Material to any item type

        // Step 2: Get the ItemMeta from the ItemStack
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return; // Safeguard if meta is null
        }
        System.out.println(displayName);
        // Step 3: Set the name of the item
        meta.setDisplayName("§r" + ChatColor.translateAlternateColorCodes('&', main.buildMessage(displayName))); // Use color codes as needed (e.g., §6 for gold)

        // Step 4: Set the lore of the item
        List<String> lore = new ArrayList<>();
        lore.add("§7" + enchantment.getDescription()); // Add lore lines
        lore.add("§aSuccess: " + succ + "%"); // Add lore lines
        lore.add("§cFailure: " + fail + "%");
        meta.setLore(lore);
        item.setItemMeta(meta);

        player.getInventory().addItem(item);
    }

    private void rollEnchantments(Player player, enchantmentRarity rarity){

        Map<enchantmentInterface, Integer> enchantments = new HashMap<>();

        for(enchantmentInterface enchantment : enchantmentManager.enchantments.values()){
            if (enchantment.getRarity().equals(rarity)){
                enchantments.put(enchantment, enchantment.maxLevel());
            }
        }

        List<enchantmentInterface> keys = new ArrayList<>(enchantments.keySet());

        Random enchantmentSelected = new Random();
        Random enchantmentLevelRandom = new Random();
        Random enchantmentAmount = new Random();
        int randomInt = 1 + enchantmentAmount.nextInt(3 - 1 + 1);
        for(int i = 0; i < randomInt; i++){
           if (!keys.isEmpty()) {
               // Generate a random index
               int randomIndex = enchantmentSelected.nextInt(keys.size());

               // Get the key at the random index
               enchantmentInterface selectedEnchantment = keys.get(randomIndex);

               // Retrieve the value associated with the random key
               Integer maxLevel = enchantments.get(selectedEnchantment);
               int enchantmentLevelSelected = enchantmentLevelRandom.nextInt(maxLevel + 1);
               if(enchantmentLevelSelected == 0){enchantmentLevelSelected = 1;}
               if(enchantmentLevelSelected > selectedEnchantment.maxLevel()){enchantmentLevelSelected = selectedEnchantment.maxLevel();}

               giveEnchantment(player, selectedEnchantment, enchantmentLevelSelected, 100, 0);
               // Print the randomly selected key and its associated value
           } else {
               System.out.println("The map is empty, no items to select.");
           }
        }


        System.out.println(enchantmentManager.enchantments.size());
        System.out.println(player+ " " + rarity);
    }
}
