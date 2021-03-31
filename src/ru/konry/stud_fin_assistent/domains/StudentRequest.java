package ru.konry.stud_fin_assistent.domains;

public class StudentRequest {
    private String hFirstName;
    private String hLastName;
    private String wFirstName;
    private String wLastName;

    public String gethFirstName() {
        return hFirstName;
    }

    public void sethFirstName(String hFirstName) {
        this.hFirstName = hFirstName;
    }

    public String gethLastName() {
        return hLastName;
    }

    public void sethLastName(String hLastName) {
        this.hLastName = hLastName;
    }

    public String getwFirstName() {
        return wFirstName;
    }

    public void setwFirstName(String wFirstName) {
        this.wFirstName = wFirstName;
    }

    public String getwLastName() {
        return wLastName;
    }

    public void setwLastName(String wLastName) {
        this.wLastName = wLastName;
    }

}
