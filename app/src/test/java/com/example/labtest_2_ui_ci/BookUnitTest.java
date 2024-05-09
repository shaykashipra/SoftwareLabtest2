package com.example.labtest_2_ui_ci;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class BookUnitTest {

    @Test
    public void testBorrowBook() {
        Book book = new Book("The Hobbit", "J.R.R. Tolkien", "fantasy", 10);

        // Borrow 5 units
        book.borrowBook(5);
        assertEquals(5, book.getUnits());

        // Borrow more units than available
        book.borrowBook(7);
        assertEquals(0, book.getUnits());

        // Borrow with negative units (should not change units)
        book.borrowBook(-3);
        assertEquals(0, book.getUnits());
    }
}
