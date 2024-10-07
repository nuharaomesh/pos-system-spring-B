
# POS System Documentation

## Overview
This document provides a detailed overview of the API endpoints for the POS (Point of Sale) system. The system includes three key entities: Customer, Item, and Order. Each entity has its own controller with various endpoints for creating, retrieving, updating, and deleting data.

## Tech Stack:

- **Spring**: A powerful Java framework used for building the backend of the application. It handles requests, processes data, and integrates with the database.

- **MySQL**: A relational database used to store and manage the application's data, such as customers, items, and orders.


## Customer Controller (`api/v1/customers`)

### Endpoints:
- `GET /api/v1/customers`: Retrieve a list of all customers.
- `GET /api/v1/customers/{id}`: Retrieve a specific customer by ID.
- `POST /api/v1/customers`: Create a new customer.
- `PUT /api/v1/customers/{id}`: Update an existing customer's details.
- `DELETE /api/v1/customers/{id}`: Delete a customer by ID.

### Request Body (for POST/PUT):
Example:
```json
{
  "customerID": "",
  "gender": "MALE",
  "gmail": "Test@gmail.com",
  "name": "Omesh Nuhara",
  "phnNo": "1234567890"
}
```

### Response:
- Success (200 OK): Returns the created/updated customer.
- Not Found (404): Customer not found.
- Bad Request (400): Invalid data.

## Item Controller (`api/v1/item`)

### Endpoints:
- `GET /api/v1/item`: Retrieve a list of all items.
- `GET /api/v1/item/{id}`: Retrieve a specific item by ID.
- `POST /api/v1/item`: Create a new item.
- `PUT /api/v1/item/{id}`: Update an existing item.
- `DELETE /api/v1/item/{id}`: Remove an item by ID.

The `ItemController` class is responsible for managing items in the system. It includes functionality for saving, updating, retrieving, and deleting items. When saving or updating an item, multipart form data is used to handle the following fields:

- `category`: The category of the item.
- `img`: The image of the item (uploaded as a file).
- `itemName`: The name of the item.
- `price`: The price of the item.
- `qty`: The quantity of the item.

The image is processed as a `MultipartFile` and converted into a Base64 string for easier storage.

### Response:
- Success (200 OK): Returns the created/updated item.
- Not Found (404): Item not found.
- Bad Request (400): Invalid data.

## Order Controller (`api/v1/orders`)

### Endpoints:
- `POST /api/v1/orders`: Place a new order.

### Request Body (for POST):
Example:
```json
[

  [
    {
      "itemID": "item11",
      "qty": 5
    },
    {
      "itemID": "item22",
      "qty": 2
    }
  ]
,
  {
    "orderID": "order11",
    "price": 100.0,
    "time": "2024.10.1",
    "qty": 7,
    "customerID": "CUSTOMER-ed5d8a10-5fe6-4dc4-a74e-94caa004a507"
  },

  [
    {
      "orderID": "order11",
      "itemID": "item11",
      "price": 20.1,
      "counts": 2
    },
    {
      "orderID": "order11",
      "itemID": "item22",
      "price": 10.1,
      "counts": 3
    }
  ]

]
```

### Response:
- Success (200 OK): Returns the created/updated order.
- Not Found (404): Order not found.
- Bad Request (400): Invalid data.

## Error Handling
- 400 Bad Request: Invalid request format or data.
- 404 Not Found: Resource not found.
- 500 Internal Server Error: Server-side error.

## Security
- If applicable, describe any token-based authentication or other security mechanisms.