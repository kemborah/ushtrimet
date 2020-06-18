package com.company;
import java.util.HashMap;
import java.util.Map;

public class Rules {
    private String rregull1;
    private String rregull2;
    private String rregull3;
    private String rregull4;
    private Map<Double, Double> discountPerPrice = new HashMap<>();
    private Map<Double, Double> percentageDiscountPerPrice= new HashMap<>();
    private Map<Double, Double> discountPerBundle = new HashMap<>();
    private Map<Double, Double> discountPerCount= new HashMap<>();
    private Map<Integer, Integer> rulesOrder= new HashMap<>();

    public Rules() {
    }

    public Rules(String rregull1, String rregull2, String rregull3, String rregull4){
        this.rregull1=rregull1;
        this.rregull2=rregull2;
        this.rregull3=rregull3;
        this.rregull4=rregull4;
        setRules();
    }


    public Map<Integer, Integer> getRulesOrder() {
        return rulesOrder;
    }

    public Map<Double, Double> getDiscountPerPrice() {
        return discountPerPrice;
    }

    public Map<Double, Double> getPercentageDiscountPerPrice() {
        return percentageDiscountPerPrice;
    }

    public Map<Double, Double> getDiscountPerBundle() {
        return discountPerBundle;
    }

    public Map<Double, Double> getDiscountPerCount() {
        return discountPerCount;
    }

    public void setRules(){
        setRule1();
        setRule2();
        setRule3();
        setRule4();
    }

    public void setRule1() {
        String splitArs[];
        String[] ars=rregull1.split(";");
        splitArs= ars[0].split(":");
        rulesOrder.put(1,Integer.parseInt(splitArs[1]));
        for (int i=0;i<=ars.length-1;i++) {
            splitArs= ars[i].split(":");
            discountPerPrice.put(Double.parseDouble(splitArs[0]),Double.parseDouble(splitArs[1]));
        }
    }

    public void setRule2() {
        String splitArs[];
        String[] ars=rregull2.split(";");
        splitArs= ars[0].split(":");
        rulesOrder.put(2,Integer.parseInt(splitArs[1]));
        for (int i=0;i<=ars.length-1;i++) {
            splitArs= ars[i].split(":");
            percentageDiscountPerPrice.put(Double.parseDouble(splitArs[0]),Double.parseDouble(splitArs[1]));
            //System.out.println(percentageDiscountPerPrice);
        }
    }

    public void setRule3() {
        String splitArs[];
        String[] ars=rregull3.split(";");
        splitArs= ars[0].split(":");
        rulesOrder.put(3,Integer.parseInt(splitArs[1]));
        for (int i=0;i<=ars.length-1;i++) {
            splitArs= ars[i].split(":");
            discountPerBundle.put(Double.parseDouble(splitArs[0]),Double.parseDouble(splitArs[1]));
            //System.out.println(discountPerBundle);
        }
    }

    public void setRule4() {
        String splitArs[];
        String[] ars=rregull4.split(";");
        splitArs= ars[0].split(":");
        rulesOrder.put(4,Integer.parseInt(splitArs[1]));
        for (int i=1;i<=ars.length-1;i++) {
            splitArs= ars[i].split(":");
            discountPerCount.put(Double.parseDouble(splitArs[0]),Double.parseDouble(splitArs[1]));
        }
        //rulesOrder[4]=discountPerCount.get();

    }

    public void setRradha(){

    }

}
