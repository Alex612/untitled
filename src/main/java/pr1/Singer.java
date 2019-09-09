package pr1;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "singer")
@NamedQueries({
        @NamedQuery(name = "Singer.findById",query = "select distinct s from Singers s " +
                "left join fetch s.albums a " + "left join fetch s.instruments i" +
                "where s.id = :id"),
        @NamedQuery(name = "Singer.findAllWithAlbum",query = "select distinct s from Singers s " +
                "left join fetch s.albums a " + "left join fetch s.instruments i")})
public class Singer extends AbstractEntity {
    public static final String FIND_SINGER_BY_ID = "Singer.findById";
    public static final String FIND_ALL_WITH_ALBUM = "Singer.findAllWithAlbum";



    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @ManyToMany
    @JoinTable(name = "singer_instrument",joinColumns = @JoinColumn(name = "SINGER_ID"),inverseJoinColumns = @JoinColumn(name = "INSTRUMENT_ID"))
    private Set<Instrument> instruments =new HashSet<>();
    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    private Date birthDate;
    private int version;
    @OneToMany(mappedBy = "singer",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Album> albums = new HashSet<>();

    @OneToMany(mappedBy = "singer",cascade = CascadeType.ALL,orphanRemoval = true)
    public Set<Album> getAlbums(){
        return albums;
    }





    public boolean addAlbum(Album album){
        album.setSinger(this);
        return getAlbums().add(album);
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public void setId(Long id){
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    public Long getId(){
        return this.id;
    }

    @Version
    @Column(name = "INSTRUMENT")
    public Set<Instrument> getInstruments(){
        return instruments;
    }


    @Version
    @Column(name = "VERSION")
    public int getVersion(){
        return version;
    }

    @Column(name = "FIRST_NAME")
    public String getFirstName(){
        return this.firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName(){
        return this.lastName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    public Date getBirthDate(){
        return birthDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String toString(){
        return "Singer id - " + id + " FirstName - " + firstName + " LastName -" +lastName + " BirthDate - " + birthDate;
    }

    public void removeAlbum(Album album) {
    }

    public void addInstrument(Instrument guitar) {
    }
}
