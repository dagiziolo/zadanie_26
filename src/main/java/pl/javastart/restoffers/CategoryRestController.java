package pl.javastart.restoffers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryRestController {
    private CategoryRepository categoryRepository;

    public CategoryRestController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //    GET: /api/categories/names – zwraca listę nazw wszystkich dostępnych kategorii
    @GetMapping("api/categories/names")
    public List<String> getAllCategory() {
        return categoryRepository.findAllNames();
    }

    @GetMapping("api/categories")
    public List<CategoryDto> getCategoriesl() {
        List<CategoryDto> result = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            CategoryDto categoryDto = new CategoryDto(category.getName(), category.getDescription(), category.getOffers().size());
            result.add(categoryDto);
        }
        return result;
    }
}
