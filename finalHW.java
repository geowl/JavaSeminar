import java.util.*;
import java.util.stream.Collectors;

class Laptop {
    String brand;
    int ram;
    int hdd;
    String os;
    String color;

    public Laptop(String brand, int ram, int hdd, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", ram=" + ram +
                ", hdd=" + hdd +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

public class finalHW {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell", 16, 512, "Windows", "Black"));
        laptops.add(new Laptop("Apple", 8, 256, "MacOS", "Silver"));
        laptops.add(new Laptop("Lenovo", 32, 1024, "Windows", "Black"));
        laptops.add(new Laptop("MSI", 8, 256, "Windows", "Black"));
        laptops.add(new Laptop("HP", 4, 128, "Linux", "Silver"));
        laptops.add(new Laptop("Huawei", 16, 512, "Windows", "Silver"));

        Map<String, Object> filters = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите критерии для фильтрации:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        System.out.println("0 - Завершить выбор");

        int choice;
        while (true) {
            choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            switch (choice) {
                case 1:
                    System.out.println("Минимальный объем ОЗУ?");
                    int ramFilter = scanner.nextInt();
                    filters.put("ram", ramFilter);
                    break;
                case 2:
                    System.out.println("Минимальный объем ЖД");
                    int hddFilter = scanner.nextInt();
                    filters.put("hdd", hddFilter);
                    break;
                case 3:
                    System.out.println("Операционная система?");
                    String osFilter = scanner.next();
                    filters.put("os", osFilter);
                    break;
                case 4:
                    System.out.println("Цвет?");
                    String colorFilter = scanner.next();
                    filters.put("color", colorFilter);
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        Set<Laptop> filteredLaptops = laptops.stream()
                .filter(laptop -> filters.getOrDefault("ram", 0) instanceof Integer && laptop.ram >= (int) filters.getOrDefault("ram", 0))
                .filter(laptop -> filters.getOrDefault("hdd", 0) instanceof Integer && laptop.hdd >= (int) filters.getOrDefault("hdd", 0))
                .filter(laptop -> filters.getOrDefault("os", "").equals("") || laptop.os.equalsIgnoreCase((String) filters.getOrDefault("os", "")))
                .filter(laptop -> filters.getOrDefault("color", "").equals("") || laptop.color.equalsIgnoreCase((String) filters.getOrDefault("color", "")))
                .collect(Collectors.toSet());

        System.out.println("Отфильтрованные ноутбуки для критериев:");
        filters.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("\nРезультаты фильтрации:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }
}