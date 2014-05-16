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
	public String[] data;

	public PacketRandomTranslation() {
	}

	public PacketRandomTranslation(String baseKey, String... data) {
		this.baseKey = baseKey;
		this.data = data;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		baseKey = ByteBufUtils.readUTF8String(buf);
		int len = buf.readUnsignedByte();
		data = new String[len];
		for (int i = 0; i < len; ++i) {
			data[i] = ByteBufUtils.readUTF8String(buf);
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, baseKey);
		buf.writeByte(data.length);
		for (String s : data) {
			ByteBufUtils.writeUTF8String(buf, s);
		}
	}

	public static class Handle implements IMessageHandler<PacketRandomTranslation, IMessage> {

		@Override
		public IMessage onMessage(PacketRandomTranslation message, MessageContext ctx) {
			BadOres.proxy.handleRandomTranslation(message);
			return null;
		}
	}

}
