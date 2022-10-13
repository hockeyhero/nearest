# Testing Data

The two features of the service to test are "Find" and "CRUD" and we want to plan our test data accordingly. 

## Find

For testing the "Find" capability, a bulk insert of test date has been implemented. Test data is organized so that with Victoria Park, London ON is centre: 42.987138, -81.248800:

- 25 entries are 42.989272, -81.260383, ~1km away or < 2km
- 25 entries are 42.957720, -81.241387, ~3km away or < 5km
- 25 entries are 42.962753, -81.169493, ~7km away or < 8km
- 25 entries are 42.963467, -81.140214, ~9km away or < 10km
- 25 entries are 42.900998, -81.167931, ~11km away or < 12km
- 25 entries are 42.880904, -81.163308, ~13km away or < 15km

There is a total of 150 records. A distribution of goalies, defense, forwards and refs of different age and ability are within each group of 25. Based on this dataset we can use postman to send Find Requests into the service and test the response. 

## CRUD

"CRUD" testing is simply:

- add a user
- prove he can be found
- update the user for set his `hideMe` property
- prove he can't be found
- delete the user

If all tests pass the service is considered good. 

## Postman Collections

The Postman Desktop Application was used to create two collections, one for Find tests and one for the CRUD test. 

See the postman files: 

- postman\nearest-Find.postman_collection
- postman\nearest-CRUD.postman_collection

These can be ported into the Postman Application for updating. 

 






