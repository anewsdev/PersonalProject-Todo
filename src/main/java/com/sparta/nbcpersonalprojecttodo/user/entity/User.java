package com.sparta.nbcpersonalprojecttodo.user.entity;

import com.sparta.nbcpersonalprojecttodo.user.dto.UserRequestDto;
import com.sparta.nbcpersonalprojecttodo.common.entity.Timestamped;
import com.sparta.nbcpersonalprojecttodo.userTodo.entity.UserTodo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserTodo> userTodos = new ArrayList<>();

    public User(UserRequestDto userRequestDto) {
        this.username = userRequestDto.getUsername();
        this.email = userRequestDto.getEmail();
    }
}