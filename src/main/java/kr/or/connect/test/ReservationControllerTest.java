package kr.or.connect.test;

import static org.hamcrest.Matcher.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.jayway.jsonpath.JsonPath;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import kr.or.connect.ApplicationConfig;
import kr.or.connect.config.WebMvcContextConfiguration;
import kr.or.connect.controller.ReservationController;
import kr.or.connect.dao.DisplayinfoDao;
import kr.or.connect.dto.Displayinfo;
import kr.or.connect.service.DisplayInfoServie;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import com.jayway.jsonpath.JsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.sql.DataSource;
import javax.xml.stream.events.Comment;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebMvcContextConfiguration.class, ApplicationConfig.class })
public class ReservationControllerTest {

    @InjectMocks
    public ReservationController reservationController;

    @Mock
    DisplayInfoServie DisplayInfoServie;

    private MockMvc mockMvc;

    @Autowired
    private DisplayinfoDao dao;

    @BeforeEach
    public void createController() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
    }

    @Test
    @DisplayName("getDisplayInfo - controllet 테스트")
    public void get_well_product_when_categoryId_notNull() throws Exception {

        // given
        Integer categoryId = 1;
        int start = 2;

        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("categoryId", Integer.toString(categoryId));
        paramMap.add("start", Integer.toString(start));

        // when
        RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/displayinfos").params(paramMap)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(reqBuilder).andExpect(handler().handlerType(ReservationController.class))
                .andExpect(status().isOk()).andDo(print());

        // then
        verify(DisplayInfoServie).getProducts(categoryId, start);
    }

    @Test
    @DisplayName("getDisplayInfo - DAO 테스트")
    public void get_well_product_when_categoryId_null() throws Exception {

        // given
        Integer categoryId = null;
        int start = 1;

        JSONParser parser = new JSONParser();
        File file = new File("src/main/resources/products.json");
        JSONObject jObj_2 = (JSONObject) parser
                .parse(new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8")));
        List<Displayinfo> list = (List<Displayinfo>) jObj_2.get("products");

        // then
        Assertions.assertEquals(list.size(), dao.selectAll(categoryId, start, null).size());
    }

    @Test
    @DisplayName("[예외발생확인] getDisplayInfo - categoryId가 6일 때 🎭")
    public void exception_test() throws Exception {

        // given
        Integer categoryId = 6;
        int start = 1;

        // then
        assertThrows(IOException.class, () -> {
            reservationController.getDisplayInfo(categoryId, start);
        });
    }

    @Test
    @DisplayName("[예외발생확인] getComments - productId가 0 일때 📃")
    public void get_well_comment_when_productId_null() throws Exception {

        // given
        Integer productId = 0;
        int start = 1;

        // then
        assertThrows(IOException.class, () -> {
            reservationController.getComments(productId, start);
        });
    }

}
