package ru.itpark.secureside.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TokenEntity {
  @Id
  private String id;
  @ManyToOne(optional = false)
  private UserEntity user;
}
