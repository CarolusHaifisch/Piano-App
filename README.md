# My Personal Project

## Java program to determine most efficient transit route to take to reach destination within Vancouver

The application will, given a start and end location, determine the most efficient transit route or routes to take to
reach the destination from the starting location, and compute the estimated travel time factoring in traffic
(implemented as some constant term, if taking buses or long waits are required), as well as train/bus schedules
and capacity of vehicles. This application will be limited to the public transit network only with only major bus routes
implemented. All subway routes will be implemented. Addresses will not be implemented (for now) so start and end
locations will be given in terms of distance relative to a transit stop/connection point. 

This application can be used by anyone living in Vancouver who wants to determine the most efficient transit route to 
take to get to a destination, and when it is complete the GUI will show the user where they are within the network and
where they are headed to next. (Possibly also add the option for the user to change routes at which point the
application will recalculate estimated arrival time and efficient route). I am interested in this idea because it can be
done without having to access online databases, which are not permitted for the project, yet this idea is still
applicable and useful to everyday life and the complexity of the application is very adjustable depending on needs and 
my ability. 

## User Stories

- As a user, I want to be able to add certain transit connections that I want to have included in my overall route,
and be able to add new desired connections to the itinerary or change my route while traveling and get a new estimate
from the new route I choose. (Where connections are individual items that can be added to a route which contains
multiple connections)
- As a user, I want to be able to view the list of transit connections I must take to reach my destination in the most
efficient way.
- As a user, I want to be able to know the estimated travel time my route will require under current estimated traffic
conditions.
- As a user, I want to be able to save a certain route into memory for future use. 
- As a user, I want to be able to view my current progress along the route to my destination and see how many stops and
connections I have left to take and which ones I have passed.
