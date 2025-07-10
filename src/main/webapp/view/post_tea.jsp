<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Post Tea Product</title>
</head>
<body>
    <h2>Post Tea Product</h2>
    <form action="post_tea_process.jsp" method="post" enctype="multipart/form-data">
        Tea Name: <input type="text" name="name" required><br><br>
        Region: <input type="text" name="region"><br><br>
        Grade: <input type="text" name="grade"><br><br>
        Weight (grams): <input type="number" step="0.01" name="weight"><br><br>
        Strength: <input type="text" name="strength"><br><br>
        Price (USD): <input type="number" step="0.01" name="price" required><br><br>
        Quantity: <input type="number" name="quantity"><br><br>
        Image: <input type="file" name="image_file" accept="image/*"><br><br>
        <input type="submit" value="Post Tea">
    </form>
</body>
</html>
