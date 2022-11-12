package com.halifaxcarpool.driver.controller;

import com.halifaxcarpool.driver.business.IViewRides;
import com.halifaxcarpool.driver.business.ViewRidesImpl;
import com.halifaxcarpool.driver.business.beans.Ride;
import com.halifaxcarpool.driver.presentation.DriverUI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DriverController {

    DriverUI driverUI = new DriverUI();

    @GetMapping("/driver/view_rides")
    @ResponseBody
    String viewRides() {
        IViewRides viewRides = new ViewRidesImpl();
        List<Ride> rideList = viewRides.getAllRides();
        return driverUI.displayRides(rideList);
    }

}
