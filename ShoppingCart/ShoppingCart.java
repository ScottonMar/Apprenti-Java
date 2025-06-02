public class ShoppingCart {
    public static void main(String[] args) {
        System.out.println("Welcome to the shopping cart app!");

        //1. Assign variables to each of the elements above.
        int productId = 1;
        int productCategory = 2;
        double productCost = 2.56;
        double productPrice = 4.99;
        int productQuantity = 78;

        //2. Calculate the total cost of the product based on inventory.
        double totalCost = productCost * productQuantity;
        System.out.println("Total Cost: " + totalCost);

        //3. Calculate the profit in dollars of the product.
        double profitPerProduct = productPrice - productCost;
        System.out.println("Profit in dollars: " + profitPerProduct);

        //4. Calculate the total potential profit.
        double totalProfit = profitPerProduct * productQuantity;
        System.out.println("Total Profit: " + totalProfit);
    }
}