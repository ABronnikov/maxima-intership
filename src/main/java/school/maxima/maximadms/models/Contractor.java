package school.maxima.maximadms.models;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@Table(name = "contractors")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE contractors SET is_removed = true WHERE id=?")
@Where(clause = "is_removed=false")
public class Contractor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "sur_name", nullable = false)
    private String surName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "telegram", nullable = false)
    private String telegram;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "removed_at", nullable = false)
    private LocalDateTime removedAt;

    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credential_id")
    private Credential credential;

    @Column(name = "country", nullable = false)
    private String country;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "contractor_id")
    private List<Comment> comments;

    @Column(name = "is_removed", nullable = false)
    private boolean isRemoved = Boolean.FALSE;
}
