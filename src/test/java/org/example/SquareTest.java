package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    @Test
    void equalsTest() {

        Square sq1 = new Square(2,5);
        Square sq2 = new Square(2,5);
        assertTrue(sq1.equals(sq2));
        assertFalse(sq1.equals(new Square(2, 4)));
    }
}