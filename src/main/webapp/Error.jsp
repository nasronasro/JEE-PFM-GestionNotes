<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/error.css" rel="stylesheet">
</head>
<body class="bg-light d-flex justify-content-center align-items-center vh-100">
    <div class="error-container text-center p-4 bg-white shadow rounded">
        <div class="alert alert-danger" role="alert">
            <h2 class="display-5 text-danger">Oops! Something went wrong.</h2>
            <p class="lead">We apologize for the inconvenience. An unexpected error occurred.</p>
        </div>
        <hr>
        <div class="text-start">
            <p class="fw-bold">Error details:</p>
            <div class="error-details bg-dark text-white p-3 rounded">
                <pre><%= exception.getMessage() %></pre>
            </div>
        </div>
        <hr>
        <a href="list-note" class="btn btn-primary mt-3">Go back to Home</a>
    </div>
</body>
</html>