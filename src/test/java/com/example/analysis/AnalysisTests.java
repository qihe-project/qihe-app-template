package com.example.analysis;

import pascal.qihe.framework.core.AnalysisContext;
import pascal.qihe.framework.core.AnalysisOptions;
import pascal.qihe.framework.engine.AnalysisEngine;
import pascal.qihe.framework.engine.AnalysisRetainMode;
import pascal.qihe.platform.analysis.QiheAnalysisEngine;
import pascal.qihe.platform.analysis.QiheBuiltInAnalysisConfig;
import pascal.qihe.platform.ir.Design;
import pascal.qihe.util.collection.ClassInstanceMap;

import java.io.IOException;
import java.nio.file.Path;

public class AnalysisTests {

    public final static Path TESTCASE_DIR = TestCases.testCasesPathOf("analysis/");

    public static Design loadAnalysisTestCase(String fileName) throws IOException {
        return TestCases.loadTestCase(TESTCASE_DIR.resolve(fileName));
    }

    public static <T> T runAnalysis(Design design, Class<T> cls) {
        var map = runAnalyses(design, cls);
        return map.getInstanceOrThrow(cls);
    }

    public static ClassInstanceMap runAnalyses(Design design, Class<?>... analysisClasses) {
        return runAnalysesWithOptions(design, null, analysisClasses);
    }

    public static <T> T runAnalysisWithOptions(Design design, AnalysisOptions options,
                                               Class<T> cls) {
        var map = runAnalysesWithOptions(design, options, cls);
        return map.getInstanceOrThrow(cls);
    }

    public static ClassInstanceMap runAnalysesWithOptions(Design design, AnalysisOptions options,
                                                          Class<?>... analysisClasses) {
        return runAnalysesWithOptionsAndMode(design, options,
                AnalysisRetainMode.REQUESTED_ONLY, analysisClasses);
    }

    public static ClassInstanceMap runAnalysesWithOptionsAndMode(
            Design design, AnalysisOptions options,
            AnalysisRetainMode mode, Class<?>... analysisClasses) {
        return buildEngine(design, options, mode, analysisClasses).execute();
    }

    public static AnalysisEngine buildEngine(
            Design design, AnalysisOptions options,
            AnalysisRetainMode mode, Class<?>... analysisClasses) {
        var ctx = AnalysisContext.builder()
                .register(QiheBuiltInAnalysisConfig.class)
                .register(AppAnalysisConfig.class)
                .withOptions(options)
                .build();
        return new QiheAnalysisEngine(ctx, design)
                .requestAnalyses(analysisClasses)
                .setAnalysisRetainMode(mode);
    }

}
