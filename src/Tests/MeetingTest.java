package Tests;

import Model.Meeting;
import org.junit.Test;

import java.sql.Time;
import java.util.Calendar;

import static org.junit.Assert.*;

public class MeetingTest {
    Meeting meetingTester = new Meeting();
    Calendar testDate = Calendar.getInstance();

//  Setter dato med et Date-objekt og sjekker om den er riktig når den blir kalt tilbake.
    @Test
    public void testSetDato() throws Exception {
        testDate.set(testDate.YEAR, 2015);
        testDate.set(testDate.MONTH, testDate.FEBRUARY);
        testDate.set(testDate.DATE, 13);

        testDate.set( testDate.HOUR_OF_DAY, 0 );
        testDate.set( testDate.MINUTE, 0 );
        testDate.set( testDate.SECOND, 0 );
        testDate.set( testDate.MILLISECOND, 0 );

        java.sql.Date tester = new java.sql.Date(testDate.getTime().getTime());

        meetingTester.setDato(tester);

        assertEquals(tester, meetingTester.getDato());
    }

//  Setter starttid med et Time-objekt og ser om dette er likt når det blir kalt tilbake.

    @Test
    public void testSetStartTid() throws Exception {
        Time start = new Time(13,00,00);
        meetingTester.setStartTid(start);
        assertEquals(start, meetingTester.getStartTid());
    }

//  Setter sluttid med et Time-objekt og ser om dette er likt når det kommer tilbake.

    @Test
    public void testSetSluttTid() throws Exception {
        Time end = new Time(13, 30,00);
        meetingTester.setSluttTid(end);
        assertEquals(end, meetingTester.getSluttTid());
    }

//  Setter en beskrivelse som en String og sjekker om denne er lik når den kommer tilbake.

    @Test
    public void testSetBeskrivelse() throws Exception {
        String Beskrivelse = "Dette er en møtetest.";
        meetingTester.setBeskrivelse(Beskrivelse);
        assertEquals(Beskrivelse, meetingTester.getBeskrivelse());
    }

//  Setter et romnavn med en String og sjekker om den er lik når den kommer tilbake.

    @Test
    public void testSetRomnavn() throws Exception {
        String rom = "IT-vest 23";
        meetingTester.setRomnavn(rom);
        assertEquals(rom, meetingTester.getRomnavn());
    }

//  Setter en gruppeID med en Integer og sjekker om denne er lik når den kommer tilbake.

    @Test
    public void testSetGruppeID() throws Exception {
        Integer gruppeID = 14;
        meetingTester.setGruppeID(gruppeID);
        assertEquals(gruppeID, meetingTester.getGruppeID(),0);
    }
}