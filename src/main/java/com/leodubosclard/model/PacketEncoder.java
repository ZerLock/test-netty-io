package com.leodubosclard.model;

import static java.nio.charset.StandardCharsets.UTF_8;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

public class PacketEncoder extends MessageToByteEncoder<Packet> {
    private final Charset charset = UTF_8;

    @Override
    public void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf output) {
        output.writeInt(packet.getMessage().length());
        output.writeCharSequence(packet.getMessage(), charset);
    }
}
