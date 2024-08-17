package com.leodubosclard.server;

import static io.netty.channel.ChannelOption.SO_BACKLOG;
import static io.netty.channel.ChannelOption.SO_KEEPALIVE;

import com.leodubosclard.Instance;
import com.leodubosclard.Logger;
import com.leodubosclard.model.PacketDecoder;
import com.leodubosclard.model.PacketEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server implements Instance {
    private final int port;

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        EventLoopGroup rootGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(rootGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        public void initChannel(SocketChannel channel) {
                            channel.pipeline()
                                    .addLast(
                                            new PacketDecoder(),
                                            new PacketEncoder(),
                                            new ServerHandler()
                                    );
                        }
                    })
                    .option(SO_BACKLOG, 128)
                    .childOption(SO_KEEPALIVE, true);

            ChannelFuture future = bootstrap.bind(port).sync();
            Logger.log("Server listening on port=" + port + " to client connections...");
            future.channel().closeFuture().sync();
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            workerGroup.shutdownGracefully();
            rootGroup.shutdownGracefully();
        }
    }
}
