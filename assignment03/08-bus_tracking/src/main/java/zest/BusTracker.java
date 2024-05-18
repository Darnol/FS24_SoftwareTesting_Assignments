package zest;

public class BusTracker {
    private GPSDeviceService gpsService;
    private MapService mapService;
    private NotificationService notificationService;
    static private final String gpsWarningMessage = "GPS signal lost. Please check back later.";
    static private final String arrivalMessage = "The bus has arrived at ";

    public BusTracker(GPSDeviceService gpsService, MapService mapService, NotificationService notificationService) {
        this.gpsService = gpsService;
        this.mapService = mapService;
        this.notificationService = notificationService;
    }

    static public String GetGPSWarningMessage() {
        return gpsWarningMessage;
    }

    static public String GetArrivalMessage(Location location) {
        return arrivalMessage + location.getWaypointName();
    }

    public void updateBusLocation(String busId) {
        Location newLocation = gpsService.getCurrentLocation(busId);
        if (newLocation != null) {
            mapService.updateMap(busId, newLocation);
            if (newLocation.isKeyWaypoint()) {
                notificationService.notifyPassengers(busId, GetArrivalMessage(newLocation));
            }
        } else {
            handleGPSFailure(busId);
        }
    }

    private void handleGPSFailure(String busId) {
        notificationService.notifyPassengers(busId, GetGPSWarningMessage());
    }
}
