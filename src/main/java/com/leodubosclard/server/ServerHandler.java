package com.leodubosclard.server;

import com.leodubosclard.Logger;
import com.leodubosclard.model.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        Logger.log(ctx, "New client connected");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        Logger.log(ctx, "A client was disconnected");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Logger.log(ctx, "Received packet from client: " +  msg);
        Packet res = new Packet("Hello client!");
        ctx.writeAndFlush(res);
    }
}
