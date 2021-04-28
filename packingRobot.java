package jav;

import java.util.Arrays;
import java.util.Random;


public class packingRobot extends Thread{
	 static int packingRobotsNo = 0;
	 int dosesOwnedByRobot = 0;
	 int dosesInCurrentFacility;
	 
	 public packingRobot() {
		 packingRobotsNo++;
	 }
	 
	 synchronized void getNumberOfDosesFromFacility(int doses) {
		 dosesInCurrentFacility = doses;
	 }
	 
	 synchronized int getDosesFromRobot() {
		 int d = dosesOwnedByRobot;
		 dosesOwnedByRobot = 0;
		 return d;
	 }
	 
	 synchronized void getDoses(int doses) {
		 dosesOwnedByRobot = doses;
	 }
	 

}
