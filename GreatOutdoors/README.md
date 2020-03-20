Great Outdoors – Overview:
=========================
Great Outdoors (GO) is an electronic distributor of outdoor products.
GO sources the products from various manufacturers and supplies it to Retailers across various countries.

Product Categories:
===================
1. Camping Equipment
2. Golf Equipment
3. Mountaineering Equipment
4. Outdoor Protection
5. Personal Accessories

Functional Requirements:
=======================
A. Build Product Master:

1. Build product master with product details, technical specifications along with product sample images
2. Customer should be able to view all the product details, technical specifications and selected product images, color and 
product dimensions while ordering.
3. An Option to suggest changes to the product specifications by customer.

B. Manage Product Shipping Addresses:

4. Shipping Address selection should be made available for each product in the Customer Order
5. There should be an option to change the default Shipping Address for each product of the Customer Order
6. Ask customer if the selected product needs gift packing or not. If customer says yes then ask explicitly whether change of shipping
address is needed or not.
7. Based on customer’s purchase and delivery pattern, system should suggest customer to change the Shipping Address using customer orders
history.
8. An Option to change the product if the customer order is not yet processed.
9. An option to change the shipping address if the customer order is not yet sent for delivery.

C. Maintain Customer Addresses List:

10. There should be an option to define more than one shipping addresses at Customer level so that customer can pick up the right 
shipping address for each product while ordering.
11. Customer should be able to create a new address (if address doesn’t exist that he/she wants) and able to mark it as a onetime use 
address or add it to his/her addresses list.
12. An option to build frequently purchasing products and its’ associated delivery addresses by the customer. System should be able to 
suggest this list based on order history.

Reduce the inventory at Retail stores
• Provide month on month /quarter or quarter /year on year reports on the time that an item stays on the shelf of the Retailer
 
• Show hot product
• Refund

Getting Started:

Built with: Maven, JDK, SpringBoot, Spring Security, Spring JPA, MySQL, Github
External Tools : Docker
Dockerfile specifies how the application is built and packaged

URL:
Admin: http://localhost:9090/admin/product
Customer : http://localhost:9090/customer/product


