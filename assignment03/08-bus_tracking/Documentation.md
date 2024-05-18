# Real-Time Bus Tracking System

## A. Accuracy of Location Updates
To test the location update we first write a setup method that mocks the
GPS device, map, and notification service and initializes the bus tracker. 
First, we write a test where we initialize a bus ID and a example location.
We mock the response of the GPS service, let the bus tracker update the bus
location, and check whether the map service properly updates the location.
In the second test we mock the response of the GPS service to be null, let 
the bus tracker update the bus location, but then check whether 
the notification service notifies the passenger accordingly.
To enhance testability we move the GPS warning message to a function which
is publicly available.

## B. Notification of Key Events
For the notification of key events we write two tests. One time the location
is a key waypoint and the other time it is not. In the two tests a respective
location is initialized. The GPS service is mocked to return the location.
After the location update we check whether the passengers were notified in the
first case and whether no notification took place in the second case.
To enhance testability we move the arrival message to a function which
is publicly available. When verifying that the notification took place, we 
also capture the arguments passed and compare them to what we expect.

## C. Response to GPS Signal Loss
We have already implemented a test in which the GPS service returns a null 
location. This should simulate the case where the GPS device service is down.

## D. Comparison
Direct method calls
- Advantages
  - Simplicity
- Disadvantages
  - Tight coupling

Event-driven updates
- Advantages
  - Loose coupling
  - Support for asynchronous processing
- Disadvantages
  - Generally more complex

For a location service I would definitely tend to event-driven updates. The 
advantages outweigh the counterargument that it gets more complex. I would 
even say as the system grows an event-driven architecture is easier to handle.
With regard to testing one could say that a simpler system yields simpler
unit tests. Nevertheless, I think the tests would get messy too as the system 
grows. A well organized system seems to generally yield more organized tests.
It is debatable whether as the system grows such easy mocking is still possible.
So it would be crucial to design such a system for testability.