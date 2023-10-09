### Instructions
1. Open in IDE and start the application (I use IntelliJ so just press play button)
2. To use the endpoints I recommend to use Postman. I'll provide the endpoints and examples of the body in this file. If you want to try the endpoint in a different way, you're on your own :)
3. Create an User
   - POST http://localhost:8080/api/users
   - Body: 
``` json
{ "firstName": "Bruce",
 "lastName": "Wayne",
 "address": {
 "street": "Hallostr",
 "houseNumber": "3",
 "city": "Gotham",
 "postalCode": "12345"
 },
 "birthday": "1992-03-19"    
}
```
   
4. Create a Device
   - POST http://localhost:8080/api/devices
   - Body:
```json
{
    "serialNumber": "1",
    "phoneNumber": "01738493203",
    "model": "sd3"
}
```

5. Assign a device to an user
   - PUT http://localhost:8080/api/users/{userId}/devices
   - Body: the device that is returned from step 4 (whole object) example =>
```json
{
  "id": "c9b3fae5-769f-4b0f-839d-c46397c225aa",
  "serialNumber": "1",
  "model": "sd3",
  "phoneNumber": "01738493203",
  "userId": null
}
```

6. List all users with devices assigned to them
   -GET http://localhost:8080/api/users/devices