package com.sxit.campus.campuslink_back_end.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false)
  private Long id;

  @Column(unique = true, nullable = false)
  private String account;

  @Column(nullable = false)
  private String password;

  public enum Gender {
    MALE,
    FEMALE
  }

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Gender gender;

  @Column(nullable = false)
  private String department;

  @Column(name = "class_name", nullable = false)
  private String className;

  @Lob
  @Column(name = "introduction")
  private String introduction;

  public enum Role {
    GRADUATE,
    UNDERGRADUATE
  }

  @Enumerated(EnumType.STRING) // 枚举存储为字符串
  @Column(nullable = false)
  private Role role;
}
