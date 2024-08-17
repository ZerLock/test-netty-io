package com.leodubosclard;

import io.netty.channel.ChannelHandlerContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static String now() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(LocalDateTime.now());
    }

    public static <T> void log(ChannelHandlerContext ctx, T message) {
        System.out.println(now() + " [" + ctx.name() + "] " + message);
    }

    public static <T> void log(T message) {
        System.out.println(now() + " [] " + message);
    }
}
