package dborah.book_organizer.book_organizer.location;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{
    @Query("SELECT l FROM Location l WHERE l.locationName = ?1")
    Optional<Location> getLocationByLocationName(String title);

    @Query("SELECT l FROM Location l WHERE "
    + "(:locationName is null OR l.locationName = :locationName) "
    + "AND (:space is null OR l.totalSpace = :space) "
    + "AND (:id is null OR l.id = :id)"
    )
    Optional<List<Location>> getSpecificLocations(
        @Param("locationName") String locationName,
        @Param("space") Integer space,
        @Param("id") Long id
    );

}
