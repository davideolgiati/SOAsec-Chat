package client;

import java.io.*;
import java.util.*;

public class Keyboard {
  private Thread threadLettura; // Thread di lettura
  private Boolean killSwitch = false; // Segnale di chiusura
  private String msg = ""; // Messaggio letto dallo scanner
  private Cond cond = null; // Lambda di fine esecuzione
  private final Runnable main =
      () -> {
	InputStreamReader is = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(is);
	try {
	  while (!br.ready() && !killSwitch) {
	    Thread.sleep(200);
	  }
	  msg = killSwitch ? "" : br.readLine();
	} catch (Exception e) {
	  msg = "";
	}
	killSwitch = true;
      };

  public interface Cond {
    Boolean run();
  }

  Keyboard() {
    cond = () -> false;
  }

  Keyboard(Cond inputCond) {
    cond = inputCond;
  }

  private String logica(Runnable logic) throws Exception {
    threadLettura = new Thread(logic);
    threadLettura.start();

    while (!killSwitch) {
      if (cond.run() && threadLettura != null) {
	killSwitch = true;
      }
    }
    return msg;
  }

    private String logica(Runnable logic, Cond cond) throws Exception {
    threadLettura = new Thread(logic);
    threadLettura.start();

    while (!killSwitch) {
      if (cond.run() && threadLettura != null) {
	killSwitch = true;
      }
    }
    return msg;
  }

  public String next() {
    try {
      return logica(main);
    } catch (Exception e) {
      return "";
    }
  }

  public String next(Cond cond) throws Exception {
    try {
	return logica(main, cond);
    } catch (Exception e) {
      return "";
    }
  }
}

