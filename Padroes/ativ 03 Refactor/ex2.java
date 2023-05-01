import java.util.Date;

public class Logger
{
    public boolean isLogMessageValid(String message){
        boolean isValid = !String.isNullOrEmpty(message);
        return isValid;
    }


}

class DateUtil
{
    boolean isAfter(Date date1, Date date2){
        
    }

    int differenceInDays(Date date1, Date date2){

    }

}

double amount = order.getAmount();
double price = product.getPrice();
double discount = 0;

if(amount>10){
    discount = price * 0.9 ;
}

double finalPrice = (price - discount) * amount;



var miles  = 0.0;
if(!car.HasFuel() || !car.EngineWorks()){
    return miles;
}else{
    var startingMiles = car.Miles;

    car.Drive();

    var endingMiles = car.Miles;

    return miles = endingMiles - startingMiles;
}
}

