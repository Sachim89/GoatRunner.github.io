package com.GoatRunner.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.GoatRunner.controller.AdminController;
import com.GoatRunner.controller.BookingController;
import com.GoatRunner.controller.DriverController;
import com.GoatRunner.controller.LoginController;
import com.GoatRunner.controller.UpdateController;



public class MyApplication extends Application{
	@Override
    public Set<Class<?>> getClasses() {
		System.out.println("Entered");
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(LoginController.class);
        s.add(BookingController.class);
        s.add(DriverController.class);
        s.add(AdminController.class);
        s.add(UpdateController.class);
        
        return s;
    }
}
