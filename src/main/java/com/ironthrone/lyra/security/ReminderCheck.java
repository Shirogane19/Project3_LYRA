package com.ironthrone.lyra.security;

import java.util.Timer;
import java.util.TimerTask;

public class ReminderCheck {

	  Timer timer;

	  public ReminderCheck(int seconds) {
	    timer = new Timer();
	    timer.schedule(new RemindTask(), seconds * 1000);
	  }

	  class RemindTask extends TimerTask {
	    public void run() {
	      System.out.println("Time's up!");
	    }
	  }

	  public static void main(String args[]) {
	    System.out.println("About to schedule task.");
	    new ReminderCheck(5);
	    System.out.println("Task scheduled.");
	  }
}


