package dborah.book_organizer.book_organizer.location;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    //ATTRIBUTES
    private final LocationRepository LOCATION_REPO;


    //CONSTRUCTOR
    @Autowired
    public LocationService(LocationRepository LOCATION_REPO) {
        this.LOCATION_REPO = LOCATION_REPO;
    }


    //METHODS
    public List<Location> getLocations(){
        return LOCATION_REPO.findAll();
    }

    public List<Location> getSpecifiLocations(String locationName, Integer space, Long id){
        Optional<List<Location>> locationOptional = LOCATION_REPO.getSpecificLocations(locationName, space, id);
        
        if(!locationOptional.isPresent()){
            throw new IllegalStateException(
                "Location with name=" + locationName 
                + ", id=" + id +", "
                + " and/or amount of space=" + space
                + " does not exist in database"
            );
        }
        
        return locationOptional.get();
    }

    public void addLocation(Location location){
        Optional<Location> locationOptional = LOCATION_REPO.getLocationByLocationName(location.getLocationName());

        if(locationOptional.isPresent()){
            throw new IllegalStateException(
                "location with name = "
                + location.getLocationName()
                + " already exists"
            );
        }

        LOCATION_REPO.save(location);
    }

    public void deleteLocation(Long id, String locationName){
        Optional<List<Location>> locationOptional = LOCATION_REPO.getSpecificLocations(locationName, null, id);

        if(!locationOptional.isPresent()){
            throw new IllegalStateException(
                "location with name=" + locationName
                + " and/or id=" + id
                + " does not exist in the database"
            );
        }
        
        LOCATION_REPO.delete(locationOptional.get().get(0));
    }
}
