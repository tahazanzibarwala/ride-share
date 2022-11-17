package com.halifaxcarpool.customer.database.dao;

import com.halifaxcarpool.customer.business.beans.RideRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RideRequestsDaoMockImpl implements IRideRequestsDao {

    private static Map<Integer, List<RideRequest>> mockData = new HashMap<>();

    static {
        populateMockData();
    }

    @Override
    public void createRideRequest(RideRequest rideRequest) {

    }

    @Override
    public List<RideRequest> viewRideRequests(int customerId) {
        return mockData.get(customerId);
    }

    private static void populateMockData() {
        int customerId = 1;
        List<RideRequest> rideRequests = new ArrayList<>();
        rideRequests.add(new RideRequest(1, customerId, "Barrington st", "Dalhousie"));
        rideRequests.add(new RideRequest(2, customerId, "Oxford st", "Young st"));
        mockData.put(customerId, rideRequests);
        customerId = 2;
        rideRequests = new ArrayList<>();
        rideRequests.add(new RideRequest(3, customerId, "Citadel", "Gottingen st."));
        rideRequests.add(new RideRequest(4, customerId, "Dalhousie", "Park lane"));
        mockData.put(customerId, rideRequests);
    }

}
