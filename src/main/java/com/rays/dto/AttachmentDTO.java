package com.rays.dto;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import com.rays.common.BaseDTO;

/**
 * Data Transfer Object (DTO) representing a file attachment entity in the ORS application.
 * <p>
 * Maps to the {@code ST_ATTACHMENT} database table and stores file metadata
 * such as name, type, and description, along with the binary file content
 * and the ID of the associated user. Used primarily for storing and retrieving
 * user profile pictures and other uploaded documents.
 * </p>
 *
 * @author Krati Trivedi
 */
@Entity
@Table(name = "ST_ATTACHMENT")
public class AttachmentDTO extends BaseDTO {

    /**
     * The original file name of the attachment.
     */
    @Column(name = "NAME", length = 100)
    protected String name = null;

    /**
     * The MIME content type of the attachment (e.g., {@code image/jpeg}).
     */
    @Column(name = "TYPE", length = 100)
    protected String type = null;

    /**
     * A brief description of the attachment (e.g., "profile pic").
     */
    @Column(name = "DESCRIPTION", length = 500)
    protected String description = null;

    /**
     * The ID of the user associated with this attachment.
     */
    @Column(name = "USER_ID")
    protected Long userId = null;

    /**
     * The binary content of the uploaded file, stored as a Large Object (LOB).
     */
    @Lob
    @Column(name = "DOC")
    private byte[] doc;

    /**
     * Default constructor that creates an empty {@code AttachmentDTO}.
     */
    public AttachmentDTO() {
    }

    /**
     * Constructs an {@code AttachmentDTO} from a {@link MultipartFile}.
     * <p>
     * Extracts the original file name, content type, and byte content
     * from the provided multipart file.
     * </p>
     *
     * @param file the {@link MultipartFile} to initialize this DTO from
     */
    public AttachmentDTO(MultipartFile file) {
        name = file.getOriginalFilename();
        type = file.getContentType();
        try {
            doc = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the original file name of the attachment.
     *
     * @return the file name as a {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the original file name of the attachment.
     *
     * @param name the file name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the MIME content type of the attachment.
     *
     * @return the content type as a {@link String}
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the MIME content type of the attachment.
     *
     * @param type the content type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the description of the attachment.
     *
     * @return the description as a {@link String}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the attachment.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the ID of the user associated with this attachment.
     *
     * @return the user ID as a {@link Long}
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user associated with this attachment.
     *
     * @param userId the user ID to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Returns the display value for dropdown list usage.
     * <p>
     * Not applicable for attachments; always returns {@code null}.
     * </p>
     *
     * @return {@code null}
     */
    public String getValue() {
        return null;
    }

    /**
     * Returns the binary content of the uploaded file.
     *
     * @return the file content as a byte array
     */
    public byte[] getDoc() {
        return doc;
    }

    /**
     * Sets the binary content of the uploaded file.
     *
     * @param doc the file content as a byte array
     */
    public void setDoc(byte[] doc) {
        this.doc = doc;
    }

    /**
     * Returns the unique key attribute name for this entity.
     * <p>
     * Not applicable for attachments; always returns {@code null}.
     * </p>
     *
     * @return {@code null}
     */
    @Override
    public String getUniqueKey() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Returns the unique key value for this entity.
     * <p>
     * Not applicable for attachments; always returns {@code null}.
     * </p>
     *
     * @return {@code null}
     */
    @Override
    public String getUniqueValue() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Returns the human-readable label for this entity.
     * <p>
     * Not applicable for attachments; always returns {@code null}.
     * </p>
     *
     * @return {@code null}
     */
    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Returns the database table name associated with this entity.
     * <p>
     * Not applicable for attachments; always returns {@code null}.
     * </p>
     *
     * @return {@code null}
     */
    @Override
    public String getTableName() {
        // TODO Auto-generated method stub
        return null;
    }
}