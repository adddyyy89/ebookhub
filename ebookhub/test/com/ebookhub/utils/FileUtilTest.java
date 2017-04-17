package com.ebookhub.utils;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class FileUtilTest {

	@Test
	public void testIsPdf() throws IOException {
		File file = new File("abc.pdf");
		file.createNewFile();
		assertThat(FileUtil.isPdf(file), is(true));
		file.delete();
		
		file = new File("abc.txt");
		file.createNewFile();
		assertThat(FileUtil.isPdf(file), is(false));
		file.delete();
	}
	
	@Test
	public void testGetFileName() throws IOException{
		File file = new File("abc.pdf");
		file.createNewFile();
		assertThat(FileUtil.getFileName(file), is("abc"));
		file.delete();
		
		file = new File("abcas.dasd.txt");
		file.createNewFile();
		assertThat(FileUtil.getFileName(file), is("abcas.dasd"));
		file.delete();
	}

}
