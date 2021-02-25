package kr.or.connect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.dao.CategoryDao;
import kr.or.connect.dto.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> getCategories() {
        List<Category> list = categoryDao.selectAll();
        return list;
    }

    @Override
    public int getCount() {
        return categoryDao.selectCount();
    }
}
