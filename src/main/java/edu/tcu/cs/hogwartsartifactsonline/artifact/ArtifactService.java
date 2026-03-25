package edu.tcu.cs.hogwartsartifactsonline.artifact;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

//  annotation
//  service depends on the repo
@Service
@Transactional
public class ArtifactService {

    private final ArtifactRepository artifactRepository;

    public ArtifactService(ArtifactRepository artifactRepository) {
        this.artifactRepository = artifactRepository;
    }

    public Artifact findArtifactById(String artifactId){
        return this.artifactRepository.findById(artifactId)
                .orElseThrow(() -> new ArtifactNotFoundException(artifactId));
    }

}
