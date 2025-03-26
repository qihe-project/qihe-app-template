package com.example.analysis;

import org.junit.jupiter.api.Test;
import pascal.qihe.framework.core.AnalysisOptions;

import java.io.IOException;

import static com.example.analysis.AnalysisTests.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloAnalysisTest {

    @Test
    void testResult() throws IOException {
        // Load the test case "hello.v" from the directory "test/src/resources/testcases/analysis"
        // into a Design object.
        var design = loadAnalysisTestCase("hello.v");
        // Run the HelloAnalysis on the Design object, and get the instance of that analysis.
        var helloAnalysis = runAnalysis(design, HelloAnalysis.class);
        // Assert that the analysis result matches the expected output.
        assertEquals("Hello, human. The top modules are: [a]",
                helloAnalysis.getResult());
    }

    @Test
    void testOption() throws IOException {
        var design = loadAnalysisTestCase("hello.v");
        // Run the HelloAnalysis on the Design object, with a TOML configuration file
        // to set the value of the `userName` option.
        var helloAnalysis = runAnalysisWithOptions(design, AnalysisOptions.loadToml("""
                [hello]
                userName="world"
                """), HelloAnalysis.class);
        // Assert that the `userName` option is set to the expected value.
        assertEquals("world", helloAnalysis.userName);
    }

}