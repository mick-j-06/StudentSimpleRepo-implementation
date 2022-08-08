package com.example.helloworldapi.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(unique = true, length = 100)
    private String email;
    @Column(nullable = false)
    private String birthday;

    public Student(String name, String email, String birthday) {
        this.name = name;
        this.email = email;
        this.birthday = birthday;
    }
}
