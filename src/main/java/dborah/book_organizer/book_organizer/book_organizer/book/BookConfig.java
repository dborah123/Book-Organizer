package dborah.book_organizer.book_organizer.book_organizer.book;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dborah.book_organizer.book_organizer.location.Location;
import dborah.book_organizer.book_organizer.location.LocationRepository;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner(BookRepository bookRepo, LocationRepository locationRepo){ 
        return args -> {
            Location deskBookShelf = new Location(
                "Desk Book Shelf",
                1
            );


            Book absalomAbsalom = new Book(
                "Absalom, Absalom!",
                "William Faulkner",
                303,
                20,
                deskBookShelf
            );

            Book theSoundAndTheFury = new Book(
                "The Sound and the Fury",
                "William Faulkner",
                240,
                15,
                deskBookShelf
            );

            locationRepo.saveAll(List.of(deskBookShelf));
            bookRepo.saveAll(List.of(absalomAbsalom, theSoundAndTheFury));
        };
    }
}
