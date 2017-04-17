package com.ebookhub.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.ebookhub.exceptions.EbookHubException;
import com.ebookhub.exceptions.SetupException;

/**
 * This should run on application startup
 * 
 * Goal 1 - Check properties files is valid
 * 
 * Goal 2 - Check database is up
 * 
 * Goal 3 - Sync ebook directory with db
 * 
 * @author Anand
 *
 */
public class StartupSync {

	static Logger logger = Logger.getLogger(StartupSync.class);

	private static boolean healthy = false;

	static Properties properties;

	static String root_directory;

	final static String PROPERTIES_PATH = "src\\ebookhub.properties";

	final static String EBOOK_DIRECTORY_ROOT = "ebook.directory.root";

	static {

		try {
			logger.info("StartupSync started");

			// Check Properties and ebook directory existence
			checkProperties();

			// Check Database connectivity and Hibernate connection
			testDBConnection();

			// Sync Database with ebook directory
			syncDB();

			// System health
			healthy = true;

			logger.info("StartupSync completed - System is good and Database is in sync");
		} catch (EbookHubException ex) {
			logger.error("Unable to setup due to - " + ex.getMessage());
		}

	}

	private static void checkProperties() throws SetupException {
		logger.info("StartupSync.checkProperties called");

		try {
			File file = new File("PROPERTIES_PATH");
			properties = new Properties();
			properties.load(new FileInputStream(file.getAbsolutePath()));

			// Check root directory
			if (!properties.containsKey(EBOOK_DIRECTORY_ROOT)) {
				throw new SetupException("ebookhub.properties - Entry Missing - ebook root directory");
			}

			root_directory = properties.getProperty(EBOOK_DIRECTORY_ROOT);

			if (null == root_directory || root_directory.isEmpty()) {
				throw new SetupException("ebookhub.properties - Entry Missing - ebook root directory not specified");
			}

			File rootDir = new File(root_directory);
			if (null == rootDir || rootDir.isFile()) {
				throw new SetupException(
						"ebookhub.properties - Invalid Entry - ebook root directory has invalid entry");
			}

		} catch (FileNotFoundException fnf) {
			logger.error("ebookhub.properties - File Not Found");
			throw new SetupException("ebookhub.properties - File Not Found");
		} catch (IOException e) {
			logger.error("Unable to load ebookhub.properties");
			throw new SetupException("Unable to load ebookhub.properties");
		}

		logger.info("StartupSync.checkProperties completed");
	}

	private static void testDBConnection() {
		logger.info("StartupSync.testDBConnection called");

		logger.info("StartupSync.testDBConnection completed");
	}

	private static void syncDB() {
		logger.info("StartupSync.syncDB called");

		logger.info("StartupSync.syncDB completed");
	}

	public static boolean isHealthy() {
		return healthy;
	}
}
