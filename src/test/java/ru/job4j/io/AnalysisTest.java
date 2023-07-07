package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.*;

class AnalysisTest {
    @Test
    void unavailableTest(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("400 11:01:01");
            out.println("300 11:02:02");
            out.println("200 11:03:02");
            out.println("200 11:04:01");
            out.println("200 11:05:01");
            out.println("500 11:06:01");
            out.println("300 11:07:01");
        }

        File target = tempDir.resolve("target.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader read = new BufferedReader(new FileReader(target))) {
            read.lines()
                    .forEach(line -> {
                        rsl.append(format("%s\n", line));
                    });
        }
        assertThat("10:57:01;11:02:02;\n"
                + "11:06:01;11:07:01;\n")
                .hasToString(rsl.toString());
    }
}
