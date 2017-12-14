package com.zj;

import com.zj.model.Reader;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2017/12/12
 * Time: 16:50
 * CopyRight: Zhouji
 */

//@ContextConfiguration(classes = AddressBookConfiguration.class)
//@SpringApplicationConfiguration(classes = ReadingListController.class)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class AddressServiceTests {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity()).build();
    }

    @Test
    public void homepage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/reading"))
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("book", Matchers.is(Matchers.empty())));

    }

    @Test
    public void postBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/reading")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "Book title"))
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("book", Matchers.is(Matchers.empty())));
    }


    @Test
    @WithMockUser(username = "cci", password = "sdf", roles = "READER")
    public void security() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "http://localhost:8080/llogin"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("book", Matchers.is(Matchers.empty())));
    }


    @Test
    @WithUserDetails("cci")
    public void securityUserDeatails() throws Exception {
        Reader exReader = new Reader();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attribute("reader", samePropertyValuesAs(exReader)))
                .andExpect(model().attribute("books", hasSize(0)));
    }


}
