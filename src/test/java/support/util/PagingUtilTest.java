package support.util;

import org.junit.Test;
import org.slf4j.Logger;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

public class PagingUtilTest {
    private static final Logger logger = getLogger(PagingUtilTest.class);
    private static final int MAX_PAGE = 10;
    public static final int SHOW_NUMBER_OF_PAGE = 5;

    @Test
    public void getNextPage() {
        logger.debug("next page test");
        for(int i = 0; i < SHOW_NUMBER_OF_PAGE; i++){
            PagingUtil pu = new PagingUtil(i, MAX_PAGE);
            logger.debug("cur page : {}" , i);
            assertThat(pu.getNextPage()).isEqualTo((Integer) SHOW_NUMBER_OF_PAGE);
        }

        for(int i = SHOW_NUMBER_OF_PAGE; i < MAX_PAGE; i++){
            PagingUtil pu = new PagingUtil(i, MAX_PAGE);
            logger.debug("cur page : {}" , i);
            assertThat(pu.getNextPage()).isEqualTo((Integer)MAX_PAGE);
        }

        PagingUtil pu = new PagingUtil(MAX_PAGE, MAX_PAGE);
        assertNull(pu.getNextPage());
    }

    @Test
    public void getPrePage() {
        logger.debug("pre page test");
        for(int i = 0; i < SHOW_NUMBER_OF_PAGE; i++){
            PagingUtil pu = new PagingUtil(i, MAX_PAGE);
            logger.debug("cur page : {}" , i);
            assertNull(pu.getPrePage());
        }

        for(int i = SHOW_NUMBER_OF_PAGE; i < MAX_PAGE; i++){
            PagingUtil pu = new PagingUtil(i, MAX_PAGE);
            logger.debug("cur page : {}" , i);
            assertThat(pu.getPrePage()).isEqualTo((Integer)4) ;
        }

        PagingUtil pu = new PagingUtil(MAX_PAGE, MAX_PAGE);
        assertThat(pu.getPrePage()).isEqualTo((Integer)9) ;
    }

    @Test
    public void getPages() {
        logger.debug("pages test");
        for(int i = 0; i < SHOW_NUMBER_OF_PAGE; i++){
            PagingUtil pu = new PagingUtil(i, MAX_PAGE);
            logger.debug("cur page : {}" , i);
            assertThat(pu.getPages().size()).isEqualTo(SHOW_NUMBER_OF_PAGE);
        }

        for(int i = SHOW_NUMBER_OF_PAGE; i < MAX_PAGE; i++){
            PagingUtil pu = new PagingUtil(i, MAX_PAGE);
            logger.debug("cur page : {}" , i);
            assertThat(pu.getPages().size()).isEqualTo(SHOW_NUMBER_OF_PAGE);
        }

        PagingUtil pu = new PagingUtil(MAX_PAGE, MAX_PAGE);
        assertThat(pu.getPages().size()).isEqualTo(1);
        logger.debug("cur pages : {}", pu.getPages());
    }
}