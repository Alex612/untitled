package pr1;

import pr1.Singer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

@Transactional
@Repository("SingerDao")
public class SingerDaoImpl implements SingerDao {
    private SessionFactory sessionFactory;
    private static final Log logger = LogFactory.getLog(SingerDaoImpl.class);

    @Transactional(readOnly = true)
    public List<Singer> findAllWithAlbum(){
        return sessionFactory.getCurrentSession().createNamedQuery("Singer.findAllWithAlbum").list();
    }




    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Singer s").list();
    }

    @Transactional(readOnly = true)
    public Singer findById(Long id) {
        return (Singer) sessionFactory.getCurrentSession().getNamedQuery("Singer.findById").setParameter("id",id).uniqueResult();
    }


    public Singer save(Singer singer) {
        sessionFactory.getCurrentSession().saveOrUpdate(singer);
        logger.info("Singer saved with id: "+ singer.getId());
        return singer;
    }

    @Override
    public void delete(Singer contact) {

    }
}
