package dborah.book_organizer.book_organizer.book_organizer.book;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="books")
public class BookController {
    //ATTRIBUTES
    private Logger logger = LoggerFactory.getLogger(BookController.class);
    private final BookService BOOK_SERVICE;

    
    //CONSTRUCTORS
    public BookController(BookService BOOK_SERVICE) {
        this.BOOK_SERVICE = BOOK_SERVICE;
    }


    //MAPPING
    @GetMapping
    public List<Book> getBooks(){
        return BOOK_SERVICE.getBooks();
    }

    @GetMapping(path="/search/")
    public List<Book> getSpecificBooks(
        @RequestParam(required=false) String title,
        @RequestParam(required=false) String author,
        @RequestParam(required=false) Integer minWidth,
        @RequestParam(required=false) Integer maxWidth,
        @RequestParam(required=false) String location
    ){
        return BOOK_SERVICE.getSpecificBooks(title, author, minWidth, maxWidth, location);
    }

    @PostMapping(path="/add/")
    public void addBook(@RequestBody Book book){
        logger.info("Passed to controller layer");
        BOOK_SERVICE.addBook(book);
    }

    @DeleteMapping(path="/delete/")
    public void deleteBook(
        @RequestParam(required=false) String title,
        @RequestParam(required=false) Long id,
        @RequestParam(required=false) String author
    ){
        BOOK_SERVICE.deleteBook(title, id, author);
    }

    @PutMapping(path="/update/id={id}")
    public void updateBook(
        @PathVariable("id") Long id,
        @RequestParam(required=false) String title,
        @RequestParam(required=false) String author,
        @RequestParam(required=false) Integer pageCount,
        @RequestParam(required=false) Integer width,
        @RequestParam(required=false) Long locationId
    ){
        BOOK_SERVICE.updateBook(title, author, pageCount, width, locationId, id);
    }
}
