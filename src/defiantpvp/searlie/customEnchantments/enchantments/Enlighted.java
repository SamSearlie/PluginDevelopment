package defiantpvp.searlie.customEnchantments.enchantments;

import defiantpvp.searlie.customEnchantments.enums.enchantmentApplyType;
import defiantpvp.searlie.customEnchantments.enums.enchantmentRarity;
import defiantpvp.searlie.customEnchantments.enums.enchantmentStacks;
import defiantpvp.searlie.customEnchantments.enums.enchantmentType;
import defiantpvp.searlie.customEnchantments.interfaces.enchantmentInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class Enlighted implements enchantmentInterface {

    @Override
    public String getName() {return "Enlighted";}
    @Override
    public String getDescription(){return "Heals the defender";}
    @Override
    public int maxLevel() {return 3;}

    @Override
    public enchantmentApplyType getApplyType() {return enchantmentApplyType.ARMOR;}

    @Override
    public enchantmentRarity getRarity() {return enchantmentRarity.LEGENDARY;}

    @Override
    public enchantmentStacks stacks() {return enchantmentStacks.TRUE;}

    @Override
    public enchantmentType enchantType() {return enchantmentType.DEFENSIVE;}

    @Override
    public void applyOffensive(int a, EntityDamageByEntityEvent event){
        System.out.println("Damage Reduction: " + a);
    }

    @Override
    public void applyPassiveEffect(Player player, int enchantmentLevel){}

    @Override
    public int applyDefensive(int enchantmentLevel, EntityDamageEvent event){return (int) 1.2;}

    @Override
    public void removeEffect(Player player){}

}
