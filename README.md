# Electric Fields Lines Program
Draws electric field lines between point charges


## Installation

This program requires  Java Runtime Environment (JRE) 8 to run. Download it [here](https://www.oracle.com/java/technologies/javase-jre8-downloads.html).

Download the program (a jar file) from the latest release on the [releases page](https://github.com/encrxpted/Electric-Fields-Lines-Program/releases). The jar file will run as long as you have JRE 8 installed.


## How to Use
The following is up-to-date with version v0.0-alpha

### Point Charges
* Add a point charge: type in the point charge's charge, x-coordinate, and y-coorindate in the 3 text fields at the top of the screen, then click "add charge." Note that the top left corner will be the origin. The x-coordinate will be bigger toward the right, while the y-coordinate will be bigger toward the bottom.
* Remove a point charge: right click the point charge and click "delete charge."

### Field Lines

* Add a field line: right click the point charge you want to start the line at, then click "add a line." Then, fill in the angle (in degrees) that the field line should start with. Note that 0 degrees is a horizontal line pointing to the right, and positive increments are counterclockwise (like the unit circle).

Note: When field lines are drawn, they are drawn according to the electric field created by the present point charges. Field lines are refreshed each time a point charge is added or deleted. When creating diagrams, experiment with different numbers of lines/initial angles to get the correct ratios of lines coming out of each point charge.

## To-Dos

* Removing lines feature
* Clear all feature
* "How it works" section in readme
* "View details" feature for point charges to view its coords, charge, and any lines that begin from it

## Previous Versions

* v0.0-alpha (first version)
  * Feature to add and remove point charges
  * Feature to add field lines coming out any point charge at any angle
