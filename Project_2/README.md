Steps:
1. Compile everything.
2. $ java SimulationGUI

Description: This project simulates a population of Tasmanian Devils and their behavior.  With various population sizes, grid sizes,
and whether or not there is a road in the grid (all specified by the user once SimulationGUI has been run), we can see how quickly
it will take the entire population of Tasmanian Devils to become infected with Road Runnder Disease (RRD; transmitted by touch).
Each Devil is created in a random location on the hex grid, with a random hex set as its destination.  When two Devils cross paths,
there is an 80% chance the healthy one will get infected (if more than one land on the same spot, there is an 80% chance for each of
them).  Carrion will spawn every 100 steps on a non-road hex, and every 10 steps on a road hex, because of traffic.  These carrion
give off scent that attract the Devils to the carrion, increasing the likelihood that they will cross paths with another Devil and
become infected.  It is interesting to see how quickly the entire population of Devils becomes infected with a road compared to 
without one.
