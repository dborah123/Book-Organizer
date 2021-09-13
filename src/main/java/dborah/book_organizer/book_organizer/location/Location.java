package dborah.book_organizer.book_organizer.location;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Location {
    //ATTRIBUTES
    @Id
    @SequenceGenerator(
        name="location_sequence",
        sequenceName="location_sequence",
        allocationSize=1
    )
    @GeneratedValue(
        strategy=GenerationType.SEQUENCE,
        generator="location_sequence"
    )
    private Long id;
    private String locationName;
    private Integer totalSpace;
    private Integer spaceRemaining;


    //CONSTRUCTORS
    public Location() {
    }

    public Location(String locationName, Integer totalSpace) {
        this.locationName = locationName;
        this.totalSpace = totalSpace;
        this.spaceRemaining = getTotalSpace();
    }


    //GETTERS AND SETTERS
    public String getLocationName() {
        return this.locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Integer getTotalSpace() {
        return this.totalSpace;
    }

    public void setSpace(Integer totalSpace) {
        this.totalSpace = totalSpace;
    }

    public Integer getSpaceRemaining() {
        return this.spaceRemaining;
    }

    public void setSpaceRemaining(Integer spaceRemaining) {
        this.spaceRemaining = spaceRemaining;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    //TOSTRING
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", locationName='" + getLocationName() + "'" +
            ", totalSpace='" + getTotalSpace() + "'" +
            ", spaceRemaining='" + getSpaceRemaining() + "'" +
            "}";
    }
    

}
