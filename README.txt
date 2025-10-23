PROJECT_TYPE: REST API
FRAMEWORK: SPRING BOOT
PROGRAMMING_LANGUAGE: JAVA
PROJECT_NAME: Shorting URL Service
VERSION: 1.0.0
AUTHOR: Oluwaseun Joseph Olotu
DATABASE: MySql
DATABASE_NAME: shorting_url_db



END_POINTS:

1. Create Shorten Url
   - Method: POST
   - URL: /shorten
   - Request Body:
     {
         "url" : "https://www.google.com/home/school"
     }
   - Response:
     {
         "id": 3,
         "url": "https://www.google.com/home/school",
         "shortCode": "aCuy80b",
         "createdAt": "2025-10-23T18:00:31.014638Z",
         "updatedAt": "2025-10-23T18:00:31.014736Z"
     }
    - Description: This endpoint creates a shortened URL for the provided original URL.

2. Retrieve Original URL
    - Method: GET
    - URL: /shorten/{shortCode}
    - Response:
      {
            "id": 3,
            "url": "https://www.google.com/home/school",
            "shortCode": "aCuy80b",
            "createdAt": "2025-10-23T18:00:31.014638Z",
            "updatedAt": "2025-10-23T18:00:31.014736Z"
      }
    - Description: This endpoint retrieves the original URL associated with the provided short code.

3. Update Short URL
    - Method: PUT
    - URL: /shorten/{shortCode}
    - Request Body:
      {
          "url" : "https://www.google.com/home/updated-school33"
      }
    - Response:
      {
          "id": 3,
          "url": "https://www.google.com/home/updated-school33",
          "shortCode": "aCuy80b",
          "createdAt": "2025-10-23T18:00:31.014638Z",
          "updatedAt": "2025-10-24T10:15:45.123456Z"
      }
    - Description: This endpoint updates the original URL for the given short code.

4. Delete Short URL
    - Method: DELETE
    - URL: /shorten/{shortCode}
    - Response:
        HTTP Status 204 No Content
    - Description: This endpoint deletes the shortened URL associated with the provided short code.

 5. Get Short URL Statistics
    - Method: GET
    - URL: /shorten/{shortCode}/stats
    - Response:
      {
          "id": 3,
          "url": "https://www.google.com/home/school",
          "shortCode": "aCuy80b",
          "createdAt": "2025-10-23T18:00:31.014638Z",
          "updatedAt": "2025-10-23T18:00:31.014736Z",
          "accessCount": 42
      }
    - Description: This endpoint retrieves statistics for the shortened URL, including the number of times it has been accessed.