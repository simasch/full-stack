@import ch.martinelli.demo.jte.domain.Product

@param java.util.List<Product> products
@param Product product
@param String message = null

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Product Management</title>
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>
<h1>Products</h1>

@if(message != null)
    <div class="alert">
        ${message}
    </div>
@endif

<form method="post" action="/products">
    @if(product.getId() != null)
        <input type="hidden" name="id" value="${product.getId()}">
    @endif

    <div class="form-group">
        <label for="name">Name</label>
        <input type="text" id="name" name="name" value="${product.getName()}" required>
    </div>

    <div class="form-group">
        <label for="description">Description</label>
        <textarea id="description" name="description">${product.getDescription()}</textarea>
    </div>

    <div class="form-group">
        <label for="price">Price</label>
        <input type="number" id="price" name="price" value="${product.getPrice()}" step="0.01">
    </div>

    <div class="form-group">
        <label for="stock">Stock</label>
        <input type="number" id="stock" name="stock" value="${product.getStock()}">
    </div>

    <div class="form-buttons">
        <button type="submit">Save</button>
        @if(product.getId() != null)
            <a href="/products">Cancel</a>
        @endif
    </div>
</form>

<table class="product-table">
    <thead>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Stock</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    @for(Product prod : products)
        <tr>
            <td>${prod.getName()}</td>
            <td>${prod.getDescription()}</td>
            <td>$${String.format("%.2f", prod.getPrice())}</td>
            <td>${prod.getStock()}</td>
            <td>
                <a href="/products/edit/${prod.getId()}">Edit</a>
            </td>
        </tr>
    @endfor
    </tbody>
</table>
</body>
</html>