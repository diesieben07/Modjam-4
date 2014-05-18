package mod.badores.client.gui;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import mod.badores.BadOres;
import mod.badores.oremanagement.BadOre;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
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

	private final List<BadOre> sortedOres;

	public GuiBadOreBook() {
		sortedOres = Ordering.from(String.CASE_INSENSITIVE_ORDER)
				.onResultOf(new Function<BadOre, String>() {
					@Override
					public String apply(BadOre input) {
						return input.getDisplayName();
					}
				})
				.immutableSortedCopy(BadOres.oreManager.getAllOres());
	}

	@Override
	public void initGui() {
		super.initGui();
		@SuppressWarnings("unchecked")
		List<GuiButton> buttons = buttonList;

		int bookXBegin = (width - 192) / 2;

		buttons.add(nextPage = new GuiButtonChangePage(BUTTON_NEXT, bookXBegin + 120, 2 + 154, false));
		buttons.add(prevPage = new GuiButtonChangePage(BUTTON_PREV, bookXBegin + 38, 2 + 154, true));
		updateButtonState();
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
			case BUTTON_NEXT:
				++page;
				break;
			case BUTTON_PREV:
				--page;
				break;
		}
		updateButtonState();
	}

	private void updateButtonState() {
		nextPage.visible = page < sortedOres.size() - 1;
		prevPage.visible = page > 0;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float renderPartials) {
		int bookXStart = (width - 192) / 2;
		mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect(bookXStart, 2, 0, 0, 192, 192);

		BadOre ore = sortedOres.get(page);
		ItemStack stack = BadOres.oreManager.getBlockInfo(ore).asStack();
		itemRender.renderItemAndEffectIntoGUI(fontRendererObj, mc.renderEngine, stack, bookXStart + 40, 14);

		fontRendererObj.drawString("§n" + ore.getDisplayName(), bookXStart + 40 + 4 + 16, 17, 0x000000);

		super.drawScreen(mouseX, mouseY, renderPartials);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
