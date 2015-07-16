package mod.badores.util;

import mod.badores.BadOres;
import net.minecraft.util.StatCollector;

/**
 * @author diesieben07
 */
public class I18n {

    public static String translate(String key) {
        return StatCollector.translateToLocal(key);
    }

    public static String translate(String key, Object... args) {
        return StatCollector.translateToLocalFormatted(key, args);
    }

    public static String translateBO(String key) {
        return StatCollector.translateToLocal(BadOres.MOD_ID + '.' + key);
    }

    public static String translateBO(String key, Object... args) {
        return StatCollector.translateToLocalFormatted(BadOres.MOD_ID + '.' + key, args);
    }

}
