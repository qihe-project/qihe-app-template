package com.example.analysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pascal.qihe.framework.core.DiagnosticManager;
import pascal.qihe.framework.core.DiagnosticReporter;
import pascal.qihe.framework.core.annotation.Analysis;
import pascal.qihe.framework.core.annotation.InjectAnalyses;
import pascal.qihe.platform.analysis.common.HierarchyAnalysis;
import pascal.qihe.platform.ir.Design;

@Analysis(name = "hello", description = "Example analysis.")
public class HelloAnalysis {

    private static final Logger logger = LoggerFactory.getLogger(HelloAnalysis.class);

    // ----- Analysis Options ------

    @Analysis.Option(description = "Your name.")
    public String userName = "human";

    // ----- Dependencies -----

    private final HierarchyAnalysis hierarchy;
    private final DiagnosticReporter reporter;

    @InjectAnalyses
    public HelloAnalysis(HierarchyAnalysis hierarchy,
                         DiagnosticManager diagnosticManager) {
        this.hierarchy = hierarchy;
        this.reporter = diagnosticManager.getReporter(getClass());
    }

    // ----- State in analysis -----

    private String result;

    // ----- Method to run analysis -----

    @Analysis.Run
    public void run(Design design) {
        logger.info("The design has {} modules", design.getModules().size());
        if (design.getModules().isEmpty()) {
            result = "Sorry, " + userName + ". No top module found";
            reporter.warning(design, "The design is empty.");
        } else {
            result = "Hello, " + userName + ". The top modules are: " + hierarchy.getTopModules();
        }
    }

    // ----- Query APIs -----

    public String getResult() {
        return result;
    }

}