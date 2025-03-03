@import ch.martinelli.demo.jte.domain.Product

@param Product product

<td>${product.getName()}</td>
<td>${product.getDescription()}</td>
<td>$${String.format("%.2f", product.getPrice())}</td>
<td>${product.getStock()}</td>
<td>
    <a href="/products/edit/${product.getId()}">Edit</a>
</td>

