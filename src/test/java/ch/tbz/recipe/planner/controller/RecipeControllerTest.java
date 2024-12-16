package ch.tbz.recipe.planner.controller;

import ch.tbz.recipe.planner.domain.Recipe;
import ch.tbz.recipe.planner.mapper.RecipeEntityMapper;
import ch.tbz.recipe.planner.repository.RecipeRepository;
import ch.tbz.recipe.planner.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @MockBean
    private RecipeEntityMapper recipeEntityMapper;

    @MockBean
    private RecipeRepository recipeRepository;

    @Test
    public void testGetAllRecipes() throws Exception {
        Recipe recipe = new Recipe(UUID.randomUUID(), "Test Recipe", "Test Description", "Test Image", List.of());
        when(recipeService.getRecipes()).thenReturn(List.of(recipe));

        mockMvc.perform(get("/api/recipes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Recipe"));
    }

    @Test
    public void testGetRecipeById() throws Exception {
        UUID recipeId = UUID.randomUUID();
        Recipe recipe = new Recipe(recipeId, "Test Recipe", "Test Description", "Test Image", List.of());
        when(recipeService.getRecipeById(recipeId)).thenReturn(recipe);

        mockMvc.perform(get("/api/recipes/recipe/" + recipeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Recipe"));
    }

    @Test
    public void testAddRecipe() throws Exception {
        Recipe recipe = new Recipe(UUID.randomUUID(), "Test Recipe", "Test Description", "Test Image", List.of());
        when(recipeService.addRecipe(any(Recipe.class))).thenReturn(recipe);

        mockMvc.perform(post("/api/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Recipe\",\"description\":\"Test Description\",\"image\":\"Test Image\",\"ingredients\":[]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Recipe"));
    }
}