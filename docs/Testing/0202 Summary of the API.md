# Summary of the API

The Nearest Service is a support service to the main application hockeyhero. It's purpose is to provide lightning fast look-ups based on set criteria. It is not meant to be publically available and as such the interface is basic with no authorization. 

The calls available are: 

- POST - to create a user
- GET - to return all users
- GET/{id} - to return a single user
- GET/search - to return a select set of users
- PUT - to update a user
- DELETE - to delete a user

The GET/search is the workhorse of the service. The search is based on the following criteria: 

- latitude (required)
- longitude (required
- radius (required)
- position
- lowAge
- highAge
- lowSkill
- highSkill

Using the GET/search API users can do searches such as: 

- list all goalies within 2 kms
- list all defencemen over 45 within 5 kms
- list all above-average forwards between 25 - 45 within 8 kms 




