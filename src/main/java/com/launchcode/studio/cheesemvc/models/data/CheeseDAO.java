package com.launchcode.studio.cheesemvc.models.data;

import com.launchcode.studio.cheesemvc.models.Cheese;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CheeseDAO extends CrudRepository<Cheese, Integer> {
  //Custom query methods go in here
  default void deleteMany(int[] cheeseIDs){
    for(int cheeseID : cheeseIDs){
      this.delete(cheeseID);
    }
  }

  List<Cheese> findByCategoryId(long categoryID);
}
