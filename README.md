# Qihe Application Template

A starter template for building Verilog static analysis tools using the [Qihe framework][qihe].

This project provides:

- A `HelloAnalysis` example demonstrating how to develop a new analysis (as explained in
  the [tutorial][new-analysis-tutorial])
- The same command-line interface (CLI) as Qihe's [`platform-cli`][platform-cli] module for executing analyses

**Key Notes:**

1. This template doesn't cover analysis development specifics - refer to the [Qihe tutorials][platform] for
   detailed guidance.
2. CLI usage documentation is available in the `platform-cli` [README][platform-cli].

## Prerequisites

- Java 21 or later
- Qihe (installed locally)

### Installing Qihe Locally

Since Qihe isn't currently available in Maven Central, you'll need to install it to your local Maven repository:

```shell
# Clone the Qihe repository
git clone https://github.com/pascal-lab/qihe.git
cd qihe
# Build and test Qihe
./gradlew build 
# Install to local Maven repository
./gradlew publishToMavenLocal 
```

> Note: The project's `build.gradle.kts` is pre-configured to check your local Maven repository for Qihe dependencies.

## Getting Started

Before developing with this template, verify it works correctly by following either method below.
 
### Option 1: Install as Command Line Tool

```shell
# Build the project with tests
./gradlew build

# Install the project to the build directory `./build/install/qihe`.
# You can copy it to another directory if desired; in this example, we will leave it in place.
./gradlew installDist

# Add the installed binaries to your PATH variable:
# For Bash:
export PATH=$PATH:$(pwd)/build/install/qihe/bin
# For PowerShell:
$env:Path += ";$(Resolve-Path ./build/install/qihe/bin)"

# Now a `qihe` command is available
qihe -h 
```

### Option 2: Run Directly via Gradle

If you want to run `qihe` without installation, you can use the Gradle task:

```shell
./gradlew run --args="<argument>..."
```

This behaves identically to:

```shell
qihe <argument>...
```

[qihe]: https://github.com/pascal-lab/qihe/

[platform]: https://github.com/pascal-lab/qihe/tree/master/platform

[platform-cli]: https://github.com/pascal-lab/qihe/tree/master/platform-cli

[new-analysis-tutorial]: https://github.com/pascal-lab/qihe/blob/doc/platform/src/main/docs/new-analysis.md