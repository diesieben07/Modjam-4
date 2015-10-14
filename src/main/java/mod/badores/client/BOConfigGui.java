package mod.badores.client;

import com.google.common.collect.ImmutableList;
import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import mod.badores.BadOres;
import mod.badores.config.BOConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

import java.util.List;
import java.util.Set;

/**
 * @author diesieben07
 */
public class BOConfigGui extends GuiConfig {

    public BOConfigGui(GuiScreen parent) {
        super(parent,
                createConfigElements(),
                BadOres.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(BadOres.config.configFile.toString()));
    }

    @SuppressWarnings("unchecked")
    private static List<IConfigElement> createConfigElements() {
        return ImmutableList.builder()
                .addAll(new ConfigElement(BadOres.config.configFile.getCategory(BOConfig.CATEGORY_BALANCING)).getChildElements())
                .addAll(new ConfigElement(BadOres.config.configFile.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements())
                .build();
    }

    public static final class Factory implements IModGuiFactory {

        @Override
        public void initialize(Minecraft mc) {

        }

        @Override
        public Class<? extends GuiScreen> mainConfigGuiClass() {
            return BOConfigGui.class;
        }

        @Override
        public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
            return null;
        }

        @Override
        public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
            return null;
        }

    }

}
