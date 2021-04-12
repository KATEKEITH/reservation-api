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

import org.hamcrest.Matcher;
import org.json.JSONObject;
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
import kr.or.connect.config.WebAppInitializer;

import kr.or.connect.controller.ReservationController;
import kr.or.connect.dao.DisplayinfoDao;
import kr.or.connect.dto.Displayinfo;
import kr.or.connect.service.DisplayInfoServie;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.is;

import javax.sql.DataSource;
import javax.xml.stream.events.Comment;

import org.springframework.web.bind.annotation.ControllerAdvice;

import javassist.NotFoundException;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebAppInitializer.class, ApplicationConfig.class })
public class ReservationControllerTest {

    @InjectMocks
    public ReservationController reservationController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
    }

    @Test
    @DisplayName("상품 목록 조회 테스트")
    public void get_well_product_when_categoryId_notNull() throws Exception {

        ResultActions result = mockMvc
                .perform(get("/api/displayinfos?categoryId=2&start=1").accept(MediaType.APPLICATION_JSON));

        result.andDo(print()).andExpect(status().isOk())
                // .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(handler().handlerType(ReservationController.class))
                .andExpect(handler().methodName("getDisplayInfo"));
        // .andExpect(jsonPath("$[0]['products']").exists());
        // .andExpect(jsonPath("$.success", is(true)))
        // .andExpect(jsonPath("$.products").isArray());
        // .andExpect(jsonPath("$.reservationUserComments.length()", is(5)));
    }

    @Test
    @DisplayName("상품 조회 실패 테스트 (존재하지 않는 ID)")
    public void get_well_comment_when_categoryId_not_exist() throws Exception {

        ResultActions result = mockMvc
                .perform(get("/api/displayinfos?categoryId=7&start=1").accept(MediaType.APPLICATION_JSON));

        result.andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(handler().handlerType(ReservationController.class))
                .andExpect(handler().methodName("getDisplayInfo"));

        // .andExpect(handler().handlerType(ReservationController.class))
        // .andExpect(handler().methodName("getDisplayInfo"))
        // .andExpect(jsonPath("$.success", is(false)))
        // .andExpect(jsonPath("$.error").exists())
        // .andExpect(jsonPath("$.error.status", is(404)));
    }

}
