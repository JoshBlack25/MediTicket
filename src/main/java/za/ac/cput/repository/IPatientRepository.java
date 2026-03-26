//230255639 - Aidan Barends
//Date Completed 25 March

package za.ac.cput.repository;

import za.ac.cput.domain.Patient;

import java.util.List;

public interface IPatientRepository extends IRepository <Patient, Integer> {

    List<Patient>getAll();
}