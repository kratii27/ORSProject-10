package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.AttachmentDAOInt;
import com.rays.dto.AttachmentDTO;

/**
 * Service implementation class for managing attachment operations.
 * This class provides the business logic implementation for attachment-related
 * operations by extending BaseServiceImpl and implementing AttachmentServiceInt.
 * It handles attachment data persistence with transactional support.
 * 
 * @author Krati Trivedi
 */
@Service
@Transactional
public class AttachmentServiceImpl extends BaseServiceImpl<AttachmentDTO, AttachmentDAOInt>
		implements AttachmentServiceInt {

}