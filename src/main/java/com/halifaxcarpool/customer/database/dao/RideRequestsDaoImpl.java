package com.halifaxcarpool.customer.database.dao;

import com.halifaxcarpool.commons.database.DatabaseImpl;
import com.halifaxcarpool.commons.database.IDatabase;
import com.halifaxcarpool.customer.business.beans.RideRequest;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RideRequestsDaoImpl implements IRideRequestsDao {

    IDatabase database;
    Connection connection;

    public RideRequestsDaoImpl() {
        database = new DatabaseImpl();
    }

    @Override
    public void insertRideRequest(RideRequest rideRequest) {
        try {
            connection = database.openDatabaseConnection();
            String SQL_STRING = "{CALL insert_ride_request(?,?,?,?,?)}";
            CallableStatement stmt = connection.prepareCall(SQL_STRING);
            stmt.setInt(1, rideRequest.getRideRequestId());
            stmt.setString(2, rideRequest.getStartLocation());
            stmt.setString(3, rideRequest.getEndLocation());
            stmt.setInt(4, rideRequest.getCustomerId());
            stmt.setString(5, new SimpleDateFormat("YYYY-MM-dd HH:mm:ss")
                    .format(Calendar.getInstance().getTime()));
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.closeDatabaseConnection();
        }
    }

    @Override
    public List<RideRequest> viewRideRequests(int customerId)  {
        try {
            connection = database.openDatabaseConnection();

            String SQL_STRING = "{CALL view_ride_requests(?)}";
            CallableStatement stmt = connection.prepareCall(SQL_STRING);
            stmt.setInt(1, customerId);

            ResultSet resultSet = stmt.executeQuery();

            return buildRideRequestsFrom(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.closeDatabaseConnection();
        }
        return new ArrayList<>();
    }

    public void cancelRideRequest(RideRequest rideRequest) {
        try {
            connection = database.openDatabaseConnection();
            String SQL_STRING = "{CALL cancel_ride_request(?, ?)}";
            CallableStatement statement = connection.prepareCall(SQL_STRING);
            statement.setInt(1, rideRequest.getRideRequestId());
            statement.setInt(2, rideRequest.getCustomerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<RideRequest> buildRideRequestsFrom(ResultSet resultSet) throws SQLException {

        List<RideRequest> rideRequests = new ArrayList<>();
        while (resultSet.next()) {
            int rideRequestId = Integer.parseInt(resultSet.getString("ride_req_id"));
            int customerId = Integer.parseInt(resultSet.getString("customer_id"));
            String startLocation = resultSet.getString("start_location");
            String endLocation = resultSet.getString("end_location");
            RideRequest rideRequest = new RideRequest(rideRequestId, customerId, startLocation, endLocation);
            rideRequests.add(rideRequest);
        }
        return rideRequests;
    }

}
