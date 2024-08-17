package com.leodubosclard;

import com.leodubosclard.client.Client;
import com.leodubosclard.server.Server;

import java.util.Optional;

public class InstanceFactory {

    public static Optional<Instance> create(String[] args) {
        if (isServer(args)) {
            return Optional.of(new Server(Integer.parseInt(args[1])));
        }
        if (isClient(args)) {
            return Optional.of(new Client(args[1], Integer.parseInt(args[2])));
        }
        return Optional.empty();
    }

    private static boolean isServer(String[] args) {
        return args.length == 2 && commandMatch(args[0], "host") && isValidPort(args[1]);
    }

    private static boolean isClient(String[] args) {
        return args.length == 3 && commandMatch(args[0], "connect") && isValidPort(args[2]);
    }

    private static boolean commandMatch(String in, String cmd) {
        return in.equals(cmd);
    }

    private static boolean isValidPort(String port) {
        try {
            Integer.parseInt(port);
            return true;
        } catch(NumberFormatException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

}
