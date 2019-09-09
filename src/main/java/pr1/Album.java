package pr1;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "album")
public class Album implements Serializable {
    private Long id;
    private String title;
    private Date releaseDate;
    private int version;
    private Singer singer;
    private Set<Instrument> instruments = new HashSet<>();


    @ManyToMany
    @JoinTable(name = "singer_instrument",joinColumns = @JoinColumn(name = "SINGER_ID"),
            inverseJoinColumns = @JoinColumn(name = "INSTRUMENT_ID"))
    public Set<Instrument> getInstruments(){
        return instruments;
    }

    public void setInstruments(Set<Instrument> instruments) {
        this.instruments = instruments;
    }

    @ManyToOne
    @JoinColumn(name = "SINGER_ID")
    public Singer getSinger(){
        return this.singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    public Long getId(){
        return this.id;
    }

    @Version
    @Column(name = "VERSION")
    public int getVersion(){
        return version;
    }

    @Column
    public String getTitle(){
        return this.title;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "RELEASE_DATE")
    public Date getReleaseDate(){
        return this.releaseDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setVersion(int version) {
        this.version = version;
    }



    @Override
    public String toString(){
        return "AlbumID - " + id + " Title -" + title + " ReleaseDate - " + releaseDate;
    }
}
