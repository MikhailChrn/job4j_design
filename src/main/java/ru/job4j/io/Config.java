package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    private boolean validate(String line) {
        if (line.startsWith("=")) {
            throw new IllegalArgumentException(
                    "line does not contain a key");
        }

        if (line.indexOf("=") == line.length() - 1
                && line.length() > 0) {
            throw new IllegalArgumentException(
                    "line does not contain a value");
        }

        return !line.startsWith("#") && !"".equals(line);
    }

    public void load() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(this::validate)
                    .forEach(line -> {
                        String[] str = line.split("=", 2);
                        values.put(str[0], str[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) throws Exception {
        Config conf = new Config("data/app.properties");
        conf.load();
        for (Map.Entry<String, String> entry : conf.values.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
