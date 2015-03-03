package Tests;

import Model.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    User userTester = new User();

//  Setter en brukernavn og ser om det er likt når det kommer tilbake.
    @Test
    public void testSetUsername() throws Exception {
        String user = "Henrik";
        userTester.setUsername(user);
        assertEquals(user, userTester.getUsername());
    }

//  Setter et passord og ser om det er likt når det kommer tilbake.

    @Test
    public void testSetPassword() throws Exception {
        String pw = "test123";
        userTester.setPassword(pw);
        assertEquals(pw, userTester.getPassword());
    }

//  Setter et fornavn og ser om det er likt når det kommer tilbake.

    @Test
    public void testSetFirstname() throws Exception {
        String fName = "henrik";
        userTester.setFirstname(fName);
        assertEquals(fName,userTester.getFirstname());
    }

//   Setter et etternavn og ser om det er likt når det kommer tilbake.

    @Test
    public void testSetLastname() throws Exception {
        String lName = "mørk";
        userTester.setLastname(lName);
        assertEquals(lName,userTester.getLastname());
    }

//  Setter en mailAdresse og ser om den er lik når den kommer tilbake.

    @Test
    public void testSetEmail() throws Exception {
        String mail = "kongen@kongen.no";
        userTester.setEmail(mail);
        assertEquals(mail, userTester.getEmail());
    }

//  Setter et tlfnummer og ser om det er likt når det kommer tilbake.

    @Test
    public void testSetPhone() throws Exception {
        String tlph = "123456789";
        userTester.setPhone(tlph);
        assertEquals(tlph, userTester.getPhone());
    }
}