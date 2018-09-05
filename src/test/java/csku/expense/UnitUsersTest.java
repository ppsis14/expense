package csku.expense;

import csku.expense.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitUsersTest {
    private Users users;
    int id = 1;
    int pin = 6499;
    double balance = 200;

    @BeforeEach
    void setUp(){ users = new Users(id, pin, balance); }

    @Test
    void getUserBalance() {
        assertEquals(200, users.getUserBalance());
    }

    @Test
    void  getId() {assertEquals(1, users.getId());}
    @Test
    void getPin(){ assertEquals(6499, users.getPin()); }

    @Test
    void testMatchPinValid() {
        assertTrue(users.matchPin(pin));
    }

    @Test
    void testMatchPinInvalid() {
        assertFalse(users.matchPin(pin+100));
    }

    @Test
    void testValidateUser(){ assertTrue(users.validateUser(id, pin)); }

    @Test
    void testInValidateUser(){ assertFalse(users.validateUser(id, pin+100)); }

}