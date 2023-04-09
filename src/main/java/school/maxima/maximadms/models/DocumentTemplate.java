package school.maxima.maximadms.models;

import java.time.Instant;
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
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@Table(name = "document_templates")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE document_templates SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class DocumentTemplate {

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

    @Column(name = "version", nullable = false)
    private Integer version;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_templates_id")
    private List<DocumentTemplateField> fields;

    @Column(name = "is_removed", nullable = false)
    private boolean isRemoved = Boolean.FALSE;
}