

Restaurant Application:


To Run this app:
1) Go to com/restaurant/online/RestaurantApplication.java
2) Run this application. 

Technologies:
1) Java
2) SpringBoot
3) Spring AOP


Project Structure:

1) Controller: API Hit would first come to controller only.
2) Entity: All POJO Class
3) Enums
4) Exception: Custom Exceptions and also using Controller Advice so that nay exception can be represent in UI in strcture Way.
5) Persistance: In Memory DB. Using Concurrent hashMap and BlockingQueue
6) Service : API: Service Intefacer
			 Impl: Implementation
7) By Default I have assumed 30 mins is delivery time of any order. Job is scheduled and Every Minute Object would be get refreshed automatically.
			 

Test Project: 
1) There are Unit test cases written for main flows.
2) In Application.properties. I have configured port number 2121 to use.

Product API:

1) To get All Available items:
	Call type: GET
	Call: http://localhost:2121/api/items
	
	return: List of Item objects (Menu)
	
	
Restaurant API;
1) To Order an Item:
	Call type: POST
	Call: http://localhost:2121/api/orders
	params in Json: 
	{
	"itemId" : 1 ,
	"quantity" : 3
	}
	
	return: Order is created and would get order object response. Executive ID is not being exposed to Customer though it is part of POJO.
	
2) Check Status of Order:
	Call type: GET
	Call: http://localhost:2121/api/orders/1 
	
	return: order object status. returning Object along with Status
	

	
3) Update Delivery Status: (First Assign it to any executive the run this API)

	Call type: PUT
	Call: http://localhost:2121/api/orders
	params in Json: 
	{
	"orderId" : 1 ,
	"status" : "DELIVERED"
	}
	
	return: Order Object Reponse. 
	
4) All Active Executives:

	Call type: GET
	Call: http://localhost:2121/api/orders/online
	
	return: List of online executives 
	
	
Delivery Executive APIs:

1) To Assign any order with executives.

	Call type: POST
	Call: http://localhost:2121/api/executives
	params in Json: 
	{
	"orderId" : 1 ,
	"deliveryExecutiveId" : 4 
	}
	
	return: Executive is assigned if available and active. Reponse would be executive object.
	
2) Status of any Executive:
	

	Call type: GET
	Call: http://localhost:2121/api/executives/4
	
	return: Gives Executive object with Id 4 if exists.
	


	
	
	