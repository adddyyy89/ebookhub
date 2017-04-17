package com.ebookhub.utils;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.ebookhub.hibernate.dto.BookDTO;

public class FileUtil {

	public static boolean isPdf(File file) {
		if (null == file)
			return false;

		return file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length())
				.equalsIgnoreCase("pdf") ? true : false;
	}

	public static String getFileName(File file) {
		if (null == file)
			return null;

		return file.getName().substring(0, file.getName().lastIndexOf("."));
	}

	public static List<BookDTO> getBooks(File rootDir) {
		List<BookDTO> ebooks = new ArrayList<>();
		BookDTO book = null;
		for (File file : rootDir.listFiles()) {
			book = null;
			if (file.isFile() && FileUtil.isPdf(file)) {
				book = new BookDTO();
				book.bookName = FileUtil.getFileName(file);
				book.location = file.getAbsolutePath();
				book.updateTimestamp = new Timestamp(System.currentTimeMillis());
				book.views = 0;
				ebooks.add(book);
			}
		}
		return ebooks;
	}
}
