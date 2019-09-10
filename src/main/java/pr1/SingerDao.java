package pr1;

import pr1.Singer;
import java.util.List;

public interface SingerDao {
    List<Singer> findAll();
    List<Singer> findAllWithAlbum();
    Singer findById(Long id);
    Singer save(Singer singer);
    void delete(Singer singer);
}
