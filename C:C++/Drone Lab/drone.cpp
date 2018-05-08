// Drone class contatins basic variables that a drone has (ID, location, target, status)
// as well as helper functions to get those values and functions for basic movement of the Drone
// to and from a target location. 


#include <iostream>
using namespace std;


class Drone
{
    int x_location, y_location;
    int x_destination, y_destination;
    char ID;
    bool landed, home;
public:
    Drone(char id, int xLoc, int yLoc)
    {
        x_location = 0;
        y_location = 0;
        ID = id;
        x_destination = xLoc;
        y_destination = yLoc;
        landed = false;
        home = true;
    }
    void set_location (int x, int y) { x_location = x; y_location = y; }    // Sets location of Drone
    int getXLocation () { return x_location; }                              // Gets Drone's x location
    int getYLocation () { return y_location; }                              // Gets Drone's y location
    int getXDestination () { return x_destination; }                        // Gets Drone's target x location
    int getYDestination () { return y_destination; }                        // Gets Drone's target y location
    char getID() { return ID; }                                             // Gets Drone's ID
    bool landStatus() { return landed; }                                    // Drone landed means delivered
    bool homeStatus() { return home; }                                      // Drone home means at airport
    
    // Basic movement to get Drone to a target location to make a delivery
    void moveDroneToTarget()
    {
        this->home = false;
        
        // Check if x location != x destination
        if (this->x_location != this->x_destination)
        {
            this->set_location(x_location + 1, y_location);
            if (this->x_location == x_destination && this->y_location == y_destination)
                this->landed = true;
        }
            
        // Check if y location != y destination
        else if (this->y_location != this->y_destination)
        {
            this->set_location(x_location, y_location + 1);
            if (this->x_location == x_destination && this->y_location == y_destination)
                this->landed = true;
        }
    }
    
    // Basic movement to get Drone back to airport after making a delivery
    void sendDroneHome()
    {
        // Checks if x location is 0 (x location of airport)
        if (this->x_location != 0)
        {
            this->x_location = this->x_location - 1;
            
            // If at airport (0,0) then drone is home
            if (this->x_location == 0 && this->y_location == 0)
                this->home = true;
        }
        
        // Checks if y location is 0 (y location of airport)
        else if (this->y_location != 0)
        {
            this->y_location = this->y_location - 1;
            
            // If at airport (0,0) then drone is home
            if (this->x_location == 0 && this->y_location == 0)
                this->home = true;
        }
    }
    
};
