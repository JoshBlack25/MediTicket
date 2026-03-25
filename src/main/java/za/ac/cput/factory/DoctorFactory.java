/* DoctorFactory.java
   Factory class for creating Doctor objects
   Author: Joshua A (your student number)
   Date: 25 March 2026
*/
package za.ac.cput.factory;

import za.ac.cput.domain.Doctor;

public class DoctorFactory {

    public static Doctor createDoctor(int doctorId, String doctorName, String doctorSurname,
                                      String doctorSpecialty, String doctorCell, String doctorEmail) {
        return new Doctor.Builder()
                .setDoctorId(doctorId)
                .setDoctorName(doctorName)
                .setDoctorSurname(doctorSurname)
                .setDoctorSpecialty(doctorSpecialty)
                .setDoctorCell(doctorCell)
                .setDoctorEmail(doctorEmail)
                .build();
    }
}
