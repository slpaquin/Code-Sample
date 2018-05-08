// When Drone location is given please take into account that (4,5) for a location is actually
// row 5 and column 6 on the grid due to the nature of multidimmensional arrays.
// Drone target locations ar erandomly generated, with the max range being et to the grid size.

#include <iostream>
#include <pthread.h>
#include <thread>
#include <cstdlib>
#include "drone.cpp"
using namespace std;

// Size of map and number for range to generate random variables for target locations
const int MAX_X = 10;
const int MAX_Y = 10;

// Global map that can be accessed and changed from everywhere
char grid[MAX_X][MAX_Y];

// Function Prototypes
void addTarget(Drone drone);
void startGrid();
void printGrid();
void addDrone(Drone drone);
void updateGrid(Drone drone);
void droneRun(Drone drone);

// Main Drone Simulation
int main()
{
    // Makes blank grid
    startGrid();
    
    // Initialize Drone A
    Drone A('A', (rand() % MAX_X), (rand() % MAX_Y));
    addTarget(A);
    addDrone(A);
    cout << "- Drone " << A.getID() << " Running -" << endl;
    droneRun(A);
    
    // Initialize Drone B
    Drone B('B', (rand() % MAX_X), (rand() % MAX_Y));
    addTarget(B);
    addDrone(B);
    cout << "- Drone " << B.getID() << " Running -" << endl;
    droneRun(B);
    
    // Initialize Drone C
    Drone C('C', (rand() % MAX_X), (rand() % MAX_Y));
    addTarget(C);
    addDrone(C);
    cout << "- Drone " << C.getID() << " Running -" << endl;
    droneRun(C);
    
    // Initialize Drone D
    Drone D('D', (rand() % MAX_X), (rand() % MAX_Y));
    addTarget(D);
    addDrone(D);
    cout << "- Drone " << D.getID() << " Running -" << endl;
    droneRun(D);
    
    // Initialize Drone E
    Drone E('E', (rand() % MAX_X), (rand() % MAX_Y));
    addTarget(E);
    addDrone(E);
    cout << "- Drone " << E.getID() << " Running -" << endl;
    droneRun(E);
    
    // Initialize Drone F
    Drone F('F', (rand() % MAX_X), (rand() % MAX_Y));
    addTarget(F);
    addDrone(F);
    cout << "- Drone " << F.getID() << " Running -" << endl;
    droneRun(F);
    
    // Initialize Drone G
    Drone G('G', (rand() % MAX_X), (rand() % MAX_Y));
    addTarget(G);
    addDrone(G);
    cout << "- Drone " << G.getID() << " Running -" << endl;
    droneRun(G);
    
    // Initialize Drone H
    Drone H('H', (rand() % MAX_X), (rand() % MAX_Y));
    addTarget(H);
    addDrone(H);
    cout << "- Drone " << H.getID() << " Running -" << endl;
    droneRun(H);
    
    // Initialize Drone I
    Drone I('I', (rand() % MAX_X), (rand() % MAX_Y));
    addTarget(I);
    addDrone(I);
    cout << "- Drone " << I.getID() << " Running -" << endl;
    droneRun(I);
    
    // Initialize Drone J
    Drone J('J', (rand() % MAX_X), (rand() % MAX_Y));
    addTarget(J);
    addDrone(J);
    cout << "- Drone " << J.getID() << " Running -" << endl;
    droneRun(J);
    
    // Prints completed grid at end showing no more drones or destinations
    printGrid();
    cout << "- All Drones have completed their deliveries -" << endl;
    cout << "-    Thank you for choosing this Airport!    -" << endl << endl;
    
    return 0;
}

// Sets the initial values of each grid spot to *, only called once at start of program
void startGrid()
{
    for (int i = 0; i < MAX_X; i++)
        for (int j = 0; j < MAX_Y; j++)
            grid[i][j] = '*';
}

// Prints the grid everytime something is changed
void printGrid()
{
    for (int i = 0; i < MAX_X; i++)
    {
        for (int j = 0; j < MAX_Y; j++)
            cout << grid[i][j] << ' ';
        cout << endl;
    }
    cout << endl;
}

// Adds target (Drone ID) to map
void addTarget(Drone drone)
{
    int x_target = drone.getXDestination();
    int y_target = drone.getYDestination();
    
    // If target location is a * change to drone ID (uppercase)
    if(grid[x_target][y_target] == '*')
        grid[x_target][y_target] = drone.getID();
    
}

// Adds Drone (Drone ID but lowercase version) to map
void addDrone(Drone drone)
{
    int x_location = drone.getXLocation();
    int y_location = drone.getYLocation();
    
    if (grid[x_location][y_location] == '*')
        grid[x_location][y_location] = tolower(drone.getID());
}

// Checks for updates in location and prints updated map
void updateGrid (Drone drone)
{
    int x_location = drone.getXLocation();
    int y_location = drone.getYLocation();
    
    // If map location is a * or drone ID(uppercase letter)
    if (grid[x_location][y_location] == '*' || grid[x_location][y_location] == drone.getID())
    {
        grid[x_location][y_location] = tolower(drone.getID());
        
        // replaces lowecase letter with * after it is moved in x direction
        if (grid[x_location - 1][y_location] == tolower(drone.getID()) )
            grid[x_location - 1][y_location] = '*';
        // replaces lowecase letter with * after it is moved in y direction
        else if (grid[x_location][y_location - 1] == tolower(drone.getID()) )
            grid[x_location][y_location - 1] = '*';
        // replaces lowecase letter with * after it is moved in x direction
        else if (grid[x_location + 1][y_location] == tolower(drone.getID()) )
            grid[x_location + 1][y_location] = '*';
        // replaces lowecase letter with * after it is moved in y direction
        else if (grid[x_location][y_location + 1] == tolower(drone.getID()) )
            grid[x_location][y_location + 1] = '*';
    }
    printGrid();
}

// Simple function that loops moving drone to target and loops back to airport
// At end sets airport location to * to show that there are no more drones
void droneRun(Drone drone)
{
    // Loop to send drone to target
    while (drone.landStatus() == false)
    {
        drone.moveDroneToTarget();
        updateGrid(drone);
    }
    
    // Loop to send drone home
    while (drone.homeStatus() == false)
    {
        drone.sendDroneHome();
        updateGrid(drone);
    }
    
    // Changes airport to * once drone has completed delivery
    grid[0][0] = '*';
}
