package Capt208E;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full file path: ");
        String path = sc.nextLine();
        System.out.println("Enter salary: ");
        Double salary = sc.nextDouble();

        try ( BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<Employee> emp = new ArrayList<>();
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                emp.add(new Employee(fields[0], fields[1],
                        Double.parseDouble(fields[2])));
                line = br.readLine();
            }

            Comparator<String> comp = (s1, s2) -> s1.toUpperCase()
                    .compareTo(s1.toUpperCase());
            List<String> emails = emp.stream()
                    .filter(p -> p.getSalary() > salary)
                    .map(p -> p.getEmail())
                    .sorted(comp.reversed())
                    .collect(Collectors.toList());

            emails.forEach(System.out::println);

            EmployeeService es = new EmployeeService();

            double sum = es.filteredSum(emp, p -> p.getName().charAt(0) == 'M');
            System.out.println("Sum of salary of people whose name "
                    + "starts with 'M' = " + String.format("%.2f", sum));

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();

    }

}
