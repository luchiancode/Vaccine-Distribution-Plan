package jav;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class pharmaceuticalCompanyHQ {
	
	static volatile int vaccineDoses = 0;
	
	public static void main(String[] args) throws InterruptedException 
    { 
	 Random rand = new Random();
	  //get the random number of facilities
	 int productionFacilitiesNo = rand.nextInt(4) + 2; 
	 // a vector of our facilities
	 Vector<productionFacility> facilities = new Vector<productionFacility>(productionFacilitiesNo);
	 
	 //M = rand.nextInt(400) + 100; 
	 int M = rand.nextInt(4) + 10;
	 
	 for(int i = 0; i < productionFacilitiesNo; i++) {
		 
		 
		 
		 int[][] grid = new int [M][M];
	       
		 //fill the grid with 0s
	        for (int[] row: grid)
	            Arrays.fill(row, 0);
	        
	        int robotsNumber = rand.nextInt(M/2); 
	        while(robotsNumber == 0) robotsNumber = rand.nextInt(M/2); 
	       // System.out.println(robotsNumber); 
	        
	        Set<Pair<Integer,Integer>> robotsLocations = new HashSet<Pair<Integer,Integer>>(robotsNumber);
	        
	        for(int j = 0; j < robotsNumber; j++) {
	        	int x = rand.nextInt(M); 
	        	int y = rand.nextInt(M);
	        	Pair<Integer, Integer> aux = new Pair<Integer, Integer>(x,y);
	        	robotsLocations.add(aux);
	        }
	        
	       
		 
		 productionFacility auxiliaryFacility = new productionFacility(grid, M, robotsNumber, robotsLocations);
		 auxiliaryFacility.setGrid(grid, robotsLocations);
		 facilities.add(auxiliaryFacility);
		 auxiliaryFacility.runProductionRobots(auxiliaryFacility);
		 auxiliaryFacility.runPackingRobots(auxiliaryFacility);
		 
		 System.out.println("Vaccine count: " + vaccineDoses); 
		 
		 
		 for(productionFacility facility : facilities) {
			 spawnRobots(facility);
		 }
		 
		
	 }
	 
	
	 
	 
    }
	
		
	static void getDosesFromRobots(int doses) {vaccineDoses +=doses;}
	 
	 static void spawnRobots(productionFacility facility) {
		 try {
			 Random rand = new Random();
			int robotsNumber = facility.getRobotsNumber();
			if (robotsNumber < facility.getGridSize()/2) {
				
				int[][] grid = facility.getGrid();
				int M = facility.getGridSize();
				 
		        Set<Pair<Integer,Integer>> robotsLocations = new HashSet<Pair<Integer,Integer>>(robotsNumber);
		        
		        for(int j = 0; j < robotsNumber; j++) {
		        	 Random rand1 = new Random();
					int x = rand1.nextInt(M); 
		        	int y = rand1.nextInt(M);
		        	Pair<Integer, Integer> aux = new Pair<Integer, Integer>(x,y);
		        	robotsLocations.add(aux);
		        }
		        
			 facility.setGrid(grid, robotsLocations);
			 
			 int r = rand.nextInt(500) + 500;
			 Thread.sleep(r);
			}
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

			
	}

   
	
}
