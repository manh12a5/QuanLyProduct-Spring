package service.category;

import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.ICategoryRepository;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        List<Category> categoryList = (List<Category>) categoryRepository.findAll();
        return categoryList;
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Category findById(Long id) {
        return null;
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<Category> findByName(String name) {
        return null;
    }
}
