package com.ivan.weather.station.persistence.repository.impl;

import com.ivan.weather.station.persistence.entity.Role;
import com.ivan.weather.station.persistence.entity.User;
import com.ivan.weather.station.persistence.repository.api.RoleRepository;
import com.ivan.weather.station.persistence.repository.api.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {

    private final RoleRepository roleRepository;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory, RoleRepository roleRepository) {
        super(sessionFactory);
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(User user) {
        List<Role> roles = roleRepository.findAll();
        if (count() == 0) {
            user.setRoles(roles);
            for (Role role : roles) {
                role.getUsers().add(user);
            }
        } else {
            user.setRoles(roles.stream()
                    .filter(role -> role.getRoleType().equals(Role.RoleType.USER))
                    .collect(Collectors.toList()));
            for (Role role : roles) {
                role.getUsers().add(user);
            }
        }
        super.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return executeInTransaction(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
            Root<User> root = criteriaQuery.from(getEntityClass());
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), username));
            return getOrEmpty(session, criteriaQuery);
        });
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

}
