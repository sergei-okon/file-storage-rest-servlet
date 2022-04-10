package ua.com.okonsergei.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
//            (cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Event> events;
}
