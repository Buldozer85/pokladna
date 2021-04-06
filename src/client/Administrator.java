package client;

import java.time.Instant;

import gui.AdminUvodniFrame;

public class Administrator {
  public static void main(String[] args) throws Exception {

    AdminUvodniFrame admin = new AdminUvodniFrame();
    admin.setVisible(true);
    Instant i =  Instant.now();
    System.out.println(i);

  }
}
