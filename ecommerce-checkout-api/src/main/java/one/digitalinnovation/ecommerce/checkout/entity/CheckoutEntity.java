package one.digitalinnovation.ecommerce.checkout.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CheckoutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String code;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;


    public enum Status {
        CREATED,
        APPROVED
    }
}
