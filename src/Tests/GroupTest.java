package Tests;

import Model.Group;
import Model.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class GroupTest {
    Group groupTester = new Group();

//  Setter brukere i gruppen og ser om  disse er like når de kommer tilbake.

    @Test
    public void testSetUsers() throws Exception {
        ArrayList<User> members = new ArrayList<User>(Arrays.asList(new User(), new User()));
        groupTester.setUsers(members);
        assertEquals(members, groupTester.getUsers());
    }

//  Setter en bruker inn i gruppa og ser om den får tilbake den riktige.

    @Test
    public void testGetUser() throws Exception {
        User testUser = new User();
        groupTester.addUser(testUser);
        assertEquals(testUser, groupTester.getUser(testUser));
    }

//    Setter en gruppeID og ser om denne er riktig når den kommer tilbake.

    @Test
    public void testSetGroupID() throws Exception {
        Integer ID = 15;
        groupTester.setGroupID(ID);
        assertEquals(ID, groupTester.getGroupID(),0);
    }
}