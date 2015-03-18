package Tests;

import Model.Invite;
import Model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Model.PersonalAppointment;
import Server.DatabaseServer;
import Model.Meeting;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DatabaseServerTest {
    DatabaseServer server;
    DatabaseServer server2;
    User user1;
    User user2;
    PersonalAppointment ap;
    PersonalAppointment ap1;
    PersonalAppointment shareAp;
    PersonalAppointment returnAp;

    @Before
    public void setUp() throws Exception {
        server = new DatabaseServer();
        server2 = new DatabaseServer();
        user1 = new User();
        user2 = new User();
        user1.setUsername("Test1");
        user1.setPassword("testtest");
        user1.setEmail("test@test.no");
        user1.setPhone("12345678");
        user1.setFirstname("Henrik");
        user1.setLastname("Martinius");
        user2.setUsername("Test2");
        user2.setPassword("testtest");
        user2.setEmail("test2@test.no");
        user2.setPhone("87654321");
        user2.setFirstname("Masoom");
        user2.setLastname("Maham");
        server.addUser(user1);
        server2.addUser(user2);
        server.login(user1.getUsername(), user1.getPassword());
        server2.login(user2.getUsername(), user2.getPassword());

    }

    @Test
    public void testHendelsetidspunktTrue() throws Exception {
        ap = new PersonalAppointment();
        Date date = new Date(115,2,20);
        ap.setDato(date);
        Time startTime = new Time(12, 00,00);
        ap.setStartTid(startTime);
        Time endTime = new Time(12,30,00);
        ap.setSluttTid(endTime);
        ap.setRomnavn("Realfagkantina");
        ap.setBeskrivelse("This is a test.");
        server.addAppointment(ap, 2);
        PersonalAppointment returnAp = server.getLastAppointment();
        assertEquals(date, returnAp.getDato());

    }

    @Test
    public void testHendelsetidspunktFalse() throws Exception {
        ap1 = new PersonalAppointment();
        Date date = new Date(115, 2, 21);
        ap1.setDato(date);
        Time startTime = new Time(12, 00, 00);
        ap1.setStartTid(startTime);
        Time endTime = new Time(12, 30, 00);
        ap1.setSluttTid(endTime);
        ap1.setRomnavn("Realfagkantina");
        ap1.setBeskrivelse("This is a second test.");
        server.addAppointment(ap1, 2);
        PersonalAppointment returnAp = server.getLastAppointment();
        Date wrongDate = new Date(115, 4, 20);
        assertNotSame(wrongDate, returnAp.getDato());
    }
    @Test
    public void shareAppointment(){
        shareAp = new PersonalAppointment();
        Date date = new Date(115, 3,25);
        shareAp.setDato(date);
        Time startTime = new Time(12,00,00);
        Time endTime = new Time(13,00,00);
        shareAp.setStartTid(startTime);
        shareAp.setSluttTid(endTime);
        shareAp.setRomnavn("Realfagkantina");
        shareAp.setBeskrivelse("This is a third test.");
        try{
            shareAp.setGruppeID(server.getGroupId("TestGroup"));
        }catch (Exception e){
            System.out.println(e);
        }
        try{
            server.addAppointment(shareAp, server.getGroupId("TestGroup"));
        }catch (Exception e){
            System.out.println(e);
        }
        try{
            shareAp = server.getSpecificAppointment(server.getLastAppointment().getAvtaleID());}
        catch (Exception e){
            System.out.println(e);
        }
        Invite inv = new Invite(server2);
        try{
            inv = server2.getInvite(shareAp);
        }catch (Exception e){
            System.out.println(e);
        }

        try{
            returnAp = server2.getSpecificAppointment(inv.getAvtaleID());

        }catch (Exception e){
            System.out.println(e);
        }
        assertEquals(shareAp.getAvtaleID(),returnAp.getAvtaleID());
        assertEquals(shareAp.getDato(), returnAp.getDato());
        assertEquals(shareAp.getStartTid(), returnAp.getStartTid());
        assertEquals(shareAp.getBeskrivelse(), returnAp.getBeskrivelse());


    }
    @After
    public void tearDown() throws Exception{

    }
}