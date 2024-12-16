package ch.tbz.recipe.planner.mapper;

import ch.tbz.recipe.planner.domain.Recipe;
import ch.tbz.recipe.planner.entities.RecipeEntity;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

public class RecipeEntityMapperTest {

    private RecipeEntityMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(RecipeEntityMapper.class);
    }

    @Test
    public void testEntityToDomain() {
        UUID id = UUID.randomUUID();
        RecipeEntity entity = new RecipeEntity();
        entity.setId(id);
        entity.setName("Pasta");

        Recipe domain = mapper.entityToDomain(entity);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(domain.getId()).isEqualTo(id);
        softly.assertThat(domain.getName()).isEqualTo("Pasta");
        softly.assertAll();
    }

    @Test
    public void testDomainToEntity() {
        UUID id = UUID.randomUUID();
        Recipe domain = new Recipe();
        domain.setId(id);
        domain.setName("Pasta");

        RecipeEntity entity = mapper.domainToEntity(domain);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(entity.getId()).isEqualTo(id);
        softly.assertThat(entity.getName()).isEqualTo("Pasta");
        softly.assertAll();
    }
}