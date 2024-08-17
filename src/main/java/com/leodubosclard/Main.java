package com.leodubosclard;

 public class Main {
    public static void main(String[] args) {
        InstanceFactory.create(args).ifPresentOrElse(Instance::run, () -> {
            System.err.println("USAGE:");
            System.err.println("\thost <port>");
            System.err.println("\tconnect <host> <port>");
        });
    }
}
