package defiantpvp.searlie;

import defiantpvp.searlie.customEnchantments.Consumables.applyEnchantment;
import defiantpvp.searlie.customEnchantments.Consumables.runePouch;
import defiantpvp.searlie.customEnchantments.enchantmentManager;
import defiantpvp.searlie.eventHandler.attackDamage;
import defiantpvp.searlie.eventHandler.equipArmor;
import defiantpvp.searlie.eventHandler.inventoryManager;
import org.bukkit.plugin.java.JavaPlugin;


public class main extends JavaPlugin {

    @Override
    public void onEnable(){
        System.out.println("Primary Plugin has started");
        getServer().getPluginManager().registerEvents(new attackDamage(), this);
        getServer().getPluginManager().registerEvents(new equipArmor(), this);
        getServer().getPluginManager().registerEvents(new runePouch(), this);
        getServer().getPluginManager().registerEvents(new applyEnchantment(), this);
        getServer().getPluginManager().registerEvents(new inventoryManager(), this);

        enchantmentManager.initializeEnchantments();
    }

    public static String buildMessage(String loreAdd) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= 1; i++) {
            builder.append(loreAdd);
            if (i != 1)
                builder.append(" ");
        }
        System.out.println(builder);
        return builder.toString();
    }

    @Override
    public void onDisable(){
        System.out.println("Primary Plugin has stopped");
    }

}
