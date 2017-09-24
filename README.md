# Retail-RESTful-webservice

The goal is to create a proof of concept for a products API. The idea is to create a RESTful websevice  that can retrieve product and price details by ID. 
The application performs:

(i) GET request to retrieve product details. 
	  URI structure used: /products/{id}

(ii) GET to retrieve products name from an external API.

(iii) Read pricing information from a NoSQL data store; mongodb and combine the response with the product id and name from 
      http request into a single response.
      
(iv) PUT request to update the product price in data store.

YAML API specification documentation :/src/resources/APIDoc.yml
