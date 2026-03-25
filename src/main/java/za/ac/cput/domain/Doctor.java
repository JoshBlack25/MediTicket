/* Doctor.java
   Domain class for doctor using Builder Pattern
   Author: Joshua A (your student number)
   Date: 25 March 2026
*/
package za.ac.cput.domain;

public class Doctor {

    private int doctorId;
    private String doctorName;
    private String doctorSurname;
    private String doctorSpecialty;
    private String doctorCell;
    private String doctorEmail;

    public int getDoctorId() {
        return doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorSurname() {
        return doctorSurname;
    }

    public String getDoctorSpecialty() {
        return doctorSpecialty;
    }

    public String getDoctorCell() {
        return doctorCell;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", doctorName='" + doctorName + '\'' +
                ", doctorSurname='" + doctorSurname + '\'' +
                ", doctorSpecialty='" + doctorSpecialty + '\'' +
                ", doctorCell='" + doctorCell + '\'' +
                ", doctorEmail='" + doctorEmail + '\'' +
                '}';
    }

    private Doctor(Builder builder) {
        this.doctorId        = builder.doctorId;
        this.doctorName      = builder.doctorName;
        this.doctorSurname   = builder.doctorSurname;
        this.doctorSpecialty = builder.doctorSpecialty;
        this.doctorCell      = builder.doctorCell;
        this.doctorEmail     = builder.doctorEmail;
    }

    public static class Builder {
        private int doctorId;
        private String doctorName;
        private String doctorSurname;
        private String doctorSpecialty;
        private String doctorCell;
        private String doctorEmail;

        public Builder setDoctorId(int doctorId) {
            this.doctorId = doctorId;
            return this;
        }

        public Builder setDoctorName(String doctorName) {
            this.doctorName = doctorName;
            return this;
        }

        public Builder setDoctorSurname(String doctorSurname) {
            this.doctorSurname = doctorSurname;
            return this;
        }

        public Builder setDoctorSpecialty(String doctorSpecialty) {
            this.doctorSpecialty = doctorSpecialty;
            return this;
        }

        public Builder setDoctorCell(String doctorCell) {
            this.doctorCell = doctorCell;
            return this;
        }

        public Builder setDoctorEmail(String doctorEmail) {
            this.doctorEmail = doctorEmail;
            return this;
        }

        public Doctor build() {
            return new Doctor(this);
        }
    }
}
