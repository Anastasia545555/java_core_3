import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] productNames = {"Хлеб", "Яблоки", "Молоко"};
        float[] productPrices = {100f, 200f, 300f};
        final String PATH = "D:\\Другое\\Проекты\\java_core_3\\файлы";
        File dir = new File(PATH);
        File textFile = new File(dir, "basket1.bin");
        String com;
        boolean mark = true;
        Basket basket = new Basket();
        basket = new Basket(productNames, productPrices);
        while (mark) {
            try {
                basket = Basket.loadFromBinFile(textFile);
            } catch (IOException ex) {

            } catch (ClassNotFoundException e) {
                System.out.println("Не удалось найти файл");
            }
            System.out.println("\nВыберите номер команды или нажмите end:" +
                    "\n1. Добавить товар в корзину" +
                    "\n2. Проверить корзину товаров" +
                    "\n3. Сохранить текущую корзину покупок" +
                    "\n4. Восстановить корзину по названию файла\n");
            Scanner scanner = new Scanner(System.in);
            com = scanner.nextLine();
            switch (com) {
                case ("1") -> {
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Введите номер товара и количество через пробел");
                    basket.printCard();
                    System.out.println("");
                    String s = scan.nextLine();
                    String[] parts = s.split(" ");
                    try {
                        int productNum = Integer.parseInt(parts[0]) - 1;
                        int prodAmount = Integer.parseInt(parts[1]);
                        basket.addToCart(productNum, prodAmount);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                }
                case ("2") -> {
                    System.out.println("Ваша корзина:");
                    basket.printCard();
                    break;
                }
                case ("3") -> {
                    if (!basket.equals(null)) {
                        basket.saveBin(textFile);
                        System.out.println("Корзина сохранена");
                    } else System.out.println("Корзина не сохранена");
                    break;
                }
                case ("4") -> {
                    Scanner scan = new Scanner(System.in);
                    try {
                        System.out.println("Введите название файла");
                        textFile = new File(dir, scan.nextLine());
                        basket = Basket.loadFromBinFile(textFile);
                        System.out.println("Ваша корзина:");
                        basket.printCard();
                    } catch (IOException ex) {
                        System.out.println("Не удалось найти файл");
                    } catch (ClassNotFoundException e) {
                        System.out.println("Не удалось найти файл");
                    }
                    break;
                }
                case ("end") -> {
                    mark = false;
                    break;
                }
                default -> {
                    System.out.println("Введенной команды не существует");
                    break;
                }
            }


        }

    }
}