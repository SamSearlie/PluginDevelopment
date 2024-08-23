package defiantpvp.searlie.eventHandler;

import defiantpvp.searlie.customEnchantments.enchantmentManager;
import defiantpvp.searlie.customEnchantments.enums.enchantmentStacks;
import defiantpvp.searlie.customEnchantments.enums.enchantmentType;
import defiantpvp.searlie.customEnchantments.interfaces.enchantmentInterface;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;


public class attackDamage implements Listener {

    //Player Damaging Function
    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event){
        Entity damager = event.getDamager();
        if (damager instanceof Player) {
            Player player = (Player) damager;
            List <ItemStack> equippedItems = itemList(player);
            for(enchantmentInterface enchantment : enchantmentManager.enchantments.values()){
                if(enchantment.enchantType().equals(enchantmentType.OFFENSIVE)){
                    int enchantmentLevel = enchantmentManager.hasEnchantment(equippedItems, enchantment.getName());
                    System.out.println("After Attack: "+ enchantmentLevel);
                    if(enchantmentLevel > 0){
                        if(enchantment.stacks().equals(enchantmentStacks.TRUE)){
                            enchantment.applyOffensive(enchantmentLevel, event);
                        } else if (enchantmentLevel > enchantment.maxLevel()) {
                            enchantment.applyOffensive(enchantment.maxLevel(), event);
                        }
                    }
                }
            }
        }
    }

    //Player Damaged Function
    @EventHandler
    public void onPlayerDamaged(EntityDamageEvent e){
        Entity eventPlayer = e.getEntity();
        if (eventPlayer instanceof Player) {
            Player player = (Player) eventPlayer;
            List <ItemStack> equippedItems = itemList(player);
            for(enchantmentInterface enchantment : enchantmentManager.enchantments.values()){
                if(enchantment.enchantType().equals(enchantmentType.DEFENSIVE)){
                    int a = enchantmentManager.hasEnchantment(equippedItems, enchantment.getName());
                    System.out.println(a + " " + enchantment.getName());
                    if(a > 0){
                        if(enchantment.stacks().equals(enchantmentStacks.TRUE)){
                            enchantment.applyDefensive(a, e);
                        } else if (a > enchantment.maxLevel()) {
                            enchantment.applyDefensive(enchantment.maxLevel(), e);
                        }
                    }
                }
            }
        }
    }


    private List<ItemStack> itemList(Player player){
        // Get the player's armor
        ItemStack[] armorContents = player.getInventory().getArmorContents();
        // Get the weapon in the player's main hand
        ItemStack weapon = player.getItemInHand();
        // Combine armor and weapon into a list
        List<ItemStack> equipment = new ArrayList<>();
        // Add armor pieces to the list
        for (ItemStack armor : armorContents) {
            if (armor != null) {
                equipment.add(armor);
            }
        }
        // Add the weapon to the list
        if (weapon != null) {
            equipment.add(weapon);
        }
        return  equipment;
    }
}
