package adrien.faouzi.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Documenttype")
public class DocumentType {
    private int idDocumentType;
    private String documentType;
    private List<Document> documentsByIdDocumentType;

    @Id
    @Column(name = "idDocumentType")
    public int getIdDocumentType() {
        return idDocumentType;
    }

    public void setIdDocumentType(int idDocumentType) {
        this.idDocumentType = idDocumentType;
    }

    @Basic
    @Column(name = "documentType")
    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentType that = (DocumentType) o;
        return idDocumentType == that.idDocumentType && Objects.equals(documentType, that.documentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDocumentType, documentType);
    }

    @OneToMany(mappedBy = "documenttypeByIdDocumentType")
    public List<Document> getDocumentsByIdDocumentType() {
        return documentsByIdDocumentType;
    }

    public void setDocumentsByIdDocumentType(List<Document> documentsByIdDocumentType) {
        this.documentsByIdDocumentType = documentsByIdDocumentType;
    }
}
