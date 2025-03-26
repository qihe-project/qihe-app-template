package com.example.analysis;

import pascal.qihe.platform.io.Frontend;
import pascal.qihe.platform.io.binary.DesignLoader;
import pascal.qihe.platform.io.text.DesignParser;
import pascal.qihe.platform.io.text.DesignPrinter;
import pascal.qihe.platform.io.verilog.SlangFrontend;
import pascal.qihe.platform.io.verilog.VerilogFrontend;
import pascal.qihe.platform.ir.Design;
import pascal.qihe.util.exception.NoInstanceError;
import pascal.qihe.util.print.NotepadWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Utilities for test cases.
 */
public class TestCases {

    private TestCases() {
        throw new NoInstanceError();
    }

    // ---------- Paths of test cases. ----------

    /**
     * @return a path relative to "src/test/resources/testcases"
     */
    public static Path testCasesPathOf(String path) {
        return Path.of("src/test/resources/testcases", path);
    }

    public static String testCaseName(Path testCasePath) {
        return testCasePath.getFileName().toString();
    }

    public static Path replacePathExtension(Path path, String newExtension) {
        return path.getParent().resolve(
                replaceFileExtension(path.getFileName().toString(), newExtension));
    }

    public static String replaceFileExtension(String fileName, String newExtension) {
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return fileName + newExtension;
        }
        return fileName.substring(0, lastDotIndex) + newExtension;
    }

    // ---------- Load design from test cases. ----------

    public static Design loadTestCase(Path path) throws IOException {
        return loadTestCase(path, false);
    }

    public static Design loadTestCase(Path path, boolean dump) throws IOException {
        Design design;
        try (var frontend = resolveFrontend(path)) {
            design = frontend.importDesign();
        }
        if (dump) {
            var dumpPath = replacePathExtension(path, ".qh");
            if (!Files.exists(dumpPath)) {
                try (var writer = NotepadWriter.pathOut(dumpPath)) {
                    new DesignPrinter(writer).exportDesign(design);
                }
            }
        }
        return design;
    }

    private static Frontend resolveFrontend(Path path) throws IOException {
        if (path.toString().endsWith(".v")) {
            return VerilogFrontend.fromPath(path);
        } else if (path.toString().endsWith(".sv")) {
            return SlangFrontend.fromOptions(path.toAbsolutePath().toString());
        } else if (path.toString().endsWith(".json")) {
            return SlangFrontend.fromASTDump(path);
        } else if (path.toString().endsWith(".qh")) {
            return DesignParser.fromPath(path);
        } else if (path.toString().endsWith(".bin")) {
            return DesignLoader.fromPath(path);
        } else {
            throw new IllegalArgumentException("Cannot resolve frontend of: " + path);
        }
    }

}