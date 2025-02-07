## ConsoleStore 🖥️ 💿

---

ConsoleStore is terminal-based application that simulates shopping experience. User can register and login to their accounts, list all items in the store, add them to their basket with specified quantities, view their basket summary (with total cost) and finalize their order what decreases items quantity in the database. It also increases quantity of items already in the basket if the same item is added again.

---

### 🔨 Set up & run application

To build and run this app you need installed JDK and MySQL database.
1. Execute following command in your MySQL client to create new database named console-store
    - `create database console-store`
    - There is no need to manually create database schema or insert any data - application will handle it automatically on the first run
    - You should have user in your db with login "root" and empty password 
2. Clone this repository to your directory
    - `git clone https://github.com/BeeBercik/ConsoleStore.git`
3. Navigate inside the directory
    - `cd ConsoleStore`
4. Build application and create .jar file
    - `mvn clean install`
5. Launch application using generated .jar file
    - `java -jar target/ConsoleStore-1.0-SNAPSHOT.jar`

---

### 🚀	 App features

1. User Registration and Login:
   - New users can create accounts by providing unique login credentials.
   - Existing users can log in with their credentials.
2. List Available Products:
   - Users can view all products, including their details like name, price and quantity. 
3. Add Items to Basket:
   - Users can add products to their basket with specific quantity.
   - If the requested quantity exceeds available stock, the item will not be added to the basket. 
   - If the item is already in the basket, the quantity is increased.
4. View Basket Summary:
   - Users can view all items in their basket with their quantities and the total cost. 
5. Finalize Basket:
   - Finalizing the basket simulates completing the order.
   - The application reduces the quantities of the purchased items in database and clears the basket.

---

### 📖 Technology stack
- Java
- Spring
- JDBC
- Maven
- MySQL