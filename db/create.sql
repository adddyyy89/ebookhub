CREATE SEQUENCE book_bookid_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE book_bookid_seq
  OWNER TO postgres;
  
CREATE TABLE book
(
  bookid bigint NOT NULL DEFAULT nextval('book_bookid_seq'::regclass),
  bookname text NOT NULL,
  location text NOT NULL,
  uploadtimestamp time without time zone NOT NULL DEFAULT now(),
  views bigint NOT NULL DEFAULT 0,
  category text,
  author text,
  CONSTRAINT pk_book_bookid PRIMARY KEY (bookid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE book
  OWNER TO postgres;