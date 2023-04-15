package com.khadri.hibernate.main;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.khadri.hibernate.entities.Vehicle;
import com.khadri.hibernate.util.StandardRegistryUtil;

public class EntityMain {

	public static void main(String[] args) {
		Session session = StandardRegistryUtil.createSession(Vehicle.class);
		
		
		NativeQuery<Vehicle> nativeQuery1 = session.createNativeQuery("select * from VEHICLE");
		
		nativeQuery1.addEntity(Vehicle.class);
		
		nativeQuery1.getResultList().forEach((v)->{
			System.out.println(v.getId());
			System.out.println(v.getName());
			System.out.println(v.getModel());
		});
	}
}
