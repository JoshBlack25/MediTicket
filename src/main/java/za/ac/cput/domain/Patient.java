//230255639 - Aidan Barends
//Date completed 23 March


package za.ac.cput.domain;

import java.time.LocalDate;

public class Patient {

    private int patientId;
    private String patientName;
    private String patientSurname;
    private String patientCell;
    private String patientEmail;
    private LocalDate patientDOB;

    private Patient(){}



    private Patient(Builder builder){

        this.patientId = builder.patientId;
        this.patientName = builder.patientName;
        this.patientSurname = builder.patientSurname;
        this.patientCell = builder.patientCell;
        this.patientEmail = builder.patientEmail;
        this.patientDOB = builder.patientDOB;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public String getPatientCell() {
        return patientCell;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public LocalDate getPatientDOB() {
        return patientDOB;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", patientSurname='" + patientSurname + '\'' +
                ", patientCell='" + patientCell + '\'' +
                ", patientEmail='" + patientEmail + '\'' +
                ", patientDOB=" + patientDOB +
                '}';
    }

    public static class Builder{

        private int patientId;
        private String patientName;
        private String patientSurname;
        private String patientCell;
        private String patientEmail;
        private LocalDate patientDOB;

        public Builder setPatientId(int patientId){
            this.patientId = patientId;
            return this;
        }

        public Builder setPatientName(String patientName){
            this.patientName = patientName;
            return this;
        }

        public Builder setPatientSurname(String patientSurname){
            this.patientSurname = patientSurname;
            return this;
        }

        public Builder setPatientCell(String patientCell){
            this.patientCell = patientCell;
            return this;
        }

        public Builder setPatientEmail(String patientEmail){
            this.patientEmail = patientEmail;
            return this;
        }

        public Builder setPatientDOB(LocalDate patientDOB){
            this.patientDOB = patientDOB;
            return this;
        }

        public Builder copy(Patient patient){
            this.patientId = patient.patientId;
            this.patientName = patient.patientName;
            this.patientSurname = patient.patientSurname;
            this.patientCell = patient.patientCell;
            this.patientEmail = patient.patientEmail;
            this.patientDOB = patient.patientDOB;

            return this;
        }

        public Patient build(){
            return new Patient(this);
        }
    }
}




