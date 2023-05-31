package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(7, 0);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void numberVerticesOfCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        int numberOfVertices = box.getNumberOfVertices();
        assertThat(name).isEqualTo("Cube");
        assertThat(numberOfVertices).isEqualTo(8);
    }

    @Test
    void numberVerticesOfSphere() {
        Box box = new Box(0, 1000);
        String name = box.whatsThis();
        int numberOfVertices = box.getNumberOfVertices();
        assertThat(name).isEqualTo("Sphere");
        assertThat(numberOfVertices).isEqualTo(0);
    }

    @Test
    void boxIsExist() {
        Box box = new Box(0, 1000);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void boxIsnotExist() {
        Box box = new Box(7, 1000000);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void areaOfCube() {
        Box box = new Box(8, 1);
        double result = box.getArea();
        assertEquals(result, 6.0d, 0.1d);
    }

    @Test
    void areaOfTetrahedron() {
        Box box = new Box(4, 2);
        double result = box.getArea();
        assertEquals(result, 6.9d, 0.1d);
    }
}
