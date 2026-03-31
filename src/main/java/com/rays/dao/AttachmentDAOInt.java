package com.rays.dao;

import com.rays.common.BaseDAOInt;
import com.rays.dto.AttachmentDTO;

/**
 * DAO interface for Attachment-related data access operations in the ORS application.
 * <p>
 * Extends {@link BaseDAOInt} parameterized with {@link AttachmentDTO} to inherit
 * standard CRUD and search method contracts for the Attachment entity.
 * </p>
 *
 * @author Krati Trivedi
 */
public interface AttachmentDAOInt extends BaseDAOInt<AttachmentDTO> {
}