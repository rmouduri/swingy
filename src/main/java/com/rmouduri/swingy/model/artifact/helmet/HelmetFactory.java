package com.rmouduri.swingy.model.artifact.helmet;

public class HelmetFactory {
    /**
     * Generate a fresh helmet of class @param helmetClass
     * @param helmetClass
     * @param tier
     * @return The fresh helmet
     */
    public static Helmet generateHelmet(final String helmetClass, final int tier) {
        if (tier < 0) {
            return null;
        }

        if (Bycocket.DEFAULT_HELMET_TYPE_NAME.equals(helmetClass)) {
            return new Bycocket(tier);
        } else if (HeavyHelmet.DEFAULT_HELMET_TYPE_NAME.equals(helmetClass)) {
            return new HeavyHelmet(tier);
        } else if (MagicHat.DEFAULT_HELMET_TYPE_NAME.equals(helmetClass)) {
            return new MagicHat(tier);
        }

        return null;
    }
}
