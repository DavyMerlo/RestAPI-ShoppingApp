package com.davy.restapi.user.entity;

import com.davy.restapi.address.entity.AddressEntity;
import com.davy.restapi.card.entity.CustomerCardEntity;
import com.davy.restapi.order.entity.OrderEntity;
import com.davy.restapi.shared.entity.BaseEntity;
import com.davy.restapi.token.entity.TokenEntity;
import com.davy.restapi.user.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "_users")
@SequenceGenerator(
        name="default_gen",
        sequenceName = "user_id_seq",
        allocationSize=1)
public class UserEntity extends BaseEntity implements UserDetails {

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    private String email;

    @Column(name = "user_name")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<TokenEntity> tokens;

    @OneToMany(mappedBy = "user")
    private List<OrderEntity> orderDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private CustomerCardEntity customerCard;

    private Boolean locked = false;

    private Boolean enabled = false;

    @Builder
    public UserEntity(LocalDateTime createdAt,
                      LocalDateTime updatedAt,
                      LocalDateTime deletedAt,
                      Long createdBy,
                      Long updatedBy,
                      String firstname,
                      String lastname,
                      String email,
                      String password,
                      Role role,
                      //List<Token> tokens,
                      AddressEntity address,
                      CustomerCardEntity customerCard,
                      Long id,
                      Boolean enabled,
                      Boolean locked) {
        super(id, createdAt, updatedAt, deletedAt, createdBy, updatedBy);
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.address = address;
        this.customerCard = customerCard;
        this.enabled = enabled;
        this.locked = locked;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
