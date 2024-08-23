package defiantpvp.searlie.customEnchantments;

import defiantpvp.searlie.customEnchantments.enchantments.Crushing;
import defiantpvp.searlie.customEnchantments.enchantments.Enlighted;
import defiantpvp.searlie.customEnchantments.enchantments.GodlyHealthBoost;
import defiantpvp.searlie.customEnchantments.enchantments.HealthBoost;
import defiantpvp.searlie.customEnchantments.enums.enchantmentType;
import defiantpvp.searlie.customEnchantments.interfaces.enchantmentInterface;
import defiantpvp.searlie.utilities.numberManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class enchantmentManager {
    public static final Map<String, enchantmentInterface> enchantments = new HashMap<>();

    public static void initializeEnchantments(){
        addEnchantment(new Crushing());
        addEnchantment(new Enlighted());
        addEnchantment(new HealthBoost());
        addEnchantment(new GodlyHealthBoost());

        /*
        for (enchantmentInterface enchantment : enchantments.values()){
            System.out.println("Initialized: " + enchantment);
        }
        */
    }
    private static void addEnchantment(enchantmentInterface enchantment){
        enchantments.put(enchantment.getName(), enchantment);
    }

    //Has Enchantments
    public static int hasEnchantment(List<ItemStack> items, String enchantmentName) {

        int enchantmentCount = 0;

        for (ItemStack item : items) {
            if (item == null || !item.hasItemMeta()) continue;
            ItemMeta meta = item.getItemMeta();
            if (meta == null || !meta.hasLore()) continue;
            if (meta == null || !meta.hasLore()) continue;
            List<String> lore = meta.getLore();
            for (String line : lore) {
                if (line.contains(enchantmentName)) {
                    int enchantmentLevel = numberManager.extractAndConvert(line);
                    enchantmentCount +=enchantmentLevel;
                }
            }
        }
        return enchantmentCount;
    }


    private static String hasPassiveEnchantment(ItemStack item, String enchantmentName){
        if(item == null || !item.hasItemMeta()) return "false";
        ItemMeta meta = item.getItemMeta();
        if(meta == null || !meta.hasLore()) return "false";
        List<String> lore = meta.getLore();

        for(String line : lore){
            if(line.contains(enchantmentName)) {
                return line;
            }
        }
        return "false";
    }

    //Apply Passive Enchantment
    public static void passiveEnchantment(Player player, ItemStack oldItem, ItemStack newItem){
        for(enchantmentInterface enchantment : enchantments.values()){
            if(enchantment.enchantType().equals(enchantmentType.PASSIVE)){
                //Remove old effect
                if(!hasPassiveEnchantment(oldItem, enchantment.getName()).equals("false")){
                    enchantment.removeEffect(player);
                }
                //Apply new effects
                String enchantmentName = hasPassiveEnchantment(newItem, enchantment.getName());
                if(!enchantmentName.equals("false")){
                    int enchantmentLevel = numberManager.extractAndConvert(enchantmentName);
                    enchantment.applyPassiveEffect(player, enchantmentLevel);
                }
            }
        }
    }

}
