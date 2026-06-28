# GradlewITA

A Gradle wrapper that automatically transpiles `.javaita` files before building, allowing you to write Minecraft mods (or any Java project) in your own language. (default italian)

## How it works

GradlewITA scans your `src/` directory for `.javaita` files, transpiles them to `.java` using [JavaITA](https://github.com/Orlisan/JavaITA), runs Gradle with your arguments, then cleans up the generated `.java` files.

## Requirements

- Java 21+
- A Gradle project with a `src/` directory
- A `.jitaext` extension file with your keyword translations

## Installation

1. Download `GradlewITA.jar` and place it in a folder of your choice
2. Add that folder to your PATH
3. Place your `.jitaext` extension files in an `extensions/` folder next to the JAR

**Windows:** run `gradlewita.bat` once to add to PATH automatically

**Linux/Mac:** run `chmod +x gradlewita.sh`, then add the folder to PATH manually or via `.bashrc`

## Usage

Navigate to your Gradle project and run:

    gradlewita <gradle tasks>

For example:

    gradlewita build
    gradlewita runClient

## Writing your own language

Create a `.jitaext` file with your translations in this format:

    yourKeyword-->javaKeyword

Ask an AI to generate translations — just say: *"Translate these Java keywords to [your language] in the format translation-->english, one per line"*

Then drop your `.jitaext` in the `extensions/` folder next to the JAR.

## Example

A small Italian extension for Minecraft Fabric modding is available here

## License

MIT
