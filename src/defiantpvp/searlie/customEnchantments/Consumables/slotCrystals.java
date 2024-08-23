package defiantpvp.searlie.customEnchantments.Consumables;

import defiantpvp.searlie.utilities.loreManager;
import org.bukkit.inventory.ItemStack;

public class slotCrystals {

    static public void checkSlotCrystal(ItemStack item){

        if(item.hasItemMeta()){
            int loreAmount = item.getItemMeta().getLore().size();

            if(item.getItemMeta().getLore().get(loreAmount - 1).contains("Slot Crystal")){
                System.out.println("Has Slot Crystals: " + item.getItemMeta().getLore().get(loreAmount -1 ) );
                return;
            }

            for(String a : item.getItemMeta().getLore()){
                if(a.contains("Slot Crystal")){
                    loreManager.updateSlotCrystal(item, 0);
                    return;
                }
            }
        }
        loreManager.addSlotCrystal(item);
    }
}
