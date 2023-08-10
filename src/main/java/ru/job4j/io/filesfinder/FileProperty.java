package ru.job4j.io.filesfinder;

import java.util.Objects;

public class FileProperty {

    private long size;

    private String name;

    private String absPath;

    public FileProperty(long size, String name, String absPath) {
        this.size = size;
        this.name = name;
        this.absPath = absPath;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbsPath() {
        return absPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileProperty that = (FileProperty) o;
        return size == that.size && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, name);
    }

    @Override
    public String toString() {
        return String.valueOf(absPath + " - size " + size + " Bytes ");
    }
}
