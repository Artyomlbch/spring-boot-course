<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
</head>
<body>
<form action="/login" method="post">
    <label for="username">Username:
        <input id="username" type="text" name="username" REQUIRED>
    </label><br>
    <label for="password">Password:
        <input id="password" type="password" name="password" REQUIRED>
    </label><br>
    <button type="submit">Login</button>
</form>
</body>
</html>