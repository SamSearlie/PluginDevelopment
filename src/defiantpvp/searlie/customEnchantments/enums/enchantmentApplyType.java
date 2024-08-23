package defiantpvp.searlie.customEnchantments.enums;

import java.util.Collections;
import java.util.Set;

public enum enchantmentApplyType {
    ARMOR(Set.of("HELMET", "CHESTPLATE", "LEGGINGS", "BOOTS")),
    BOOTS(Set.of("BOOTS")),
    LEGGINGS(Set.of("LEGGINGS")),
    CHESTPLATE(Set.of("CHESTPLATE")),
    HELMET(Set.of("HELMET")),
    WEAPON(Set.of("SWORD", "AXE")),
    AXE(Set.of("AXE")),
    SWORD(Set.of("SWORD")),
    HOOK(Set.of("HOE"));


    private final Set<String> equipment;

    enchantmentApplyType(Set<String> equipment) {
        this.equipment = Collections.unmodifiableSet(equipment);
    }

    // Get the equipment types
    public Set<String> getEquipment() {
        return equipment;
    }

    public enchantmentApplyType appliesTo(String equipmentType, enchantmentApplyType type) {
        System.out.println(equipment);

        // Split the material name at the underscore
        String[] parts = equipmentType.split("_");

        // Ensure that the parts array has at least two elements
        if (parts.length > 1 && equipment.contains(parts[1])) {
            return type;
        } else {
            return null; // Or handle this case differently if needed
        }
    }

}
