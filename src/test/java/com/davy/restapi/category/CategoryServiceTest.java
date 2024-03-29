package com.davy.restapi.category;

import com.davy.restapi.category.dto.CategoryDTO;
import com.davy.restapi.category.dto.CategoryRequestDTO;
import com.davy.restapi.category.dto.CategoryTryDetailsDTO;
import com.davy.restapi.category.entity.CategoryEntity;
import com.davy.restapi.shared.TestContainer;
import com.davy.restapi.shared.service.CrudService;
import com.davy.restapi.subcategory.dto.SubCategoryDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CategoryServiceTest extends TestContainer {

    @Autowired
    private CrudService<CategoryEntity, CategoryRequestDTO> categoryService;

    @DisplayName("Get all categories")
    @Test
    @Order(1)
    void shouldGetAllCategories(){
        List<CategoryDTO> categories = (List<CategoryDTO>) categoryService.findAll();
        assertThat(categories).hasSize(10);
    }

    @DisplayName("Get category by id")
    @Test
    @Order(2)
    void shouldGetCategoryById(){
        CategoryTryDetailsDTO category = (CategoryTryDetailsDTO) categoryService.findById(3L);
        assertThat(category.getId()).isEqualTo(3L);
        assertThat(category.getName()).isEqualTo("Computer & Electronics");

        List<SubCategoryDTO> expectedSubCategories = subCategoryList();
        List<SubCategoryDTO> actualSubCategories = category.getSubCategories();

        assertThat(actualSubCategories).hasSize(expectedSubCategories.size());

        for (int i = 0; i < expectedSubCategories.size(); i++) {
            assertThat(actualSubCategories.get(i).getId()).isEqualTo(expectedSubCategories.get(i).getId());
            assertThat(actualSubCategories.get(i).getName()).isEqualTo(expectedSubCategories.get(i).getName());
        }
    }

    @DisplayName("Save category")
    @Test
    @Order(3)
    @Transactional
    void shouldSaveCategory() throws IOException {
        Optional<CategoryRequestDTO> request = Optional.of(new CategoryRequestDTO(
                "TestCategory Saved",
                null
        ));
        categoryService.save(request.get());
        CategoryTryDetailsDTO savedCategory = (CategoryTryDetailsDTO) categoryService.findById(11L);
        assertNotNull(savedCategory);
        assertEquals(11L, savedCategory.getId());
        assertEquals("TestCategory Saved", savedCategory.getName());
    }

    @DisplayName("Update category")
    @Test
    @Order(4)
    void shouldUpdateCategory(){
        Optional<CategoryRequestDTO> request = Optional.of(new CategoryRequestDTO(
                "TestCategory Updated",
                null
        ));
        categoryService.updateById(3L, request.get());
        CategoryTryDetailsDTO updatedCategory = (CategoryTryDetailsDTO) categoryService.findById(3L);
        assertNotNull(updatedCategory);
        assertThat(updatedCategory.getId()).isEqualTo(3L);
        assertThat(updatedCategory.getName()).isEqualTo("TestCategory Updated");

        List<SubCategoryDTO> expectedSubCategories = subCategoryList();
        List<SubCategoryDTO> actualSubCategories = updatedCategory.getSubCategories();

        assertThat(actualSubCategories).hasSize(expectedSubCategories.size());

        for (int i = 0; i < expectedSubCategories.size(); i++) {
            assertThat(actualSubCategories.get(i).getId()).isEqualTo(expectedSubCategories.get(i).getId());
            assertThat(actualSubCategories.get(i).getName()).isEqualTo(expectedSubCategories.get(i).getName());
        }
    }


    private List<SubCategoryDTO> subCategoryList(){
        var subCategories = new ArrayList<SubCategoryDTO>();
        subCategories.add(new SubCategoryDTO(12L, "Computer & Accessories"));
        subCategories.add(new SubCategoryDTO(13L, "Sound and Vision"));
        subCategories.add(new SubCategoryDTO(14L, "Photo & Videocameras"));
        subCategories.add(new SubCategoryDTO(15L, "Telephony & Tables"));
        subCategories.add(new SubCategoryDTO(16L, "Smartwatches & Accessories"));
        subCategories.add(new SubCategoryDTO(17L, "Appliances"));
        subCategories.add(new SubCategoryDTO(18L, "Kitchen appliances"));
        subCategories.add(new SubCategoryDTO(19L, "Personal care devices"));
        return subCategories;
    }
}
