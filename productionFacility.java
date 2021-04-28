package jav;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.Semaphore;

public class productionFacility extends Thread{
	
	
	 
	public productionFacility(int grid[][], int M, int robotsNumber, Set<Pair<Integer, Integer>> robotsLocations) {
			this.grid = grid;
			this.M = M;
			this.robotsNumber = robotsNumber;
			this.robotsLocations = robotsLocations;
		}
	
	 static int readyDoses = 0;
	 int M; //grid size MxM
     volatile  int[][] grid; //production Robots matrix with their current position 
     volatile  int[][] vaccineGrid;
      int robotsNumber; // robots number
     Set<Pair<Integer, Integer>> robotsLocations;
     static int robotsCount = 0;
      Vector<productionRobot> productionRobots = new Vector<productionRobot>();
      Vector<packingRobot> packingRobots = new Vector<packingRobot>();
      static Set<String> serialNumbers = new HashSet<String>();
     
	 synchronized void runProductionRobots(productionFacility facility) 
    { 
		 
		 

	    setGrid1(facility.grid, facility.robotsLocations);
	    
        
        productionRobot Robot = new productionRobot(facility.grid, facility.M, facility.robotsNumber);
        Robot.start();
       
        System.out.println(); 
       
        System.out.println(robotsCount + 1); 
        
        System.out.println(); 

        int iterationNumber = 10;
        for (int i = 0; i < iterationNumber; i++) {
        	Robot.nextIteration(facility.grid, facility.M, facility.robotsNumber);
        	System.out.println("Robots grid\n");      
        	Robot.print(facility.grid,facility.M);
        	System.out.println();   
        	System.out.println("Vaccines grid\n");        
        	vaccineGrid = Robot.getVaccineGrid();
        	
        	Robot.print(vaccineGrid,M);
        	System.out.println();         

        	
        }
        facility.productionRobots.addElement(Robot);
        
        robotsCount++;
         
		
        
    } 
	 
	 synchronized void runPackingRobots(productionFacility facility) {
		Random rand = new Random();
		int robotsNumber = rand.nextInt(2) + 8;
		
		
		for (int i = 0; i < robotsNumber; i++) {
			packingRobot auxRobot = new packingRobot();
			facility.packingRobots.add(auxRobot);
		}
		
		for(packingRobot robot : packingRobots) {
			robot.start();
			for(productionRobot robot2 : productionRobots) {
				
				if(robot2.getVaccineCount() > 0) {
					robot.getDoses(robot2.getVaccineCount());
					robot2.resetVaccineCount();
					robot2.resetVaccineGrid();
					readyDoses += robot.getDosesFromRobot();
				}
					
			}
			
			
			
			pharmaceuticalCompanyHQ.getDosesFromRobots(readyDoses);
			
		}
		
		
		
		
	 }

	void setGrid(int grid[][], Set<Pair<Integer,Integer>> robotsLocations) 
    { 
        	
        	 for (Pair<Integer,Integer> points : robotsLocations) {
        		 grid[points.getKey()][points.getValue()] = 1;
        	 }
        	 
    }
	
	static void setGrid1(int grid[][], Set<Pair<Integer,Integer>> robotsLocations) 
    { 
        	
        	 for (Pair<Integer,Integer> points : robotsLocations) {
        		 grid[points.getKey()][points.getValue()] = 1;
        	 }
        	 
    }
	int getRobotsNumber() {
		return robotsNumber;
	}
	int getGridSize() {
		return M;
	}
	int[][] getGrid(){
		return grid;
	}
	 static void getSerialNumber(String serial) {
		 serialNumbers.add(serial);
	}
	
}
