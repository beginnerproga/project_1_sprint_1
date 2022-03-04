package com.company;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean armMonth = false ;
        boolean armYear = false ;
        int kol=0;
        int year = 0;
        HashMap <String, MonthlyReport> allMonthlyReports = new HashMap <>() ;
        YearlyReport yearlyReport= new YearlyReport("") ;
        while (true) {
            printMenu();
            int command = scanner.nextInt();
            switch (command) {
                case(1):
                    if (year==0){
                    System.out.println("За какой год вы бы хотели получить отчет");
                    year = scanner.nextInt() ;}
                    if (year>=1995 && year<=2022){
                    System.out.println("Сколько месячных отчетов готово? Введите их количество.");
                     kol =  scanner.nextInt();
                    if (!armMonth) {
                        for (int i = 1; i <= kol; i++) {
                            if (kol < 10)
                                allMonthlyReports.put(("0" +i), readMonthlyReport("m." + year + "0" + i + ".csv"));
                            else if (kol >= 10)
                                allMonthlyReports.put(("0" +i), readMonthlyReport("m." + year + i + ".csv"));
                            else if (kol <= 0 && kol >= 12) {
                                System.out.println("Вы ошиблись. Повторите попытку или выберите другую команду.");
                                break;
                            }
                        }
                        armMonth = true;
                    }
                    else {
                        System.out.println("Считывание всех файлов уже произошло") ;
                        System.out.println("Выберете другую команду") ;
                    }}
                    else
                            System.out.println("Вы ошиблись. Повторите попытку или выберите другую команду.");
                    break;
                case(2):
                    if (!armYear) {
                        if (year==0) {
                            System.out.println("За какой год вы бы хотели получить отчет");
                            year = scanner.nextInt();
                        }
                            YearlyReport yearlyReport_1 = new YearlyReport("C:\\y."+year+".csv");
                            yearlyReport = yearlyReport_1;
                            armYear = true;
                    }
                    else {
                        System.out.println("Считывание всех файлов уже произошло") ;
                        System.out.println("Выберете другую команду") ;

                    }
                    break;
                case(3):
                  try{
                    dataReconciliation(allMonthlyReports,yearlyReport,kol) ;}
                    catch (NullPointerException e){
                    System.out.println("Вы не считали либо годовой отчет, либо месячные. Повторите попытку.");
                }
                    break;
                case(4):
                    for(int i=1; i <=kol;i++ ) {
                        allMonthlyReports.get("0" +i).printMonthlyReport("0" + i);
                    }
                    break;
                    case(5):
                        try {
                            yearlyReport.printYearlyReport(year);
                        }
                        catch (NullPointerException e) {
                            System.out.println("Вы не считали либо годовой отчет, либо месячные. Повторите попытку.");
                        }
                    break;
                case(0):
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Вы ввели неккоретное значение, повторите попытку") ;
            }
        }
    }
    public static void printMenu() { //Вывод меню
        System.out.println("Досупные команды:");
        System.out.println("1. Считать все месячные отсчеты.");
        System.out.println("2. Считать годовой отчет.");
        System.out.println("3. Сверить отчеты.");
        System.out.println("4. Вывести информацию о всех месячных отчётах.");
        System.out.println("5. Вывести информацию о годовом отчёте.");
        System.out.println("0. Для выхода из программы.");

    }
    public static MonthlyReport readMonthlyReport(String path) { // чтение месячного отчета
        MonthlyReport mothlyReport = new MonthlyReport(path);
        return mothlyReport;

    }
    public static void dataReconciliation(HashMap <String, MonthlyReport> allMonthlyReports, YearlyReport yearlyReport, int kol){ // Сверка месячных и годового отчетов
        long[] expensesMonth = new  long [kol] ;
        long[] earningsMonth = new long [kol];
        long[] expensesYear = new long[kol];
        long[] earningsYear = new long[kol];
        boolean arm = false;

        for (int i = 1 ; i<=kol; i++) {
            for (String item : allMonthlyReports.get("0" + i).itemIsExpense.keySet()) {
                if (allMonthlyReports.get("0"+i).itemIsExpense.get(item)) {
                    expensesMonth[i-1] = expensesMonth[i-1] + allMonthlyReports.get("0"+i).itemSumOfOne.get(item) * allMonthlyReports.get("0"+i).itemQuantity.get(item);
                } else
                    earningsMonth[i-1] = earningsMonth[i-1] + allMonthlyReports.get("0"+i).itemSumOfOne.get(item) * allMonthlyReports.get("0"+i).itemQuantity.get(item);
            }
            expensesYear[i-1] = expensesYear[i-1] +yearlyReport.monthlyExpensesTrue.get("0"+i);
            earningsYear[i-1] = earningsYear[i-1] + yearlyReport.monthlyExpensesFalse.get("0"+i);
            }
        for (int i=0 ; i <kol;i ++){
            if (expensesYear[i]!=expensesMonth[i]){
                System.out.println("Расхождение в тратах за " + (i+1) + " месяц");
                arm=true;
            }
            if(earningsMonth[i] != earningsYear[i]){
                System.out.println("Расхождение в прибыле за "+ (i+1) + " месяц");
                arm = true;
            }
        }
        if (!arm)
            System.out.println("Успешное завершение операции,расхождений нет") ;
    }
    }



