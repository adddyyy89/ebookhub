package com.ebookhub.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	private static Logger logger = LogManager.getLogger(HibernateUtil.class);
	static {
		logger.debug("Hibernate: Configuration initializing..");
		try {
			Configuration configuration = new Configuration().configure();
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties());
			sessionFactory = configuration.buildSessionFactory(builder.build());
			logger.debug("Hibernate: Session factory created..");
		} catch (Exception e) {
			logger.error("Hibernate: Initial Session factory creation failed due to exception " + e.getMessage(), e);
			throw new ExceptionInInitializerError(e);
		}
		logger.debug("Hibernate: Configuration completed..");
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
