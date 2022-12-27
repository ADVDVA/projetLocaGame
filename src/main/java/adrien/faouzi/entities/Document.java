package adrien.faouzi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Document", indexes = {
        @Index(name = "idDocumentType", columnList = "idDocumentType"),
        @Index(name = "idOrder", columnList = "idOrder")
})
public class Document implements Serializable {
    private int idDocument;
    private int idDocumentType;
    private int idOrder;
    private int numberDocument;
    private LocalDateTime documentDate;
    private DocumentType documenttypeByIdDocumentType;
    private Order orderByIdOrder;

    @Id
    @Column(name = "idDocument")
    public int getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(int idDocument) {
        this.idDocument = idDocument;
    }

    @Basic
    @Column(name = "idDocumentType")
    public int getIdDocumentType() {
        return idDocumentType;
    }

    public void setIdDocumentType(int idDocumentType) {
        this.idDocumentType = idDocumentType;
    }

    @Basic
    @Column(name = "idOrder")
    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    @Basic
    @Column(name = "numberDocument")
    public int getNumberDocument() {
        return numberDocument;
    }

    public void setNumberDocument(int numberDocument) {
        this.numberDocument = numberDocument;
    }

    @Basic
    @Column(name = "documentDate")
    public LocalDateTime getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(LocalDateTime documentDate) {
        this.documentDate = documentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return idDocument == document.idDocument && idDocumentType == document.idDocumentType && idOrder == document.idOrder && numberDocument == document.numberDocument && Objects.equals(documentDate, document.documentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDocument, idDocumentType, idOrder, numberDocument, documentDate);
    }

    @ManyToOne
    @JoinColumn(name = "idDocumentType", referencedColumnName = "idDocumentType", nullable = false)
    public DocumentType getDocumenttypeByIdDocumentType() {
        return documenttypeByIdDocumentType;
    }

    public void setDocumenttypeByIdDocumentType(DocumentType documenttypeByIdDocumentType) {
        this.documenttypeByIdDocumentType = documenttypeByIdDocumentType;
    }

    @ManyToOne
    @JoinColumn(name = "idOrder", referencedColumnName = "idOrder", nullable = false)
    public Order getOrderByIdOrder() {
        return orderByIdOrder;
    }

    public void setOrderByIdOrder(Order orderByIdOrder) {
        this.orderByIdOrder = orderByIdOrder;
    }
}
