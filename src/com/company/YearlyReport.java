package com.company;
import java.util.HashMap;

public class YearlyReport { // Класс для годового отчета
    HashMap <String, Long> monthlyExpensesTrue = new HashMap<String,Long>() ; // хэшмэп месяц с заработками - сумма
    HashMap <String, Long> monthlyExpensesFalse = new HashMap <String, Long>(); // хэшмэп месяц с убытками - сумма
    HashMap <String, Boolean> monthIsExpense = new HashMap<String, Boolean> (); // хэшмэп название месяца - это убыток/заработок


YearlyReport (String path) { // конструктор
    if (path!="") {
        ReadCsv readcsv = new ReadCsv(path);
        String[] lineContents = split(readcsv.readingCsv());
        for (int i = 3; i < lineContents.length; i += 3) {
            monthIsExpense.put(lineContents[i], Boolean.parseBoolean(lineContents[i + 2]));
            if (monthIsExpense.get(lineContents[i]) == true) {
                monthlyExpensesTrue.put(lineContents[i], Long.parseLong(lineContents[i + 1]));
            } else {
                monthlyExpensesFalse.put(lineContents[i],Long.parseLong(lineContents[i + 1]));
            }
            }
        }
    }

    public void printYearlyReport( int year){
    System.out.println("Год: " + year);
    printProfitPerMonth();
    System.out.println("Средний расход за все месяцы в году: "+ averageConsumption());
    System.out.println("Средний доход за все месяцы в году: " + averageIncome());

    }

    private  void printProfitPerMonth () {
    long profit;
    for (String month : monthIsExpense.keySet()) {
        profit =  monthlyExpensesFalse.get(month) - monthlyExpensesTrue.get(month)  ;
        System.out.println("Прибыль - " + numberMonth(month)+ ": " + profit );
    }
    }
    private double averageIncome() {
    double sum = 0;
    int i=0;
    for (String month:monthIsExpense.keySet()){

            sum += monthlyExpensesFalse.get(month);
            i++;

        }
        sum = sum / i ;
        return sum ;

    }

    private double  averageConsumption () {
    double sum=0;
    int i=0;
    for (String month:monthIsExpense.keySet()){

         sum += monthlyExpensesTrue.get(month);
         i++;

    }
    sum = sum / i ;
    return sum ;
    }


    public String[] split(String line) { // сплитовка
        String[] lineContents = line.split(",");
        return lineContents;
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
