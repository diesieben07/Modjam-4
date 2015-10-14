package mod.badores.config;

import mod.badores.BadOres;
import mod.badores.oremanagement.BadOre;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Dries007
 * @author Lukas Tenbrink
 */
public class BOConfig {
    public static final String CATEGORY_BALANCING = "balancing";

    private final Set<BadOre> disabledOreGeneration = new HashSet<>();
    private boolean disableStartingBook = false;

    public final Configuration configFile;

    public BOConfig(File suggestedConfigurationFile) {
        configFile = new Configuration(suggestedConfigurationFile);
    }

    public boolean isStartingBookDisabled() {
        return disableStartingBook;
    }

    public boolean isOreGenerationDisabled(BadOre badOre) {
        return disabledOreGeneration.contains(badOre);
    }

    public void sync() {
        disabledOreGeneration.clear();

        // Make array of valid values (oreNames)
        List<BadOre> allOres = BadOres.oreManager.getAllOres();
        String[] oreNames = new String[allOres.size()];
        for (int i = 0; i < allOres.size(); i++) {
            oreNames[i] = allOres.get(i).getName();
        }

        // Actual config parsing
        String[] disabledOres = configFile.getStringList("disabledOreGeneration", CATEGORY_BALANCING, new String[0], "List of Ores that should not generate.\nValid values: " + Arrays.toString(oreNames) + "\nCase sensitive!", oreNames);

        for (String disabledOre : disabledOres) {
            BadOre badOre = BadOres.oreManager.getOreByName(disabledOre);
            if (badOre != null) {
                BadOres.logger.info("Disabled worldgen of {}.", disabledOre);
                disabledOreGeneration.add(badOre);
            } else {
                BadOres.logger.fatal("Configuration fault! '{}' is not a known ore!", disabledOre);
            }
        }

        disableStartingBook = configFile.getBoolean("disableStartingBook", Configuration.CATEGORY_GENERAL, false, "Disables spawning of the starting book for new players");

        if (configFile.hasChanged()) {
            configFile.save();
        }
    }
}
