/* IRepository.java
   Generic repository interface for CRUD operations
   Author: Joshua A (230317693)
   Date: 22 March 2026
*/

package za.ac.cput.repository;

import java.util.List;

public interface IRepository<T, ID> {

    T create (T enitity);
    T read(ID id);
    T update(T entity);
    void delete(ID id);
    List<T> getAll();
}
