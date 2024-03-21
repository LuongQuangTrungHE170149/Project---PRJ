<%-- Document : login Created on : 20 thg 3, 2024, 09:24:56 Author : QUANG TRUNG
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Login</title>
        
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f2f2f2;
            }

            .container {
                max-width: 400px;
                margin: 100px auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            h2 {
                text-align: center;
                color: #333;
            }

            form {
                margin-top: 20px;
            }

            form input[type="text"],
            form input[type="password"] {
                width:95%;
                padding: 10px;
                margin: 5px 0;
                border: 1px solid #ccc;
                border-radius: 3px;
            }

            form input[type="submit"] {
                width: 100%;
                padding: 10px;
                margin-top: 10px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 3px;
                cursor: pointer;
            }

            form input[type="submit"]:hover {
                background-color: #0056b3;
            }

            .error-message {
                color: red;
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Login</h2>
            <% if (request.getAttribute("errorMessage") != null) { %>
            <p style="color: red"><%= request.getAttribute("errorMessage") %></p>
            <% } %>
            <form action="LoginServlet" method="post">
                Username: <input type="text" name="username" /><br />
                Password: <input type="password" name="password" /><br />
                <input type="submit" value="Login" />
            </form>
        </div>
    </body>
</html>
