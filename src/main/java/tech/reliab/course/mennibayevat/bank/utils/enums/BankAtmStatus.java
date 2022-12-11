package tech.reliab.course.mennibayevat.bank.utils.enums;

public enum BankAtmStatus {
    WORKING, NOT_WORKING, NO_MONEY;

    public static BankAtmStatus getRandomStatus() {
        return values()[(int)Math.floor(Math.random() * values().length)];
    }
}
