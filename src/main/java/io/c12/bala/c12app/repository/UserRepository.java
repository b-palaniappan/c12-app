package io.c12.bala.c12app.repository;

import io.c12.bala.c12app.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
}
