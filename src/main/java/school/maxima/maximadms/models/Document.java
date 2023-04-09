package school.maxima.maximadms.models;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@Table(name = "documents")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE documents SET is_removed = true WHERE id=?")
@Where(clause = "is_removed=false")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "removed_at", nullable = false)
    private LocalDateTime removedAt;

    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    @Column(name = "internal_registry_number", nullable = false)
    private String internalRegistryNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private DocumentTemplate template;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;

    @OneToMany(mappedBy = "document")
    private List<File> files;

    @Column(name = "is_removed", nullable = false)
    private boolean isRemoved = Boolean.FALSE;
}
