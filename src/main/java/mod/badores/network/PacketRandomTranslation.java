package mod.badores.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import mod.badores.BadOres;

/**
 * @author diesieben07
 */
public class PacketRandomTranslation implements IMessage {

	public String baseKey;

	public PacketRandomTranslation() {
	}

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
