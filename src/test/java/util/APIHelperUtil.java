package util;

import pojos.Bookingdates;
import pojos.CreateABooking;

import java.util.List;

public class APIHelperUtil {

    //API Paths
    public static final String CREATE_A_BOOKING = "/booking";
    public static final String GET_A_BOOKING = "/booking/";

    //A method to populate Create A Booking POJO
    public static CreateABooking buildAddAMemeRequest(List<List<String>> listOfData){
        CreateABooking createABooking = new CreateABooking();
        createABooking.setFirstname(listOfData.get(1).get(1)+RandomSuffix(99999));
        createABooking.setLastname(listOfData.get(2).get(1)+RandomSuffix(99999));
        createABooking.setTotalprice(Integer.valueOf(listOfData.get(3).get(1)));
        createABooking.setDepositpaid(Boolean.valueOf(listOfData.get(4).get(1)));
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin(listOfData.get(5).get(1));
        bookingdates.setCheckout(listOfData.get(6).get(1));
        createABooking.setBookingdates(bookingdates);
        createABooking.setAdditionalneeds(listOfData.get(7).get(1));
        return createABooking;
    }

    public static int RandomSuffix(int range){
        return (int)(Math.random() * range) + 1;
    }
}
