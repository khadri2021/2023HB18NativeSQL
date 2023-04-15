package com.khadri.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class StandardRegistryUtil {

	private static SessionFactory factory;
	private static Session session;

	public static Session createSession(Class<?>... objects) {

		BootstrapServiceRegistry bootstrapServiceRegistry = new BootstrapServiceRegistryBuilder()
				.build();

		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder(
				bootstrapServiceRegistry).build();

		MetadataSources metadataSources = new MetadataSources(serviceRegistry);

		for (int i = 0; i < objects.length; i++) {
			metadataSources.addAnnotatedClass(objects[i]);
		}

		factory = metadataSources.buildMetadata().buildSessionFactory();

		session = factory.openSession();

		return session;
	}

	public static void closeResources() {
		session.close();
		factory.close();
	}

}
