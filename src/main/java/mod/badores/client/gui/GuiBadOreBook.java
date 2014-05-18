package mod.badores.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.util.List;

/**
 * @author diesieben07
 */
public class GuiBadOreBook extends GuiScreen {

	static final ResourceLocation texture = new ResourceLocation("minecraft:textures/gui/book.png");

	private static final int BUTTON_NEXT = 0;
	private static final int BUTTON_PREV = 1;

	private int page = 0;
	private GuiButtonChangePage nextPage;
	private GuiButtonChangePage prevPage;

	@Override
	public void initGui() {
		super.initGui();
		@SuppressWarnings("unchecked")
		List<GuiButton> buttons = buttonList;

		int bookXBegin = (width - 192) / 2;

		buttons.add(nextPage = new GuiButtonChangePage(1, bookXBegin + 120, 2 + 154, false));
		buttons.add(prevPage = new GuiButtonChangePage(2, bookXBegin + 38, 2 + 154, true));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float renderPartials) {
		mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect((width - 192) / 2, 2, 0, 0, 192, 192);

		super.drawScreen(mouseX, mouseY, renderPartials);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
