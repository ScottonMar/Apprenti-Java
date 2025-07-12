# **Inventory Manager Console Application**

A Java console-based inventory management system that allows users to manage product data with features like adding, updating, deleting, saving, and loading inventory.

## **Features**

* Add new products (including perishable items with expiry dates)

* View all inventory

* Search products by ID or name

* Update quantity or price

* Delete products

* Save inventory to file

* Load inventory from file

* Input validation and error handling

* Unit tested using JUnit 5

```
InventoryManager/
│
├── src/
│   ├── main/
│   │   ├── java/com/inventory/
│   │   │   ├── Main.java
│   │   │
│   │   ├── java/com/inventory/model/
│   │   │   ├── Product.java
│   │   │   ├── PerishableProduct.java
│   │   │
│   │   ├── java/com/inventory/service/
│   │   │   ├── InventoryService.java
│   │   │
│   │   ├── java/com/inventory/data/
│   │   │   ├── InventoryRepository.java
│   │   │
│   │   ├── java/com/inventory/util/
│   │   │   ├── InputUtil.java
│
├── test/
│   ├── java/com/inventory/model/
│   │   ├── ProductTest.java
│   │   ├── PerishableProductTest.java
│   ├── java/com/inventory/service/
│   │   ├── InventoryServiceTest.java
│   ├── java/com/inventory/data/
│   │   ├── InventoryRepositoryTest.java
│
├── inventory.txt         # File used to persist inventory data
├── pom.xml               # Maven project config
└── README.md             # Project overview
```

## How to Run

Open IntelliJ IDEA
Import project as a Maven project
Open Main.java, right-click > Run Main.main()
Follow the menu prompts in the terminal.

## **Sample Menu**

### **===== Inventory Manager =====**
1. Add Product
2. View Products
3. Search Product
4. Update Product
5. Delete Product
6. Save Inventory to File
7. Load Inventory from File
8. Exit
  
Enter your choice: 1
  
Is this a perishable product? (Y/N): Y
 
Enter Product ID: 002
 
Enter Product Name: Milk

Enter Quantity: 10

Enter Price: 3.49

Enter Expiry Date (yyyy-MM-dd): 2025-12-31 

Product added successfully!

### **===== Inventory Manager =====**

Enter your choice: 2

===== Inventory List =====

Product ID: 002

Name: Milk

Quantity: 10

Price: $3.49

Expiry Date: 2025-12-31

### **===== Inventory Manager =====**

Enter your choice: 3

Enter Product ID or Name: Milk

Product Found:

Product ID: 002

Name: Milk

Quantity: 10

Price: $3.49

Expiry Date: 2025-12-31

### **===== Inventory Manager =====**

Enter your choice: 8

Are you sure you want to exit? (Y/N): Y
