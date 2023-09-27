package Tests;

import Entity.User;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;


public class UserTest {

    @Test
    public void testUserCreationAndGetters()
    {
        User user = new User("John", "Doe", "john.doe@gmail.com","heslo");

        assertEquals("John",user.getMeno());
        assertEquals("Doe",user.getPriezvisko());
        assertEquals("john.doe@gmail.com",user.getEmail());
        assertEquals("heslo",user.getHeslo());

        assertNotEquals("Jon",user.getMeno());

        assertTrue("John".equals(user.getMeno()));
        //assertFalse

        assertNull(user.getUserId());
        assertNull(user.getSkore());
        assertNull(user.getJeAdmin());

        user.setSkore(50);

        assertSame(50, user.getSkore());

        //assertSame
        //assertArrayEqual

        //assertThrow

    }

}
