package mod.badores.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import mod.badores.BadOres;
import mod.badores.util.I18n;

import javax.swing.*;

/**
 * @author diesieben07
 */
public class PacketRandomTranslation implements IMessage {

	private String baseKey;

	public PacketRandomTranslation(String baseKey) {
		this.baseKey = baseKey;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		baseKey = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, baseKey);
	}

	public static class Handle implements IMessageHandler<PacketRandomTranslation, IMessage> {

		@Override
		public IMessage onMessage(PacketRandomTranslation message, MessageContext ctx) {
			BadOres.proxy.handleRandomTranslation(message);

			return null;
		}
	}

}
