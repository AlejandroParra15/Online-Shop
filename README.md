# Online Shop Backend

This project was made in Spring Boot with a MySQL database.

# Development server

The project runs on localhost:8080

# API routes

Any page must have the path '/shop' at the beginning.

## USER API:

You can use '/shop/users' with a GET request to receive the list of users.

You can use '/shop/users' with a POST request to add a new user.

You can use '/shop/users/{userId}' with a GET request to receive the details of a user setting their ID.

You can use '/shop/users/{userId}' with a PUT request to update a user's information by putting their ID.

You can use '/shop/users/{userId}' with a DELETE request to remove a user from the list by putting their ID.

## BOOK API:

You can use '/shop/books' with a GET request to receive the list of books.

You can use '/shop/books' with a POST request to add a new book.

You can use '/shop/books/{itemId}' with a GET request to receive the detail of a book by setting its ID.

You can use '/shop/books/{itemId}' with a PUT request to update a book's information by setting its ID.

You can use '/shop/books/{itemId}' with a DELETE request to remove a book from the list by setting its ID.
