package com.edw.repository;

import com.edw.bean.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 *  com.edw.repository.UserRepository
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 10 Nov 2025 9:20
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
