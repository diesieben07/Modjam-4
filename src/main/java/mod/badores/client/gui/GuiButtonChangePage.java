package mod.badores.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import static org.lwjgl.opengl.GL11.glColor4f;

/**
 * @author diesieben07
 */
public class GuiButtonChangePage extends GuiButton {

	private final boolean previous;

	public GuiButtonChangePage(int id, int x, int y, boolean previous) {
		super(id, x, y, 23, 13, "");
		this.previous = previous;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if (visible) {
			boolean mouseOver = mouseX >= xPosition && mouseY >= yPosition && mouseX < xPosition + width && mouseY < yPosition + height;
			glColor4f(1, 1, 1, 1);
			mc.renderEngine.bindTexture(GuiBadOreBook.texture);
			int u = 0;
			int v = 192;

			if (mouseOver) {
				u += 23;
			}

			if (previous) {
				v += 13;
			}

			drawTexturedModalRect(xPosition, yPosition, u, v, 23, 13);
		}
	}
}
