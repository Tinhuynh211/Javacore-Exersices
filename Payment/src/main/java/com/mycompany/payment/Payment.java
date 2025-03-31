package com.mycompany.payment;

import java.util.Scanner;

public class Payment {
    enum PaymentMethod {
        CREDIT_CARD {
            @Override
            public void processPayment() {
                System.out.println("Dang xu ly thanh toan qua the tin dung");
                System.out.println("Kiem tra thong tin the tin dung");
                System.out.println("Thuc hien thanh toan");
                System.out.println("Thanh toan thanh cong qua the tin dung");
            }
        },
        PAYPAL {
            @Override
            public void processPayment() {
                System.out.println("Dang xu ly thanh toan qua Paypal");
                System.out.println("Dang nhap vao tai khoan Paypal");
                System.out.println("Xac nhan thanh toan");
                System.out.println("Thanh toan thanh cong qua Paypal");
            }
        },
        BANK_TRANSFER {
            @Override
            public void processPayment() {
                System.out.println("Dang xu ly thanh toan qua chuyen khoan ngan hang");
                System.out.println("Kiem tra thong tin tai khoan ngan hang");
                System.out.println("Xac nhan chuyen khoan");
                System.out.println("Thanh toan thanh cong qua chuyen khoan ngan hang");
            }
        };

        public abstract void processPayment();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Menu loop: Keeps displaying the menu until the user chooses Exit
        while (true) {
            // Hien thi menu va yeu cau nguoi dung chon phuong thuc thanh toan
            System.out.println("\nChon phuong thuc thanh toan");
            System.out.println("1. The tin dung");
            System.out.println("2. Paypal");
            System.out.println("3. Chuyen khoan ngan hang");
            System.out.println("4. Thoat");
            System.out.print("Nhap lua chon cua ban (1, 2, 3 hoac 4): ");

            int choice = getValidChoice(scanner);

            // Neu nguoi dung chon Exit (4), thoat khoi chuong trinh
            if (choice == 4) {
                System.out.println("Thoat chuong trinh");
                scanner.close();
                return; // Dung chuong trinh
            }

            PaymentMethod selectedMethod = null;
            switch (choice) {
                case 1:
                    selectedMethod = PaymentMethod.CREDIT_CARD;
                    break;
                case 2:
                    selectedMethod = PaymentMethod.PAYPAL;
                    break;
                case 3:
                    selectedMethod = PaymentMethod.BANK_TRANSFER;
                    break;
                default:
                    System.out.println("Lua chon khong hop le");
                    continue; 
            }

            selectedMethod.processPayment();
        }
    }

    public static int getValidChoice(Scanner scanner) {
        int choice = 0;
        boolean validChoice = false;

        while (!validChoice) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 4) {
                    validChoice = true; 
                } else {
                    System.out.println("Lua chon khong hop le. Vui long nhap lai (1, 2, 3 hoac 4)");
                }
            } else {
                System.out.println("Vui long nhap mot so hop le (1, 2, 3 hoac 4)");
                scanner.next(); 
            }
        }

        return choice;
    }
}
