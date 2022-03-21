package stu.kms.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleTxServiceTest {

    @Setter(onMethod_ = {@Autowired})
    private SampleTxService service;

    @Test
    public void test1() {
        log.info("111");
    }
    @Test
    public void testLong() {
        String str = "Starry\r\n" +
                "Starry night\r\n" +
                "Paint your palette blue and grey\r\n" +
                "Look out on a summers' day";

        log.info(str.getBytes().length);

        service.addData(str);
    }
}
