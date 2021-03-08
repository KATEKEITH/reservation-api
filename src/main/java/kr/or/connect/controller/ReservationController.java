package kr.or.connect.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.jayway.jsonpath.internal.function.text.Length;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.or.connect.dto.Category;
import kr.or.connect.dto.Comment;
import kr.or.connect.dto.CommentImages;
import kr.or.connect.dto.DisplayInfoImage;
import kr.or.connect.dto.Displayinfo;
import kr.or.connect.dto.Price;
import kr.or.connect.dto.ProductImage;
import kr.or.connect.dto.Promotion;
import kr.or.connect.service.CategoryService;
import kr.or.connect.service.DisplayInfoImageService;
import kr.or.connect.service.DisplayInfoServie;
import kr.or.connect.service.ProductImageService;
import kr.or.connect.service.ProductPriceService;
import kr.or.connect.service.PromotionService;
import kr.or.connect.service.UserCommentImageService;
import kr.or.connect.service.UserCommentService;

import java.io.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api")
public class ReservationController {

    @Autowired
    CategoryService CategoryService;

    @Autowired
    DisplayInfoServie DisplayInfoServie;

    @Autowired
    DisplayInfoImageService DisplayInfoImageService;

    @Autowired
    UserCommentService UserCommentService;

    @Autowired
    UserCommentImageService UserCommentImageService;

    @Autowired
    ProductPriceService ProductPriceService;

    @Autowired
    ProductImageService ProductImageService;

    @Autowired
    PromotionService PromotionService;

    @ApiOperation(value = "카테고리 목록 구하기", notes = "카테고리 목록을 구합니다.")
    @ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 500, message = "Exception") })
    @GetMapping(path = "/categories")
    public HashMap<String, Object> getCategories() {

        List<Category> list = CategoryService.getCategories();
        int count = CategoryService.getCount();

        HashMap<String, Object> map = new HashMap<>();
        map.put("size", count);
        map.put("items", list);
        return map;
    }

    // @PostMapping(path = "/publish")
    // public void postClick(@RequestBody String req) {
    // System.out.println(">>>" + req);
    // }

    @GetMapping(path = "/displayinfos")
    @ApiOperation(value = "상품 목록 구하기")
    @ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 500, message = "Exception") })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "카테코리 아이디", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "start", value = "시작 위치", paramType = "query", dataType = "int") })
    public HashMap<String, Object> getDisplayInfo(
            @RequestParam(name = "categoryId", required = false) Integer categoryId,
            @RequestParam(name = "start", required = false, defaultValue = "0") int start) throws Exception {

        HashMap<String, Object> map = new HashMap<>();

        List<Displayinfo> list = DisplayInfoServie.getProducts(categoryId, start);
        int totalCount = DisplayInfoServie.getProductCount(categoryId);
        int count = list.size();

        if (list.size() == 0) {
            throw new IllegalArgumentException("상품 목록 구하기");
        }

        map.put("totalCount", totalCount);
        map.put("productCount", count);
        map.put("products", list);

        return map;
    }

    @ApiOperation(value = "전시 정보 구하기")
    @ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 500, message = "Exception") })
    @GetMapping(path = "/displayinfos/{displayId}")
    @ApiImplicitParam(name = "displayId", value = "디스플레이 아이디", paramType = "query", dataType = "int")
    public HashMap<String, Object> getDisplayInfoById(@PathVariable(name = "displayId") Integer displayId)
            throws Exception {

        if (displayId == 0) {
            throw new IllegalArgumentException("IllegalArgumentException, id=" + displayId);
        } else {
            List<ProductImage> productImagesList = ProductImageService.getProductImages(displayId);
            List<DisplayInfoImage> displayInfoImagesList = DisplayInfoImageService.getDisplayInfoImages(displayId);
            int avg = UserCommentService.getAvgScore();
            List<Price> priceList = ProductPriceService.getProductPrices(displayId);

            HashMap<String, Object> map = new HashMap<>();
            map.put("product", DisplayInfoServie.getProductsById(displayId));
            map.put("productImages", productImagesList);
            map.put("displayInfoImages", displayInfoImagesList);
            map.put("avgScore", avg);
            map.put("productPrices", priceList);
            return map;
        }

    }

    @ApiOperation(value = "댓글 목록 구하기")
    @ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 500, message = "Exception") })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "프로덕트 아이디", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "start", value = "시작 위치", paramType = "query", dataType = "int") })
    @GetMapping(path = "/displayinfos/{productId}/{start}")
    public HashMap<String, Object> getComments(@PathVariable(name = "productId", required = false) Integer productId,
            @PathVariable(name = "start", required = false) int start) throws Exception {

        int totalCount = UserCommentService.getCommentCount();
        int limit = UserCommentService.COMMENT_LIMIT;
        List<Comment> list = UserCommentService.getComments(productId, start);
        List<CommentImages> images = UserCommentImageService.getCommentImages();

        List<CommentImages> exams = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < images.size(); j++) {
                if (list.get(i).getReservationInfoId() == images.get(j).getReservationInfoId()) {
                    exams.add(images.get(j));
                }
            }
            list.get(i).setReservationUserCommentImages(exams);
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("commentcount", limit);
        map.put("reservationUserComments", list);
        return map;
    }

    @ApiOperation(value = "프로모션 정보 구하기")
    @ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 500, message = "Exception") })
    @GetMapping(path = "/promotions")
    public HashMap<String, Object> getPromotions() throws Exception {

        List<Promotion> list = PromotionService.getPromotions();
        int count = PromotionService.getPromotionCount();

        HashMap<String, Object> map = new HashMap<>();
        map.put("size", count);
        map.put("items", list);
        return map;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest req, Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURI());
        mav.addObject("contextPath", req.getContextPath());
        mav.addObject("queryString", req.getQueryString());
        mav.setViewName("/error/error");
        return mav;
    }

}
