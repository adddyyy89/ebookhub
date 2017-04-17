package com.ebookhub.utils;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import com.ebookhub.utils.StartupSync;

public class StartupSyncTest {

	@Test
	public void testIsHealthy() {
		assertThat(StartupSync.isHealthy(), is(true));
	}

}
