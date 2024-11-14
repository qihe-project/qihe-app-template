package com.example;

import pascal.qihe.platform.boot.QiheAnalysisApplication;
import pascal.qihe.platform.boot.QiheCli;

@QiheAnalysisApplication(scanBasePackages = "com.example.analysis")
public class Main {

    public static void main(String[] args) {
        QiheCli.boot(Main.class, args);
    }

}