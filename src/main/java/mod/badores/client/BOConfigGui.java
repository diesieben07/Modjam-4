package mod.badores.client;

import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.config.GuiConfig;
import mod.badores.BadOres;
import mod.badores.config.BOConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

import java.util.Set;

/**
 * @author diesieben07
 */
public class BOConfigGui extends GuiConfig {

    @SuppressWarnings("unchecked")
    public BOConfigGui(GuiScreen parent) {
        super(parent,
                new ConfigElement(BadOres.config.configFile.getCategory(BOConfig.CATEGORY_BALANCING)).getChildElements(),
                BadOres.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(BadOres.config.configFile.toString()));
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
