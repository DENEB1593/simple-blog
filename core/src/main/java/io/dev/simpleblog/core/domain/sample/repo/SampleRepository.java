package io.dev.simpleblog.core.domain.sample.repo;

import io.dev.simpleblog.core.domain.sample.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Long> {

}
