# Qihe Application

This application is built on the [Qihe platform][platform].
It includes a new `HelloAnalysis` as a demonstration and provides the same command-line interface (CLI)
as the [platform-cli][platform-cli] module in the Qihe project to execute your newly added analyses.

This guide will not go into the specifics of developing new analyses within this standalone project,
as the process closely mirrors developing analyses within the Qihe platform itself.
Therefore, before starting, please review the [README][platform] for the Qihe Platform,
which contains tutorials on how to use the platform.

We also won't repeat details on using the Qihe CLI here.
For more information, refer to the [README][platform-cli] for the `platform-cli` module in the Qihe project.

## Prerequisites

- Java 21
- Qihe Platform

Currently, Qihe platform has not been published to the maven central repository.
Therefore, to utilize the Qihe platform, you have to install Qihe to the maven local
repository which is set to be searched by the `build.gradle.kts`.
Specifically, execute the following commands:
```shell
git clone https://github.com/pascal-lab/qihe.git
cd qihe
./gradlew build # Run some tests before installation.
./gradlew publishToMavenLocal
```

## Installation

You can install the application by the following command:
```shell
# Install `qihe` to the build directory `./build/install/qihe`.
# You can copy it to another directory if desired; in this example, we will leave it in place.
./gradlew installDist

# Add the `qihe` command to your PATH variable:
# For Bash:
export PATH=$PATH:$(pwd)/build/install/qihe/bin
# For PowerShell:
$env:Path += ";$(Resolve-Path ./build/install/qihe/bin)"

# Now the `qihe` command is available
qihe -h 
```

If you want to run `qihe` without installation, you can use the Gradle task:

```shell
./gradlew run --args="<argument>..."
```

This command is equivalent to:

```shell
qihe <argument>...
```

[qihe]: https://github.com/pascal-lab/qihe/
[platform]: https://github.com/pascal-lab/qihe/tree/master/platform
[platform-cli]: https://github.com/pascal-lab/qihe/tree/master/platform-cli