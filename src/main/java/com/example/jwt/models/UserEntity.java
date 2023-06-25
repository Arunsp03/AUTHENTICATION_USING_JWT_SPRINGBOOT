package com.example.jwt.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "users"
)
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private  String username;
    private String password;
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable( name="user_roles",
    joinColumns = @JoinColumn(
            name="user_id",
            referencedColumnName = "id"
    ),
            inverseJoinColumns = @JoinColumn(
                    name="role",
                    referencedColumnName = "id"
            )
    )
    private List<Roles> roles= new ArrayList<>();

}
