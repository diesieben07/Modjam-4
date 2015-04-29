package mod.badores.config;

import mod.badores.BadOres;
import net.minecraftforge.common.config.Configuration;

import java.util.Collections;
import java.util.Set;

import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

/**
 * Created by Lukas Tenbrink on 29.04.2015.
 */
public class BOConfig {

    public static final String CATEGORY_BALANCING = "balancing";

    private static Set<String> disabledOreGeneration;

    public static void loadConfig(String configID) {

        if (configID == null || configID.equals(CATEGORY_BALANCING)) {

            disabledOreGeneration.clear();
            Collections.addAll(disabledOreGeneration, BadOres.config.get(CATEGORY_BALANCING, "disabledOreGeneration", new String[0], "List of Ores that should not generate.").getStringList());
        }

        BadOres.proxy.loadConfig(configID);
    }

    public static boolean isOreGenerationDisabled(String oreName)
    {
        return disabledOreGeneration.contains(oreName);
    }
}
