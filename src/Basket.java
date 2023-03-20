import java.io.*;
import java.util.*;

public class Basket implements Serializable{
    private String[] productName;
    private float[] productPrice;
    private int[] productAmount;

    public Basket() {

    }

    public Basket(String[] productName, float[] productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productAmount = new int[productName.length];
    }

    public Basket(String[] productName, float[] productPrice, int[] productAmount) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productAmount = productAmount;
    }

    public boolean addToCart(int productNumber, int amount) {
        if ((productNumber >= 0) && (amount > 0)) {
            for (int i = 0; i < productAmount.length; i++) {
                if (i == productNumber) {
                    productAmount[i] += amount;
                    System.out.println("Товар добавлен в корзину");
                    return true;
                }
            }
        }
        System.out.println("Не удалось добавить товар");
        return false;
    }

    public void printCard() {
        for (int i = 0; i < productName.length; i++) {
            System.out.printf("\n%d. %15s (%7f руб/шт) %5d шт - %10f руб", i + 1, productName[i], productPrice[i], productAmount[i], productPrice[i] * productAmount[i]);
        }
    }

    public void saveTxt(File textFile) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(textFile)) {
            printWriter.println(productName.length);
            for (int i = 0; i < productName.length; i++) {
                printWriter.println(productName[i] + "\t" + productPrice[i] + "\t" + productAmount[i] + "\t");
            }
        }
    }

    public static Basket loadFromTxtFile(File textFile) throws IOException {
        try (Scanner scanner = new Scanner(textFile)) {
            int size = Integer.parseInt(scanner.nextLine());
            String[] productName = new String[size];
            int[] productAmount = new int[size];
            float[] productPrice = new float[size];
            for (int i = 0; i < size; i++) {
                String[] s = scanner.nextLine().split("\t");
                productName[i] = s[0];
                productPrice[i] = Float.parseFloat(s[1]);
                productAmount[i] = Integer.parseInt(s[2]);
            }

            Basket basket = new Basket(productName, productPrice, productAmount);
            return basket;

        }
        //return null;
    }

    public void saveBin(File file) throws IOException{
        try (ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream((file)))){
            out.writeObject(this);
        }
    }

    public static Basket loadFromBinFile(File file) throws IOException, ClassNotFoundException{
        try (ObjectInputStream in=new ObjectInputStream(new FileInputStream((file)))){
            Basket b=(Basket) in.readObject();
            return b;
        }
    }
}
