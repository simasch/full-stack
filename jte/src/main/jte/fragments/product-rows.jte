@import ch.martinelli.demo.jte.domain.Product
@import org.springframework.data.domain.Page

@param Page<Product> products

@for(int i = 0; i < products.getContent().size(); i++)
    @if(i == products.getSize() - 1)
        <tr hx-get="/products/paging?size=${products.getSize()}&page=${products.getNumber() + 1}"
            hx-trigger="revealed"
            hx-swap="afterend">
            @template.fragments.product-row(product = products.getContent().get(i))
        </tr>
    @else
        <tr>
            @template.fragments.product-row(product = products.getContent().get(i))
        </tr>
    @endif
@endfor

