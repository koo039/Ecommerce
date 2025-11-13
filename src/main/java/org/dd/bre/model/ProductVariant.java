package org.dd.bre.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product_variants")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String sku;

    @Column(length = 10)
    private String size;

    private String color;

    @Column(name = "stock_qty", nullable = false)
    private Integer stockQty;

    @Enumerated(EnumType.STRING)
    private VariantStatus status = VariantStatus.INSTOCK;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToMany(mappedBy = "productVariant")
    @JsonIgnore
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "productVariant")
    @JsonIgnore
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "productVariant",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<Image> images;

}
