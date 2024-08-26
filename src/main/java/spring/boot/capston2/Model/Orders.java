package spring.boot.capston2.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;


    @NotNull(message = "User Id should not be Null!")
    @Column(columnDefinition = "int not null ")
    private Integer user_id;

    //plant_id
//    @NotNull(message = "Plant Id should not be Null!")
//    @Column(columnDefinition = "int not null")
//    private Integer plant_id;

    //قائمة بالنباتات والأدوات التي تم طلبها
    //items: String
    @NotEmpty(message = "Items should not be Empty!")
    @Column(columnDefinition = "varchar(500) not null")
    private String items;

    //quantity: int
    @NotNull(message = "Quantity should not be null!")
    @Column(columnDefinition = "int not null")
    private int quantity;

    //toolStatus: boolean
    @AssertFalse
    @Column(columnDefinition = "boolean default false")
    private boolean toolStatus;

    //totalAmount: double
    @NotNull(message = "Total Amount should not be null!")
    @Column(columnDefinition = "DOUBLE not null")
    private double totalAmount;

    //orderDate: Local date
    @Column(columnDefinition = "datetime default (current_timestamp)")
    private LocalDate orderDate;

    @Column(columnDefinition = "datetime default (current_timestamp)")
    private LocalDate updatedAt;

    @ManyToMany
    @JsonIgnore
    private Set<Plants> plants;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "users_id" , referencedColumnName = "id")
    private Users users;
}
