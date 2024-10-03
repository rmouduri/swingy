package com.rmouduri.swingy.model.artifact.armor;

public class ArmorFactory {
    /**
     * Generate a fresh armor of class @param armorClass
     * @param armorClass
     * @param tier
     * @return The fresh armor
     */
    public static Armor generateArmor(final String armorClass, final int tier) {
        if (tier < 0) {
            return null;
        }

        if (HeavyArmor.DEFAULT_ARMOR_TYPE_NAME.equals(armorClass)) {
            return new HeavyArmor(tier);
        } else if (LeatherArmor.DEFAULT_ARMOR_TYPE_NAME.equals(armorClass)) {
            return new LeatherArmor(tier);
        } else if (Robe.DEFAULT_ARMOR_TYPE_NAME.equals(armorClass)) {
            return new Robe(tier);
        }

        return null;
    }
}
