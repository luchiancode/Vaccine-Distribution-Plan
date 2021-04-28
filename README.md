# Vaccine-Distribution-Plan
Vaccine Distribution Plan  

I.                 Context  
In the actual context of the Covid-19 pandemics, the good news just came. Some pharmaceutical companies just announced that they found an efficient vaccine to fight against the SARS-Cov-2 virus. But they know they have to deliver the vaccine to all countries around the world and they need our help to serve all the requests.
The pharmaceutical company owns multiple production facilities. Each facility has production robots creating vaccine doses and packing robots that pack the doses. They need our help to create a proper plan by using Java Concurrency to distribute all the ordered doses in time to end the Covid-19 crisis.  

II.               Pharmaceutical Company Instructions:  
1.There are multiple production facilities:    
A random number of factories (between 2 and 5) that create vaccine doses (it doesn’t know how many are needed until the production plan is created and runs)  
The production facilities are designed as matrices with N x N where 100 ≤ N ≤ 500.  
Each production facility has a list of production robots  
Every few seconds the production facility will request the position of the production robot in the factory  
There can be no more than N / 2 production robots in each production facility.  
2.There are the production robots that create the vaccine doses  
The production robots are spawning randomly in each production facility at a random place in the factory at a random time σ where 500 ≤ σ ≤ 1000 (milliseconds)  
Two production robots cannot be in the same place  
 The production robots create doses by:  
Moving in one direction (left, right, up, down)  
When they reach a wall of the production facility, they move in any other direction than the wall’s  
With each move, they create a vaccine dose  
If a production robot is surrounded by other production robots and cannot move it stops for a random time σ where 10 ≤ σ ≤ 50 (milliseconds)  
Each time a dose is created, the production robot informs the production facility to know the created dose serial number.  
After each dose created, the production robot needs to recharge for a short while (X * 30 milliseconds)  
The production robots will work until all the orders are done.  
3.There are also packing robots:  
The packing robots await to receive doses from the production facilities. They are available in the pharmaceutical company’s headquarter.  
They can always read information from the production facility regarding the number of doses, but they won’t be allowed access when the production robots notify the production facilities about newly created doses or when the production facility itself asks the production robots about new vaccine doses.  
A maximum of 10 packing robots can read from the production facility at a time  
A random time must pass between two consecutive production facility readings  
All packing robots will deliver the vaccine doses to the company headquarter  
The packing robot number is known from the beginning (there are more than 8 because the company needs backup).  
4.      The Pharmaceutical Company headquarter will do the following:  
The headquarter contains all the production facilities  
It creates production facilities  
It creates production robots and assigns them to random production facility (remember that each production robot needs to register by itself to the production facility)  
