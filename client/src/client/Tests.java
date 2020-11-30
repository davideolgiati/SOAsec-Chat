package client;

import static org.junit.Assert.*; 
import org.junit.Test;


public class Tests {
    // VARIABILI GLOBALI
    // username per axis2
    protected static String username = "client1";
    // password axis2
    protected static String password = "password1";

    
    @Test
    public void ChatUsers() {
        try {
            ChatAPI tester1 = new ChatAPI("test 1");
            assertEquals("test 1", tester1.getUser());
            assertEquals("Ancora nessun utente connesso ...", tester1.listaUtenti());

            ChatAPI tester2 = new ChatAPI("test 2");
            assertEquals("test 2", tester2.getUser());
            assertEquals("test 1, test 2", tester2.listaUtenti());
            assertEquals("test 1, test 2", tester1.listaUtenti());

            tester1.request("test 2");
            assertArrayEquals(new String[] {"test 1"}, tester2.checkForRequests());
            //tester2.accept("test 1");
            assertTrue(tester2.connectTo("test 1"));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Errore");
        }
    }
}

