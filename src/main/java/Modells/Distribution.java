package Modells;

public class Distribution {

    private String Name;
    private Integer Amount;
    private Double Percentage;

    public Distribution(String name, Integer amount){
        Name = name;
        Amount = amount;
    }

    public String getName() {
        return Name;
    }

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }

     public void setName(String name) {
         Name = name;
     }

     public Double getPercentage() {
         return Percentage;
     }

     public void setPercentage(Double percentage) {
         Percentage = percentage;
     }

     @Override
     public String toString() {
         return "Distribution{" +
                 "Name='" + Name + '\'' +
                 ", Amount=" + Amount +
                 ", Percentage=" + Percentage +
                 '}';
     }
 }
