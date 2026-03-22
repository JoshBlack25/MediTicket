/* IRepository.java
   Generic repository interface for all entities
   Author: Abdullahi (your student number)
   Date: 22 March 2026
*/
package za.ac.cput.repository;

public interface IRepository<T, ID> {
    T create(T t);
    T read(ID id);
    T update(T t);
    boolean delete(ID id);
}
