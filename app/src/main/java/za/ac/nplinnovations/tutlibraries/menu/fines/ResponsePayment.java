package za.ac.nplinnovations.tutlibraries.menu.fines;

public class ResponsePayment {
    private Integer ID;
    private String Category;
    private String ProductName;
    private String CardNumber;
    private String UserName;
    private String UserSurname;
    private double Amount;
    private String Date;

    public ResponsePayment(String category, String productName, String cardNumber, String userName, String userSurname, double amount, String date) {
        Category = category;
        ProductName = productName;
        CardNumber = cardNumber;
        UserName = userName;
        UserSurname = userSurname;
        Amount = amount;
        Date = date;
    }

    public Integer getID() {
        return ID;
    }

    public String getCategory() {
        return Category;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public String getUserName() {
        return UserName;
    }

    public String getUserSurname() {
        return UserSurname;
    }

    public double getAmount() {
        return Amount;
    }

    public String getDate() {
        return Date;
    }
}
