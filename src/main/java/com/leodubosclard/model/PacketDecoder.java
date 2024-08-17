package com.leodubosclard.model;

import static java.nio.charset.StandardCharsets.UTF_8;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.Charset;
import java.util.List;

public class PacketDecoder extends ReplayingDecoder<Packet> {
    private final Charset charset = UTF_8;

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> output) {
        Packet packet = new Packet();
        int msgLength = buffer.readInt();
        packet.setMessage(buffer.readCharSequence(msgLength, charset).toString());
        output.add(packet);
    }
}
