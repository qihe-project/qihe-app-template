package com.example.analysis;

import pascal.qihe.framework.core.annotation.Analysis;
import pascal.qihe.framework.core.annotation.InjectAnalyses;
import pascal.qihe.platform.analysis.common.HierarchyAnalysis;
import pascal.qihe.platform.ir.Design;

@Analysis(name = "hello", description = "Example analysis.")
public class HelloAnalysis {

    // ----- Analysis Options ------

    @Analysis.Option(description = "Your name.")
    public String userName = "human";

    // ----- Dependencies -----

    private final HierarchyAnalysis hierarchy;

    @InjectAnalyses
    public HelloAnalysis(HierarchyAnalysis hierarchy) {
        this.hierarchy = hierarchy;
    }

    // ----- State in analysis -----

    private String result;

    // ----- Method to run analysis -----

    @Analysis.Run
    public void run(Design design) {
        System.out.println("Hello, " + userName);
        result = "The design has " + design.getModules().size() +
                " modules and the top modules are: " + hierarchy.getTopModules();
    }

    // ----- Query APIs -----

    public String getResult() {
        return result;
    }

}