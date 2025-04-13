package in.ikcon.ims.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import in.ikcon.ims.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class Users extends Auditable{

    @Column(name = "email" , unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "entity_name")
    private String entityName;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "creator" ,
            orphanRemoval = true , cascade = CascadeType.ALL)
    @OrderBy("created_date DESC")
    private List<Tickets> tickets = new ArrayList<>();
}
