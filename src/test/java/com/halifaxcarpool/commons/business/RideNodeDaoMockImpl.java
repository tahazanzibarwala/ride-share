package com.halifaxcarpool.commons.business;

import com.halifaxcarpool.commons.business.beans.LatLng;
import com.halifaxcarpool.customer.business.beans.RideNode;
import com.halifaxcarpool.customer.database.dao.IRideNodeDao;

import java.util.ArrayList;
import java.util.List;

import static com.halifaxcarpool.commons.business.directions.DirectionTestSuiteData.latLngToPolylineMap;
import static com.halifaxcarpool.commons.business.directions.DirectionTestSuiteData.polyLineToRideNodesMap;

public class RideNodeDaoMockImpl implements IRideNodeDao {

    private static final List<RideNode> resultList = new ArrayList<>();
    @Override
    public boolean insertRideNodes(List<RideNode> rideNodes) {
        resultList.addAll(rideNodes);
        return true;
    }

    @Override
    public List<RideNode> getRideNodes(LatLng latLng) {
        String polyline = latLngToPolylineMap.get(latLng);
        if (polyLineToRideNodesMap.containsKey(polyline)) {
            return polyLineToRideNodesMap.get(polyline);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public int getLatestRideId() {
        int latestRideId = -1;
        for (RideNode rideNode: resultList) {
            if (rideNode.rideId > latestRideId) {
                latestRideId = rideNode.rideId;
            }
        }
        return latestRideId;
    }

}
