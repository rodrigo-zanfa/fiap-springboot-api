package br.com.fiap.abctechapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operator_id", nullable = false)
    private Long operatorId;

    @ManyToMany
    @JoinTable(name = "orders_assistances", foreignKey = @ForeignKey(name = "FK_order_id"), joinColumns = @JoinColumn(name = "order_id"), inverseForeignKey = @ForeignKey(name = "FK_assistance_id"), inverseJoinColumns = @JoinColumn(name = "assistance_id"))
    private List<Assistance> services;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "start_order_location_id", foreignKey = @ForeignKey(name = "FK_start_order_location_id"))
    private OrderLocation startOrderLocation;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "end_order_location_id", foreignKey = @ForeignKey(name = "FK_end_order_location_id"))
    private OrderLocation endOrderLocation;
}
