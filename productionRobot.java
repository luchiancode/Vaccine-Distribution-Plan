package jav;

import java.util.Arrays;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Semaphore;

public class productionRobot extends Thread
	{ 
	 
	public productionRobot(int grid[][], int M, int robotsNumber) {
		this.grid = grid;
		this.M = M;
		this.robotsNumber = robotsNumber;
		
		vaccineGrid = new int[M][M];
		for (int[] row: vaccineGrid)
            Arrays.fill(row, 0);
	}
		
	 int M; //grid size MxM
     volatile int[][] grid; //production Robots matrix with their current position 
     int robotsNumber; // robots number
     volatile int[][] vaccineGrid;
     int vaccineCount = 0;
     Vector<String> serialNumbers = new Vector<String>();
    
     
     //get current status of the robots placement
     public int[][] getGrid(){
    	 return this.grid;
     }
     
     public int[][] getVaccineGrid(){
    	 return this.vaccineGrid;
     }
     public int getVaccineCount() {
    	 return this.vaccineCount;
     }
     public void resetVaccineCount() {vaccineCount = 0;}
     public void resetVaccineGrid() {
    	 for (int[] row: vaccineGrid)
             Arrays.fill(row, 0);
     }
		 
		    // every robot tries to create the next vaccine dose
		    void nextIteration(int grid[][], int M, int robotsNumber) {
		    	 Semaphore sem = new Semaphore(1);
		        try 
	            { 
		        	sem.acquire(); 
		    	Random rand = new Random();
		    	 int ox[] = {1,0,-1,0};
			     int oy[] = {0,1,0,-1};
			     int tries[] = {0,0,0,0};
	        	 

	        	for (int i = 0; i < M && robotsNumber > 0; i++) {
	        		for (int j = 0; j < M; j++) 
	        		{
	        			
	        		
	        			if(grid[i][j] == 1) {
	        				
	        				boolean flag = true;
	        				
	        				if(checkIfSurrounded(i, j)) flag = false;
	        				
	        				
	        				int p = rand.nextInt(4);
	        				Arrays.fill(tries, 0);
	        				
	        				while(flag == true && p < 4){
	        					 tries[p] = 1;
	        					 int XX = i + ox[p];
		        				 int YY = j + oy[p];
		        				
		        				
		        				 if(XX >= 0 && XX < M && YY >= 0 && YY < M && grid[XX][YY] == 0) {
    	        					 grid[i][j] = 0;
    	    	    	        	 grid[XX][YY] = 1;
    	    	    	        	 
    	    	    	        	 serialNumbers.add(serialNumber(XX, YY));
    	    	    	        	 productionFacility.getSerialNumber(serialNumber(XX, YY));
    	    	    	        	 vaccineGrid[XX][YY] = 1;
    	    	    	        	 vaccineCount++;
    	    	    	        	 flag = false;
    	    	    	        	 robotsNumber--;
    	    	    	        	 Thread.sleep(30);
    	        				 }
		        				 
		        				 p = rand.nextInt(4);
		        				 while(tries[p] == 1) p = rand.nextInt(4);
		        				 
	        				 }
	        			}
	        			
	        			}
	        		
	        	}
	            }catch (InterruptedException exc) { 
                    System.out.println(exc); 
                } 
	        
		    }
		    
		    void print(int grid[][], int M) {
		    	 for (int i = 0; i < M; i++) 
		         { 
		             for (int j = 0; j < M; j++) 
		             { 
		                 if (grid[i][j] == 0) 
		                     System.out.print("[]"); 
		                 else
		                     System.out.print("><"); 
		             } 
		             System.out.println(); 
		         } 
	        	 

		    }
		    
		    boolean checkIfSurrounded(int i, int j) {
		    	try {
		    	 Random rand = new Random();
		    	 int ox[] = {1,0,-1,0};
			     int oy[] = {0,1,0,-1};
		    	for(int k = 0; k <= 3; k++) {
		    		 int XX = i + ox[k];
    				 int YY = j + oy[k];
    				
    				
    				 if(XX >= 0 && XX < M && YY >= 0 && YY < M && grid[XX][YY] == 0) {
    					return false;
    				 }
		    	}
		    	
		    	
		    	 int r = rand.nextInt(40) + 10;
				 Thread.sleep(r);
		    	} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
		    	return true;
		    }
		    
		    String serialNumber(int XX, int YY) {
		    	return  "XX" + "YY";
		    }
		    
		    Vector<String> getSerialNumbers(){
		    	return serialNumbers;
		    }
		    

		
		    
		    
		    
		    
	} 

