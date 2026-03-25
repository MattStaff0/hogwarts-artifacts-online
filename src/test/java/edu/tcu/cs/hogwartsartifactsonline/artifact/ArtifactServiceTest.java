package edu.tcu.cs.hogwartsartifactsonline.artifact;

import edu.tcu.cs.hogwartsartifactsonline.wizard.Wizard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ArtifactServiceTest {
    @Mock
    ArtifactRepository artifactRepository;

    @InjectMocks
    ArtifactService artifactService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindArtifactById() {
//        Given: prepare fake data. Define behavior of mock obj artifactRepo
        Artifact a = new Artifact();
        a.setId("1250808601744904192");
        a.setName("Invisibility Cloak");
        a.setDescription("An invisibility cloak is used to make the wearer invisible.");
        a.setImageUrl("ImageUrl");

        Wizard w = new Wizard();
        w.setName("Harry Potter");
        w.setId(2);

        a.setOwner(w);

        given(artifactRepository.findById("1250808601744904192")).willReturn(Optional.of(a));

//        When: Act on the target behavior, when steps should cover the method to be tested
        Artifact retuned = artifactService.findArtifactById("1250808601744904192");

//        Then: Assert expected outcomes
        assertEquals(a.getId(), retuned.getId());
        assertEquals(a.getName(), retuned.getName());
        assertEquals(a.getDescription(), retuned.getDescription());
        assertEquals(a.getImageUrl(), retuned.getImageUrl());
        verify(artifactRepository, times(1)).findById("1250808601744904192");

    }

    @Test
    void testFindByIdNotFound() {
//        Given
        given(artifactRepository.findById(any(String.class))).willReturn(Optional.empty());

//        When
        Throwable thrown = catchThrowable(() -> {
            artifactService.findArtifactById("1250808601744904192");
        });

//        Then
        assertThat(thrown).isInstanceOf(ArtifactNotFoundException.class)
                .hasMessage("Could not find artifact with Id 1250808601744904192");
        verify(artifactRepository, times(1)).findById("1250808601744904192");

    }
}