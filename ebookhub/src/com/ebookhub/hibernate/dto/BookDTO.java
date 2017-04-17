package com.ebookhub.hibernate.dto;

import java.sql.Timestamp;

public class BookDTO implements Comparable<BookDTO> {
	public long bookId;
	public String bookName;
	public String location;
	public Timestamp updateTimestamp;
	public long views;
	public String category;
	public String author;

	@Override
	public int compareTo(BookDTO otherBookDTO) {
		return this.location.equals(otherBookDTO.location) ? 0 : 1;
	}

}
