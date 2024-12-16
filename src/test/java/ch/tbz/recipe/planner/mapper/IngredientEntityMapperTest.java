package ch.tbz.recipe.planner.mapper;

import ch.tbz.recipe.planner.domain.Ingredient;
import ch.tbz.recipe.planner.entities.IngredientEntity;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;

public class IngredientEntityMapperTest {

    private IngredientEntityMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(IngredientEntityMapper.class);
    }

    @Test
    public void testEntityToDomain() {
        UUID id = UUID.randomUUID();
        IngredientEntity entity = new IngredientEntity();
        entity.setId(id);
        entity.setName("Sugar");

        Ingredient domain = mapper.entityToDomain(entity);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(domain.getId()).isEqualTo(id);
        softly.assertThat(domain.getName()).isEqualTo("Sugar");
        softly.assertAll();
    }

    @Test
    public void testDomainToEntity() {
        UUID id = UUID.randomUUID();
        Ingredient domain = new Ingredient();
        domain.setId(id);
        domain.setName("Sugar");

        IngredientEntity entity = mapper.domainToEntity(domain);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(entity.getId()).isEqualTo(id);
        softly.assertThat(entity.getName()).isEqualTo("Sugar");
        softly.assertAll();
    }

    @Test
    public void testEntitiesToDomains() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        IngredientEntity entity1 = new IngredientEntity();
        entity1.setId(id1);
        entity1.setName("Sugar");

        IngredientEntity entity2 = new IngredientEntity();
        entity2.setId(id2);
        entity2.setName("Salt");

        List<Ingredient> domains = mapper.entitiesToDomains(asList(entity1, entity2));

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(domains).hasSize(2);
        softly.assertThat(domains.get(0).getId()).isEqualTo(id1);
        softly.assertThat(domains.get(0).getName()).isEqualTo("Sugar");
        softly.assertThat(domains.get(1).getId()).isEqualTo(id2);
        softly.assertThat(domains.get(1).getName()).isEqualTo("Salt");
        softly.assertAll();
    }

    @Test
    public void testDomainsToEntities() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        Ingredient domain1 = new Ingredient();
        domain1.setId(id1);
        domain1.setName("Sugar");

        Ingredient domain2 = new Ingredient();
        domain2.setId(id2);
        domain2.setName("Salt");

        List<IngredientEntity> entities = mapper.domainsToEntities(asList(domain1, domain2));

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(entities).hasSize(2);
        softly.assertThat(entities.get(0).getId()).isEqualTo(id1);
        softly.assertThat(entities.get(0).getName()).isEqualTo("Sugar");
        softly.assertThat(entities.get(1).getId()).isEqualTo(id2);
        softly.assertThat(entities.get(1).getName()).isEqualTo("Salt");
        softly.assertAll();
    }
}