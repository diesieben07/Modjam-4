package mod.badores.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

/**
 * @author diesieben07
 */
public class GuiBadOreBook extends GuiScreen {

	static final ResourceLocation texture = new ResourceLocation("minecraft:textures/gui/book.png");
	private int page = 0;

	@Override
	public void drawScreen(int mouseX, int mouseY, float renderPartials) {
		super.drawScreen(mouseX, mouseY, renderPartials);

		mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect((width - 192) / 2, 2, 0, 0, 192, 192);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
