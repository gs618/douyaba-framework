package com.github.douyaba.exception.core.repository;

import com.github.douyaba.exception.core.entity.ExceptionDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author s.c.gao
 */
public interface ExceptionRepository extends JpaRepository<ExceptionDO, String> {
}
