package com.zj;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2017/12/12
 * Time: 19:19
 * CopyRight: Zhouji
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeleniumTest {
    private static FirefoxDriver brower;

    @Value("${local.server.port}")
    private int port;

    // FIXME: 2017/12/12
    @BeforeClass
    public static void openBrower() {
        brower = new FirefoxDriver();
        brower.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void cloudBrower() {
        brower.quit();
    }

    @Test
    public void addBookToEmptyList () {
        String baseurl = "http://baidu.com";
        brower.get(baseurl);
        assertEquals("Your have no books in your book list", brower.findElementByTagName("div").getText());
        brower.findElementByName("title").sendKeys("BOOK TITLE");
        brower.findElementByName("author").sendKeys("BOOK AUTHOR");
        brower.findElementByName("isbn").sendKeys("BOOK ISBN");
        brower.findElementByName("description").sendKeys("BOOK TITLE, BOOK TITLE");
        brower.findElementByName("form").submit();
        WebElement dl = brower.findElementByCssSelector("dt.bookHeadline");
        assertEquals("Book Title", dl.getText());
    }
}
