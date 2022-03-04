package com.company;
import java.util.HashMap;
import java.util.ArrayList;

public class MonthlyReport { // Класс для месячного отчета


    HashMap<String, Integer > itemSumOfOne = new HashMap<>();// название товара - его цена(поштучно)
    HashMap<String, Boolean> itemIsExpense = new HashMap<>();// название товара - трата/доход
    HashMap<String, Integer> itemQuantity = new HashMap<>();//название товара - количество товара

MonthlyReport (String path) {// Конструктор
    ReadCsv readcsv = new ReadCsv(path);
    String[] lineContents = split(readcsv.readingCsv());
     for ( int i=4 ; i<lineContents.length;i +=4) {
         itemSumOfOne.put(lineContents[i], Integer.parseInt(lineContents[i + 3]));
         itemIsExpense.put(lineContents[i], Boolean.parseBoolean(lineContents[i + 1]));
         itemQuantity.put(lineContents[i], Integer.parseInt(lineContents[i + 2]));
     }
}


    private String[] split(String line) { // Сплитовка
        String[] lineContents = line.split(",|\\n");
        return lineContents;
    }
    public void printMonthlyReport (String number) { // Печать месячного отчета
    System.out.println(this.numberMonth(number));
    System.out.println("Самый прибыльный товар: " + this.maxItem());
    System.out.println("Заработок от этого товара составил: " + itemSumOfOne.get( this.maxItem())*itemQuantity.get( this.maxItem()));
    System.out.println("Самый большая трата: " + this.minItem());
    System.out.println("Убыток от этого товара сотавил: " + itemSumOfOne.get(this.minItem())*itemQuantity.get(this.minItem()));




    }
    private String minItem () { // Самая большая трата
        int unitsOfMoney = 0;
        String ItemMin = "";
        for (String item : itemSumOfOne.keySet()) {
            if (itemIsExpense.get(item)) {
                if ( (itemSumOfOne.get(item)*itemQuantity.get(item)>unitsOfMoney ) ) {
                    unitsOfMoney = itemSumOfOne.get(item) * itemQuantity.get(item);
                    ItemMin = item;
                }
            }
        }
        return ItemMin;


        }


    private String maxItem(){ // Самый большой заработок
    int unitsOfMoney = 0;
    String itemMax= "";
    for( String item : itemSumOfOne.keySet()){
        if (!(itemIsExpense.get(item))) {
            if (itemSumOfOne.get(item) * itemQuantity.get(item) > unitsOfMoney) {
                unitsOfMoney = itemSumOfOne.get(item) * itemQuantity.get(item);
                itemMax = item;
            }
        }
    }
    return itemMax;
    }
    private String numberMonth(String number){ // Замена числа на входе на месяц
    if(number.equals("01"))
        return("Январь");
    else if (number.equals("02"))
        return("Февраль");
    else if (number.equals("03"))
        return("Март");
    else if ( number.equals("04"))
        return("Апрель");
    else if (number.equals("05"))
        return("Май");
    else if (number.equals("06"))
        return("Июнь");
    else if (number.equals("07"))
        return("Июль") ;
    else if (number.equals("08"))
        return("Август");
    else if (number.equals("09"))
        return("Сентябрь");
    else if (number.equals("10"))
        return("Октябрь");
    else if (number.equals("11"))
        return("Декабрь");
    else
        System.out.println("Ошибка");
    return null;
    }

}


