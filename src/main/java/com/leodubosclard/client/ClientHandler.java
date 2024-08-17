package com.leodubosclard.client;

import com.leodubosclard.Logger;
import com.leodubosclard.model.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Logger.log(ctx, "Connected to server!");
        Packet packet = new Packet("Hello server!");
        ctx.writeAndFlush(packet);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Logger.log(ctx, "Received packet from server: " + msg);
    }
}
