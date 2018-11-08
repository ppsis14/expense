package csku.expense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitUsersTest {
    private Users users;
    String id = "1";
    String pin = "6499";
    String name = "Thikamporn";
    double balance = 200;

    @BeforeEach
    public void setUp(){ users = new Users(id, pin, name, balance); }

    @Test
    public void getUserBalance() {
        assertEquals(200, users.getUserBalance());
    }

    @Test
    public void getTotalIncome(){ assertEquals(0, users.getTotalIncome());}

    @Test
    public void getTotalExpense() {assertEquals(0, users.getTotalExpense());}

    @Test
    public void  getId() {assertEquals("1", users.getId());}

    @Test
    public void getPin(){ assertEquals("6499", users.getPin()); }

    @Test
    public void testMatchPinValid() {
        assertTrue(users.matchPin(pin));
    }

    @Test
    public void testMatchPinInvalid() {
        assertFalse(users.matchPin(pin+100));
    }

    @Test
    public void testValidateUser(){ assertTrue(users.validateUser(id, pin)); }

    @Test
    public void testInValidateUser(){ assertFalse(users.validateUser(id, pin+100)); }

}