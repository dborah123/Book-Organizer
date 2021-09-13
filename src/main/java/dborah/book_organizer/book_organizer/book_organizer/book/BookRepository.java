package dborah.book_organizer.book_organizer.book_organizer.book;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    
    @Query("SELECT b FROM Book b WHERE b.title = ?1")
    Optional<Book> getBookByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.author = ?1")
    Optional<Book> getBookByAuthor(String author);

    @Query("SELECT b FROM Book b WHERE "
    + "(:title is null OR b.title = :title) "
    + "AND (:author is null OR b.author = :author) "
    + "AND (:minWidth is null OR b.width > :minWidth) "
    + "AND (:maxWidth is null OR b.width < :maxWidth) "
    + "AND (:location is null OR b.location.locationName = :location)"
    )
    List<Book> getSpecificBooks(
        @Param("title") String title,
        @Param("author") String author,
        @Param("minWidth") Integer minWidth,
        @Param("maxWidth") Integer maxWidth,
        @Param("location") String location
    );

    @Query("SELECT b FROM Book b WHERE "
    + "(:title is null OR b.title = :title) "
    + "AND (:id is null OR b.id = :id) "
    + "AND (:author is null OR b.author = :author)"
    )
    List<Book> getBooksToDelete(
        @Param("title") String title,
        @Param("id") Long id,
        @Param("author") String author
    );
}
