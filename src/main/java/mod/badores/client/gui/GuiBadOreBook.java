package mod.badores.client.gui;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;
import mod.badores.BadOres;
import mod.badores.ore.Doesntevenexistium;
import mod.badores.oremanagement.BadOre;
import mod.badores.oremanagement.OreBookPage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.util.Arrays;
import java.util.List;

/**
 * @author diesieben07
 */
public class GuiBadOreBook extends GuiScreen {

    static final ResourceLocation texture = new ResourceLocation("minecraft:textures/gui/book.png");

    private static final int BUTTON_NEXT = 0;
    private static final int BUTTON_PREV = 1;

    private int pageIndex = 0;
    private GuiButtonChangePage nextPage;
    private GuiButtonChangePage prevPage;

    private final List<OreBookPage> pages;

    public GuiBadOreBook() {
        pages = Ordering.from(String.CASE_INSENSITIVE_ORDER)
                .onResultOf(new Function<OreBookPage, String>() {
                    @Override
                    public String apply(OreBookPage input) {
                        return input.getDisplayName();
                    }
                })
                .immutableSortedCopy(Iterables.concat(BadOres.oreManager.getAllOres(), Arrays.asList(Doesntevenexistium.INSTANCE)));
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
                ++pageIndex;
                break;
            case BUTTON_PREV:
                --pageIndex;
                break;
        }
        updateButtonState();
    }

    private void updateButtonState() {
        nextPage.visible = pageIndex < pages.size() - 1;
        prevPage.visible = pageIndex > 0;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float renderPartials) {
        int bookXStart = (width - 192) / 2;
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(bookXStart, 2, 0, 0, 192, 192);

        OreBookPage page = pages.get(this.pageIndex);
        if (page instanceof BadOre) {
            ItemStack stack = BadOres.oreManager.getBlockInfo((BadOre) page).asStack();
            itemRender.renderItemAndEffectIntoGUI(fontRendererObj, mc.renderEngine, stack, bookXStart + 40, 14);
        }

        fontRendererObj.drawString("§n" + page.getDisplayName(), bookXStart + 40 + 4 + 16, 17, 0x000000);
        fontRendererObj.drawSplitString(page.getDescriptionText(), bookXStart + 40, 17 + 15, 115, 0x000000);

        super.drawScreen(mouseX, mouseY, renderPartials);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void keyTyped(char c, int key) {
        char lowerCase = Character.toLowerCase(c);
        if (key == Keyboard.KEY_ESCAPE) {
            mc.displayGuiScreen(null);
        } else if (Character.getType(lowerCase) == Character.LOWERCASE_LETTER) {
            for (int i = 0, len = pages.size(); i < len; ++i) {
                OreBookPage page = pages.get(i);
                if (Character.toLowerCase(page.getDisplayName().charAt(0)) == c) {
                    pageIndex = i;
                    updateButtonState();
                    break;
                }
            }
        }
    }
}
