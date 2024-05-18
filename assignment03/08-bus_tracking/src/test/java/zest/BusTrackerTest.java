package zest;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;
import org.mockito.internal.matchers.Null;

public class BusTrackerTest {
    private GPSDeviceService gpsDeviceService;
    private MapService mapService;
    private NotificationService notificationService;
    private BusTracker busTracker;

    @BeforeEach
    void SetUp() {
        gpsDeviceService = mock(GPSDeviceService.class);
        mapService = mock(MapService.class);
        notificationService = mock(NotificationService.class);
        busTracker = new BusTracker(gpsDeviceService, mapService, notificationService);
    }

    @Test
    void TestUpdateLocation(){
        String busId = "1";
        var location = new Location(47.4142883,8.5495906, false, "IFI");

        when(gpsDeviceService.getCurrentLocation(busId)).thenReturn(location);
        busTracker.updateBusLocation(busId);

        verify(mapService, times(1)).updateMap(busId, location);
    }

    @Test
    void TestUpdateNullLocation(){
        String busId = "1";

        when(gpsDeviceService.getCurrentLocation(busId)).thenReturn(null);
        busTracker.updateBusLocation(busId);

        verify(notificationService, times(1)).notifyPassengers(busId, BusTracker.GetGPSWarningMessage());
    }

    @Test
    void TestPassengerNotification(){
        String busId = "1";
        var location = new Location(47.4142883,8.5495906, true, "IFI");

        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);

        when(gpsDeviceService.getCurrentLocation(busId)).thenReturn(location);
        busTracker.updateBusLocation(busId);

        verify(notificationService, times(1)).notifyPassengers(stringCaptor.capture(), stringCaptor.capture());

        var values = stringCaptor.getAllValues();
        assertEquals(busId, values.get(0));
        assertEquals(BusTracker.GetArrivalMessage(location), values.get(1));
    }

    @Test
    void TestPassengerNoNotification(){
        String busId = "1";
        var location = new Location(47.4142883,8.5495906, false, "IFI");

        when(gpsDeviceService.getCurrentLocation(busId)).thenReturn(location);
        busTracker.updateBusLocation(busId);

        verify(notificationService, times(0)).notifyPassengers(any(String.class), any(String.class));
    }
}
