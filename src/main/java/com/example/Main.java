package com.example;

import com.example.analysis.AppAnalysisConfig;
import org.springframework.context.annotation.Import;
import pascal.qihe.platform.boot.QiheAnalysisApplication;
import pascal.qihe.platform.boot.QiheCli;

@QiheAnalysisApplication
@Import(AppAnalysisConfig.class)
public class Main {

    public static void main(String[] args) {
        QiheCli.boot(Main.class, args);
    }

}