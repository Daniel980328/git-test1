package rent;

public class Book {

	
	 private String bookId;
	    private long categoryId;
	    private String title;
	    private boolean rented;
	    private String writer;
	    private String publisher;
	    private String description;
	    
	public Book (String bookId, long categoryId, String title, boolean rented, String writer, 
	String publisher, String description) {
		
		 this.bookId = bookId;
	        this.categoryId = categoryId;
	        this.title = title;
	        this.rented = rented;
	        this.writer = writer;
	        this.publisher = publisher;
	        this.description = description;
		 }
	 public String getbookId() {
	        return bookId;
	    }

	    public long getCategoryId() {
	        return categoryId;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public boolean isRented() {
	        return rented;
	    }

	    public String getWriter() {
	        return writer;
	    }

	    public String getPublisher() {
	        return publisher;
	    }

	    public String getDescription() {
	        return description;
	    }

	    // Setter 메서드
	    public void setId(String bookId) {
	        this.bookId = bookId;
	    }

	    public void setCategoryId(long categoryId) {
	        this.categoryId = categoryId;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public void setRented(boolean rented) {
	        this.rented = rented;
	    }

	    public void setWriter(String writer) {
	        this.writer = writer;
	    }

	    public void setPublisher(String publisher) {
	        this.publisher = publisher;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }
	    
	    @Override
	    public String toString() {
	        return "Book{" +
	                "bookid='" + bookId + '\'' +
	                ", categoryId=" + categoryId +
	                ", title='" + title + '\'' +
	                ", rented=" + rented +
	                ", writer='" + writer + '\'' +
	                ", publisher='" + publisher + '\'' +
	                ", description='" + description + '\'' +
	                '}';
	    }
	    
}
