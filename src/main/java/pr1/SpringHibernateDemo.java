package pr1;

import pr1.AppConfig;
import pr1.SingerDao;
import pr1.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class SpringHibernateDemo {
    private static Logger logger = LoggerFactory.getLogger(SpringHibernateDemo.class);

    public static void main(String...args){
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);
        Singer singer = singerDao.findById(2l);
        logger.info(singer.toString());
        ctx.close();
    }

    private static void listSingersWithAlbum(List<Singer> singers){
        logger.info("----- Listing singers with instruments: ");
        for(Singer singer: singers){
            logger.info(singer.toString());
            if (singer.getAlbums() !=null){
                for (Album album : singer.getAlbums()){
                    logger.info("\t" + album.toString());
                }
            }
        }
    }
}
