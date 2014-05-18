package mod.badores.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mod.badores.BOProxy;
import mod.badores.client.gui.GuiBadOreBook;
import mod.badores.client.rendering.RenderFleeingBlock;
import mod.badores.entities.EntityFleeingBlock;
import mod.badores.network.PacketRandomTranslation;
import mod.badores.util.I18n;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.common.config.Configuration;

import java.awt.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author diesieben07
 */
public class BOClientProxy implements BOProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event, Configuration config) {

	}

	@Override
	public void init(FMLInitializationEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityFleeingBlock.class, new RenderFleeingBlock());
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {

	}

	// TODO: figure out a way to make this truly random
	private static final List<String> urls = Arrays.asList(
			"http://www.minecraft.net",
			"http://www.minecraftforge.net",
			"http://www.google.com",
			"http://www.minecraftforum.net",
			"http://www.minecraftwiki.net",
			"http://mcp.ocean-labs.de/modjam/"
	);

	private static final Random r = new Random();

	@Override
	public void openRandomWebsite() {
		try {
			Desktop.getDesktop().browse(new URL(urls.get(r.nextInt(urls.size()))).toURI());
		} catch (Exception e) {
			Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentTranslation("badores.website.fail"));
		}
	}

	@Override
	public void handleRandomTranslation(PacketRandomTranslation message) {
		Minecraft mc = Minecraft.getMinecraft();
		int count = Integer.parseInt(I18n.translate(message.baseKey + ".count"));
		int n = mc.theWorld.rand.nextInt(count);
		mc.thePlayer.addChatComponentMessage(new ChatComponentTranslation(message.baseKey + '.' + n, message.data));
	}

	@Override
	public void openBook() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiBadOreBook());
	}
}
