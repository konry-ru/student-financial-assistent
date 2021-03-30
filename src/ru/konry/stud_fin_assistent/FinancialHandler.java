package ru.konry.stud_fin_assistent;

public class FinancialHandler {
    public static void main(String[] args) {
        handleFinance();
    }

    static void handleFinance() {
        prepareFinancialDocs();
        sendDocsToFinDepartment();
    }

    static void prepareFinancialDocs() {
        System.out.println("Подготовка финансовых документов...");
    }

    static void sendDocsToFinDepartment() {
        System.out.println("Отправка документов в финансовую службу...");
    }
}


