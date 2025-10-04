package org.dd.bre.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_name", nullable = false, unique = true, length = 50)
    private String statusName;

    @OneToMany(mappedBy = "status")
    private List<Order> orders;
}
