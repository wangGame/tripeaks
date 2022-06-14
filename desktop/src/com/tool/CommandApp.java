package com.tool;

import com.tool.tools.CommandLineApplication;

public class CommandApp {
    public static void main(String[] args) {
        new CommandLineApplication("CommandLineApp", args) {
            @Override
            protected int run(String[] arguments) {
                System.out.println("------------");
                return 0;
            }
        };
    }
}
