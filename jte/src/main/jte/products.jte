@import ch.martinelli.demo.jte.domain.Product
@import org.springframework.data.domain.Page

@param Page<Product> products
@param Product product
@param String message = null

<!DOCTYPE html>
<html lang="en">
<head>
    <title>JTE Product Management</title>
    <link rel="stylesheet" href="/css/main.css">
    <script src="https://unpkg.com/htmx.org@1.9.10"></script>
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
    @template.fragments.product-rows(products = products)
    </tbody>
</table>
</body>
</html>