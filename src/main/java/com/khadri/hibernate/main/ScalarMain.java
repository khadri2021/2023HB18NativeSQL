package com.khadri.hibernate.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StringType;

import com.khadri.hibernate.entities.Vehicle;
import com.khadri.hibernate.util.StandardRegistryUtil;

public class ScalarMain {

	public static void main(String[] args) {
		Session session = StandardRegistryUtil.createSession(Vehicle.class);

		NativeQuery<Vehicle> nativeQuery1 = session.createNativeQuery("select * from VEHICLE", Vehicle.class);
		System.out.println("================== Vehicle Information ================== ");
		nativeQuery1.getResultStream().forEach((v)->{
			System.out.println("Id : "+v.getId()); 
			System.out.println("Name: "+v.getName()); 
			System.out.println("Model: "+v.getModel()); 
		 });
		
		NativeQuery<Vehicle> nativeQuery2 = session.createNativeQuery("select * from VEHICLE where model=:modelName", Vehicle.class);
		nativeQuery2.setParameter("modelName", "SUV");
		System.out.println("================== Vehicle Information ================== ");
		nativeQuery2.getResultStream().forEach((v)->{
			System.out.println("Name: "+v.getName()); 
			System.out.println("Model: "+v.getModel()); 
		});
		
		//Scalar column values
		NativeQuery<Object[]> nativeQuery3 = session.createNativeQuery("select MODEL,NAME from VEHICLE");
		System.out.println("================== Vehicle Information ================== ");
		nativeQuery3.getResultStream().forEach((objArray)->{
			String model = (String)objArray[0];
			String name = (String)objArray[1];
			System.out.println("Model : "+model);
			System.out.println("Name : "+name);
		});
		
		//Scalar column values
		NativeQuery<String> nativeQuery4 = session.createNativeQuery("select * from VEHICLE");
		System.out.println("================== Vehicle Information ================== ");
		
		nativeQuery4.addScalar("MODEL", StringType.INSTANCE);
		
		nativeQuery4.getResultStream().forEach((str)->{
			System.out.println("Model : "+str);
		});
		
		
		//insertVehicle(session);
	}

	private static void insertVehicle(Session session) {
		Transaction txn = session.beginTransaction();

		Vehicle vehicle = new Vehicle();
		vehicle.setModel("SELTOS");
		vehicle.setName("KIA");

		session.save(vehicle);
		txn.commit();
	}
}
