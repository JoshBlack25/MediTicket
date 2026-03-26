// Matthew Barron 230398863
package za.ac.cput.domain;

public class ClinicStaff {
    private int staffId;
    private String staffName;
    private String staffSurname;
    private String staffRole;
    private String staffEmail;
    private String staffCell;

    // Private constructor
    private ClinicStaff(Builder builder) {
        this.staffId = builder.staffId;
        this.staffName = builder.staffName;
        this.staffSurname = builder.staffSurname;
        this.staffRole = builder.staffRole;
        this.staffEmail = builder.staffEmail;
        this.staffCell = builder.staffCell;
    }

    // Getters
    public int getStaffId() { return staffId; }
    public String getStaffName() { return staffName; }
    public String getStaffSurname() { return staffSurname; }
    public String getStaffRole() { return staffRole; }
    public String getStaffEmail() { return staffEmail; }
    public String getStaffCell() { return staffCell; }
    public String getFullName() {return staffName + " " + staffSurname;}


    // toString
    @Override
    public String toString() {
        return "ClinicStaff {" +
                "staffId=" + staffId +
                ", fullName='" + getFullName() + '\'' +
                ", staffRole='" + staffRole + '\'' +
                ", staffEmail='" + staffEmail + '\'' +
                ", staffCell='" + staffCell + '\'' +
                '}';
    }
    // methods for ClinicStaff
    public void updateContactDetails(String email, String cell) {
        this.staffEmail = email;
        this.staffCell = cell;
    }

    // Builder class
    public static class Builder {
        private int staffId;
        private String staffName;
        private String staffSurname;
        private String staffRole;
        private String staffEmail;
        private String staffCell;

        // Builder setters now return Builder for chaining
        public Builder setStaffId(int staffId) {
            this.staffId = staffId;
            return this;
        }

        public Builder setStaffName(String staffName) {
            this.staffName = staffName;
            return this;
        }

        public Builder setStaffSurname(String staffSurname) {
            this.staffSurname = staffSurname;
            return this;
        }

        public Builder setStaffRole(String staffRole) {
            this.staffRole = staffRole;
            return this;
        }

        public Builder setStaffEmail(String staffEmail1) {
            this.staffEmail = staffEmail1;
            return this;
        }

        public Builder setStaffCell(String staffCell) {
            this.staffCell = staffCell;
            return this;
        }

        // Build method
        public ClinicStaff build() {
            return new ClinicStaff(this);
        }
    }
}