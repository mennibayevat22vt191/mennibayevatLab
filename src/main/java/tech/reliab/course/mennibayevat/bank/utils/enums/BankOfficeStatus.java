package tech.reliab.course.mennibayevat.bank.utils.enums;

public enum BankOfficeStatus {
    WORKING, NOT_WORKING;

    public static BankOfficeStatus getRandomStatus() {
        return values()[(int) Math.floor(Math.random() * values().length)];
    }
}
