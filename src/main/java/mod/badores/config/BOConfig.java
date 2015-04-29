package mod.badores.config;

import mod.badores.BadOres;
import net.minecraftforge.common.config.Configuration;

import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

/**
 * Created by Lukas Tenbrink on 29.04.2015.
 */
public class BOConfig {

    public static final String CATEGORY_BALANCING = "balancing";

    public static boolean enableGameBreakingFeatures;

    public static void loadConfig(String configID) {

        if (configID == null || configID.equals(CATEGORY_BALANCING)) {
            enableGameBreakingFeatures = BadOres.config.get(CATEGORY_BALANCING, "enableGameBreakingFeatures", true).getBoolean(true);
        }

        BadOres.proxy.loadConfig(configID);
    }
}
