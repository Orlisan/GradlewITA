import JavaITA.JavaITATranspiler;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

class Main {
    public static void main(String... args) throws Exception {
        new GradlewITA(args);
    }
}

public class GradlewITA {
    public static final String pathExtensioni = GradlewITA.class.getProtectionDomain().getCodeSource().getLocation().getPath().concat("/../extensions");
    public ArrayList<JavaITATranspiler> pathsJavaIta = new ArrayList<>();
    public String gradlewName = System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("win") ? "gradlew.bat" : "gradlew";

    public GradlewITA(String... args) throws Exception {
        File dirProgetto = new File(System.getProperty("user.dir"));
        String gradlewPath = dirProgetto.toString().concat("/").concat(gradlewName);
        if (!Files.exists(Path.of(dirProgetto.toString() + "/src"))) {
            throw new Exception("La directory corrente non è un progetto java");
        }
        try (var stream = Files.walk(dirProgetto.toPath())) {
            stream.filter(p -> p.toString().toLowerCase().endsWith(".javaita"))
                    .forEach(p -> {
                        try {
                            pathsJavaIta.add(new JavaITATranspiler(p.toString(), "-t", "-s", "-o", p.getParent().toString(), "-e", pathExtensioni));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
        ArrayList<String> cmd = new ArrayList<>();
        cmd.add(gradlewPath);
        cmd.addAll(Arrays.asList(args));
        Process builder = new ProcessBuilder(cmd).inheritIO().start();
        builder.waitFor();
        for (JavaITATranspiler javaITATranspiler : pathsJavaIta) {
            javaITATranspiler.cancellaFile();
        }


    }
}