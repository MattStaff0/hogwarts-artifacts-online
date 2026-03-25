package edu.tcu.cs.hogwartsartifactsonline.artifact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//  annotation that is optional
@Repository
public interface ArtifactRepository extends JpaRepository<Artifact, String> {
//    only needs to extend the class we have made so Spring implements it
}
