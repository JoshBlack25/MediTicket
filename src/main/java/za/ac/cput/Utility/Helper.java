//230255639 - Aidan Barends
//Date Completed 24 March

package za.ac.cput.Utility;

import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDate;

public class Helper {

    public static boolean isNullOrEmpty(String str) {
        if((str == null) || (str.isEmpty())){
            return true;
        }
        return false;
    }

    public static boolean isValidEmail(String patientEmail){
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(patientEmail);
    }

    public static boolean isValidId(int patientId){
        return patientId > 0;
    }

    public static boolean isValidDate(LocalDate patientDOB){
       if(patientDOB == null){
           return false;
       }
       return !patientDOB.isAfter(LocalDate.now());
    }
}






