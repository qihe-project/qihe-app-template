package com.example.analysis;

import pascal.qihe.framework.core.annotation.Analysis;
import pascal.qihe.framework.core.annotation.InjectAnalyses;
import pascal.qihe.platform.analysis.common.HierarchyAnalysis;
import pascal.qihe.platform.ir.Module;

import java.util.stream.Collectors;

@Analysis(name = "hello")
public class HelloAnalysis {

    private final HierarchyAnalysis hierarchy;

    @InjectAnalyses
    public HelloAnalysis(HierarchyAnalysis hierarchy) {
        this.hierarchy = hierarchy;
    }

    @Analysis.Run
    public void run() {
        var topModuleNames = hierarchy.getTopModules().stream()
                .map(Module::getName)
                .collect(Collectors.joining(","));
        System.out.println("Hello, top modules: " + topModuleNames);
    }

}
