package dborah.book_organizer.book_organizer.location;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="locations")
public class LocationController {
    //ATTRIBUTES
    private final LocationService LOCATION_SERVICE;


    //CONSTRUCTOR
    public LocationController(LocationService LOCATION_SERVICE) {
        this.LOCATION_SERVICE = LOCATION_SERVICE;
    }


    //METHODS
    @GetMapping
    public List<Location> getLocations(){
        return LOCATION_SERVICE.getLocations();
    }

    @GetMapping(path="/search/")
    public List<Location> getSpecificLocations(
        @RequestParam(required=false) String locationName,
        @RequestParam(required=false) Integer totalSpace,
        @RequestParam(required=false) Long id
    ){
        return LOCATION_SERVICE.getSpecifiLocations(locationName, totalSpace, id);
    }

    @PostMapping(path="/add/")
    public void addLocation(@RequestBody Location location){
        LOCATION_SERVICE.addLocation(location);
    }

    @DeleteMapping(path="/delete/")
    public void deleteLocation(
        @RequestParam(required=false) Long id,
        @RequestParam(required=false) String locationName
    ){
        LOCATION_SERVICE.deleteLocation(id, locationName);
    }
}
