package com.mattaeng.mattaengapi.repository;

import com.mattaeng.mattaengapi.entity.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {

}
