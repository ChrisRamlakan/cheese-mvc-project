package com.launchcode.studio.cheesemvc.models.data;

import com.launchcode.studio.cheesemvc.models.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MenuDAO extends CrudRepository<Menu, Long> {
}
