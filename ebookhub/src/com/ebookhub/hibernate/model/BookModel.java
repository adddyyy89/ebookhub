package com.ebookhub.hibernate.model;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.ebookhub.exceptions.EbookHubException;
import com.ebookhub.hibernate.dto.BookDTO;

public class BookModel extends GenericModel<BookDTO, BookEntity> {

	@Override
	BookDTO toDTO(BookEntity entity) {
		BookDTO dto = new BookDTO();
		dto.author = entity.getAuthor();
		dto.bookId = entity.getBookId();
		dto.bookName = entity.getBookName();
		dto.category = entity.getCategory();
		dto.location = entity.getLocation();
		dto.updateTimestamp = entity.getUpdateTimestamp();
		dto.views = entity.getViews();
		return dto;
	}

	@Override
	BookEntity toEntity(BookDTO dto) {
		BookEntity entity = new BookEntity();
		entity.setAuthor(dto.author);
		entity.setBookId(dto.bookId);
		entity.setBookName(dto.bookName);
		entity.setCategory(dto.category);
		entity.setLocation(dto.location);
		entity.setUpdateTimestamp(dto.updateTimestamp);
		entity.setViews(dto.views);
		return entity;
	}

	public void add(Session session, BookDTO dto) {
		session.saveOrUpdate(toEntity(dto));
	}

	public void delete(Session session, BookDTO dto) {
		session.delete(toEntity(dto));
	}

	public boolean checkExistsByLocation(Session session, BookDTO dto) throws EbookHubException {
		if (null == dto || null == dto.location || dto.location.isEmpty()) {
			return false;
		}

		String query = "SELECT COUNT(*) FROM book WHERE location = :location ;";

		@SuppressWarnings("rawtypes")
		List rows = session.createSQLQuery(query).addEntity(Book.class).setParameter("location", dto.location).list();

		if (rows.size() == 1) {
			return true;
		} else if (rows.size() > 1) {
			throw new EbookHubException("Something went horibly wrong. Multiple entries present for same location!!");
		} else {
			return false;
		}

	}

	public boolean checkExistsByName(Session session, BookDTO dto) throws EbookHubException {
		if (null == dto || null == dto.bookName || dto.bookName.isEmpty()) {
			return false;
		}

		String query = "SELECT COUNT(*) FROM book WHERE bookname = :name ;";

		@SuppressWarnings("rawtypes")
		List rows = session.createSQLQuery(query).addEntity(Book.class).setParameter("name", dto.bookName).list();

		if (rows.size() == 1) {
			return true;
		} else if (rows.size() > 1) {
			throw new EbookHubException("Something went horibly wrong. Multiple entries present for same name!!");
		} else {
			return false;
		}
	}

	public List<BookDTO> list(Session session) {
		String query = "SELECT * FROM book";
		@SuppressWarnings("unchecked")
		List<BookEntity> rows = session.createSQLQuery(query).addEntity(BookEntity.class).list();
		List<BookDTO> bookDTOList = new ArrayList<BookDTO>();

		rows.stream().forEach(row -> bookDTOList.add(toDTO(row)));

		return bookDTOList;
	}

}
