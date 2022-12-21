package com.mysite.sbb.Repository;

import com.mysite.sbb.Domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
