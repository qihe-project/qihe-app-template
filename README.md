# Qihe Application

This is an application based on the [Qihe](https://github.com/pascal-lab/qihe) platform.

## Prerequisite

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

## Development

### Run

This application has the `com.example.Main` class as its entry point.
There are many ways to invoke it, depending on the IDE or editor you use,
but basically they are executing `./gradlw run --args='<arguments>'`.

See documents (TODO) to learn Qihe CLI.

### Test

TODO

## Install

You can install the application by the following command:
```shell
# Install qihe application to the build directory ./build/install/qihe,
# which can then be copied to other path.
./gradlew installDist
# We leave the install in place and add the `qihe` command to the PATH variable.
export PATH=$PATH:./build/install/qihe/bin/ # for bash
$env:Path += ';./build/install/qihe/bin/' # for powershell
# Now the `qihe` command is available
qihe -h 
```

## Usage Example

Suppose you have installed this application and have a Verilog file `a.v`
in the current working directory:
```verilog
module top();
    initial begin
        $display("Hello, world!");
    end
endmodule
```

Before following-up operations, compile it to the Qihe IR first:
```shell
qihe compile a.v -o a.qh
```

You can apply the demo analysis `HelloAnalysis` to it by
```shell
qihe run hello -i a.qh
```
It prints `Hello, top modules: top`.

You can also simulate it by
```shell
qihe sim -i a.qh -t 1
```
It prints `Hello, world!`.