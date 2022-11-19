# cab-booking-spring-boot

Steps followed for this project.
-------------------------------------
I have pre inserted car details in the database. 

Step 1-->Please register in the project by going to this URL in postman http://localhost:8083/register for admin and add this json in the body and select POST method
          {
              "email":"admin@gmail.com",
              "fname":"admin",
              "lname":"flintzy",
              "password":"1234",
              "phone":1234567890
           }
           The details can be any of your wish. Go to MySQL and edit the role to admin as by default when a user registers this system assigns "user" role to all users.
           update customer set role = "admin" where fname = "admin";
           Your admin is registered to the system and now you are good to go...
           
Step 2-->You can register as many users you want. Now to login, Select POST method, and paste this URL http://localhost:8083/authenticate and enter email and password in the body, in the below format
         {
            "email":"admin@gmail.com",
            "pass":"1234"
         }
         This should generate a JWT token in the results, Copy it. In Postman, go to Authorization, select Type as "Bearer Token" and paste the copied token which has            validity of 1 day(Can change it in jwtutil.java).
         
Step 3--> Similarly you can login to the system as a normal user as mentioned in step 2. Since this is role based security, /admin/** urls are only allowed for admin               role, /driver/** for driver only and /user/** for all the 3 roles(admin,user,driver).

Step 4--> You can go to controller package and find the endpoints for every task to do. I am mentioning few of them below which can be copy pasted in body and updated           based on need.
          
      --> car related(only admin)
          -----------
          1)select GET in postman, add this URL http://localhost:8083/admin/getAllCars --->All Cars in the system with status as explained in glossary below.
          2)select GET in postman, add this URL http://localhost:8083/admin/getAvailableCars  -->All cars that are not assigned with driver
          3)select POST in postman, add this URL http://localhost:8083/admin/saveCar and then paste the below car details, make sure the car_id is unique
                  {
                      "car_id" : 105,
                      "car_name" : "Mercedes C class",
                      "car_capacity" : 6,
                      "car_rent" : 900
                  }
            4)select DELETE in postman, add this URL http://localhost:8083/admin/deleteCar/105 here 105 is car_id-->this will delete the car with mentioned id.
            
        --> add driver(only admin)
            ----------------
            1)select POST in postman, add this URL http://localhost:8083/admin/addDriver and add the email of the person in body whom you want to assign as driver
              {
                  "email":"abc@gmail.com"
              }
              
            2)select POST in postman, add this URL http://localhost:8083/admin/deleteDriver and add the email of the person in body whom you want to delete as driver
            {
                  "email":"abc@gmail.com"
             }
             
         --> car assign(only admin)
             -------
             1)select POST in postman, add this URL http://localhost:8083/admin/carAssign and enter driver email and car id in body to assign the car to driver
             {
                  "driver_email":"abc@gmail.com",
                  "car_id":101
              }
              
             2)select POST in postman, add this URL http://localhost:8083/admin/carUnassign and enter the driver email in body to unassign current assigned car.
             {
                  "driver_email":"abc@gmail.com"
              }
              
              3)select POST in postman, add this URL http://localhost:8083/admin/getDriverCars and enter the driver email in body to get all cars used by the driver
              {
                  "driver_email":"abc@gmail.com"
              }
              
              
          ---> get all car used by driver(only driver)
              ---------------
              1) Login again with driver credentials(above it is abc@gmail.com and password set by you while registering) and add the generated token in
              Authorization's Bearer Token type.
                 Now, select  POST in postman, add this URL http://localhost:8083/driver/getListCar and hit send to give all the car used by logged in driver.
                 
                 
           ----> customer actions(user,driver,admin)
                -------
              Login as a normal registerd user, add the token
              1)select GET in postman, add this URL http://localhost:8083/user/getAvailCar, go to params and add 'seat' as key and value as any seat capacity,
              this will list all the available cars with the mentioned seats which has driver assigned to it.(car with drivers only will be listed)
              
              2)select POST in postman, add this URL http://localhost:8083/user/bookCab and enter the car_id that needs to book in body, basically car id can be found
              from the above step getting available car with required seat, so enter the same car_id. This will generate the transaction id of booking.
                {
                        "car_id":102
                 }
                 
               3)select GET in postman, add this URL http://localhost:8083/user/allBookings and hit send, this will generate the current booking of car where
               transaction id can be found with other details(only the active bookings, not previous cancelled one)
               
               4)select POST in postman, add this URL http://localhost:8083/user/cancelCab and add transaction id(txn_id) of booking which can be found from above
               step as well as when booking to cancel the booked cab.
                 {

                    "txn_id":1

                 }


Tables in Database : there are 4 database tables customer,booking, casr_assign, car

Glossary--> You might notice few variables in the code, below is the explaination for few of them:

          In Customer.java
          -----------------
          isCustomerFree --> Initially, it is 1 which means customer has not booked any car. 0 means customer has booked car.
          isDriverAvailable --> To become a driver, user must register to the system. Admin can then make a user as driver. So once the user has become driver, 1 means
          driver is available and not assigned with any car, 0 means driver is assigned with car. By default this field is 0 for other roles.
          
           In Car.java
          -----------------
          car_status--> By default it is 1 which means, car is free and not assigned with any driver. 0 means car is been assigned with the driver and it cannot be 
          assigned to anyone else.
          car_avail--> By default it is 1, which means car is available for booking. 0 means it is already booked.
          
          
            In CarAssign.java
          -----------------
          isActive--> when the car is assigned to any driver the status is saved as 1 which means this car is currently used by the driver. Once the car gets  
          unassigned this status change to 0
          
            In Booking.java
          -----------------
          isActive--> when the booking is done by the customer, this status is saved as 1 once the user cancels the booking, the status changes to zero. (Also if the
          journey is finished same can be implemented)
           
  
