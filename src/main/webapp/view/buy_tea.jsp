<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tea Products</title>
</head>
<body>
    <h2>Available Tea Products</h2>

    <!-- Example static products (no backend, no styles) -->
    <h3>Green Tea</h3>
    <p>Price: $10 per pack</p>
    <p>Origin: Sri Lanka</p>
    <a href="tea_details.jsp?name=Green%20Tea&price=10&origin=Sri%20Lanka" target="_blank">
        <button>Add to Cart</button>
    </a>
    <hr>

    <h3>Black Tea</h3>
    <p>Price: $8 per pack</p>
    <p>Origin: India</p>
    <a href="tea_details.jsp?name=Black%20Tea&price=8&origin=India" target="_blank">
        <button>Add to Cart</button>
    </a>
    <hr>

    <h3>Herbal Tea</h3>
    <p>Price: $12 per pack</p>
    <p>Origin: China</p>
    <a href="tea_details.jsp?name=Herbal%20Tea&price=12&origin=China" target="_blank">
        <button>Add to Cart</button>
    </a>
    <hr>

</body>
</html>
