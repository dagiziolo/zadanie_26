package pl.javastart.restoffers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CategoryRestController {
    private CategoryRepository categoryRepository;

    public CategoryRestController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //    GET: /api/categories/names – zwraca listę nazw wszystkich dostępnych kategorii
    @GetMapping("api/categories/names")
    public List<String> getNameCategory() {
        return categoryRepository.findAllNames();
    }

    //    GET: /api/categories – zwraca listę wszystkich kategorii w serwisie.
    @GetMapping("api/categories")
    public List<CategoryDto> getCategories() {
        List<CategoryDto> listCategoryDto = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            CategoryDto categoryDto = new CategoryDto(category.getName(), category.getDescription(), category.getOffers().size());
            listCategoryDto.add(categoryDto);
        }
        return listCategoryDto;
    }

    //  POST: /api/categories -> pozwala dodać nową kategorię przesyłając do niego odpowiedni obiekt JSON
    @PostMapping("api/categories")
    public void saveCategory(@RequestBody CategoryDto categoryDto) {
        Category category = new Category(categoryDto.getName(), categoryDto.getDescription());
        categoryRepository.save(category);
    }

    //    DELETE: /api/categories/{id}
    @DeleteMapping("api/categories/{id}")
    public void removeCategory(@PathVariable long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            categoryRepository.delete(optional.get());
        }
    }
}
