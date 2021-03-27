package za.ac.nplinnovations.tutlibraries.menu.fines;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InterfacePost {
    @POST("api/contactus")
    Call<Void> sendContactUsMessage(@Query("name") String name, @Query("email") String email,
                                    @Query("subject") String subject, @Query("message") String message);


    @POST("api/payments")
    Call<ResponsePayment> processPayment(@Query("productname") String productName,
                                         @Query("category") String category, @Query("cardno") String cardno,
                                         @Query("cvv") String cvv, @Query("expdate") String expdate, @Query("name") String name,
                                         @Query("surname") String surname, @Query("contactNo") String contactNo, @Query("amount") double amount);

}
