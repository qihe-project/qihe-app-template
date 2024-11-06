package com.example.analysis;

import org.junit.jupiter.api.Test;
import pascal.qihe.framework.core.AnalysisOptions;

import java.io.IOException;

import static com.example.analysis.AnalysisTests.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloAnalysisTest {

    @Test
    void testResult() throws IOException {
        var design = loadAnalysisTestCase("hello.v");
        var helloAnalysis = runAnalysis(design, HelloAnalysis.class);
        assertEquals("The design has 2 modules and the top modules are: [a]",
                helloAnalysis.getResult());
    }

    @Test
    void testOption() throws IOException {
        var design = loadAnalysisTestCase("hello.v");
        var helloAnalysis = runAnalysisWithOptions(design, AnalysisOptions.loadToml("""
                [hello]
                userName="world"
                """), HelloAnalysis.class);
        assertEquals("world", helloAnalysis.userName);
    }

}