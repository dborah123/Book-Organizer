package dborah.book_organizer.book_organizer.book_organizer.book;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dborah.book_organizer.book_organizer.location.Location;
import dborah.book_organizer.book_organizer.location.LocationRepository;

@Service
public class BookService {
    //ATTRIBUTES
    private Logger logger = LoggerFactory.getLogger(BookService.class);
    private final BookRepository BOOK_REPO;
    private final LocationRepository LOCATION_REPO;


    //CONSTRUCTORS
    @Autowired
    public BookService(BookRepository BOOK_REPO, LocationRepository LOCATION_REPO) {
        this.BOOK_REPO = BOOK_REPO;
        this.LOCATION_REPO = LOCATION_REPO;
    }


    //METHODS
    public List<Book> getBooks(){
        return BOOK_REPO.findAll();
    } 

    public List<Book> getSpecificBooks(String title, String author, Integer minWidth, Integer maxWidth, String location){
        List<Book> book = BOOK_REPO.getSpecificBooks(title, author, minWidth, maxWidth, location);

        if(book == null){
            throw new IllegalStateException(
                "No books found"
            );
        }
        return book;
    }

    public void addBook(Book book){
        logger.info("Passed to service layer");
        Optional<Book> bookOptional = BOOK_REPO.getBookByTitle(book.getTitle());
        logger.info("Book Optional Created");
        
        if(bookOptional.isPresent()){
            logger.error("Book already in database");
            throw new IllegalStateException("Book already in database");
        }

        else{
            logger.info("Book not in database...saving process initiated");

            if(book.getLocation().getId() == null){
                //If user does not provde id
                logger.info("ID not specified");
                Optional<Location> id = LOCATION_REPO.getLocationByLocationName(book.getLocation().getLocationName());

                if(id.isPresent()){
                    //Set id of book to the queried location
                    logger.info("Location found in database");
                    book.setLocation(id.get());
                }

                else{
                    //Create a new Location object
                    logger.info("Location not found in database, new location created");
                    Location newLocation = new Location(book.getLocation().getLocationName(), book.getLocation().getTotalSpace());
                    LOCATION_REPO.save(newLocation);
                    book.setLocation(newLocation);
                }
            }

            BOOK_REPO.save(book);
        }
    }

    public void deleteBook(String title, Long id, String author){
        logger.info("Deleting Book");
        List<Book> books = BOOK_REPO.getBooksToDelete(title, id, author);
        logger.info("Books accumulated for deletion");

        while(!books.isEmpty()){
            logger.info("Deleting book");
            BOOK_REPO.deleteById(books.get(0).getId());
        }
        logger.info("Deleing complete");
    }   

    @Transactional
    public void updateBook(String title, String author, Integer pageCount, Integer width, Long locationId, Long id){
        /*
        Allows user to update current books in database
        */

        //Find desired book
        Optional<Book> bookOptional = BOOK_REPO.findById(id);
        Book book;
        
        if(!bookOptional.isPresent()){
            throw new IllegalStateException(
                "Book with id=" + id + " cannot be found in the database"
            );
        }
        else{
            book = bookOptional.get();
        }

        //Check if title author combo is already taken
        if(title != null){
            List<Book> books = BOOK_REPO.getSpecificBooks(title, null, null, null, null);

            if(!books.isEmpty()){
                throw new IllegalStateException(
                "the title, " + title + " is already taken in the database"
                );
            }
            else{
                book.setTitle(title);
            }
        }

        if(author != null){
            book.setAuthor(author);
        }

        if(pageCount != null){
            book.setPageCount(pageCount);
        }

        if(width != null){
            book.setWidth(width);
        }

        if(locationId != null){
            Optional<Location> location = LOCATION_REPO.findById(locationId);

            if(location.isPresent()){
                book.setLocation(location.get());
            }
            else{
                throw new IllegalStateException(
                    "Location with id=" + locationId + " cannot be found in database"
                );
            }
        }


    }
}
