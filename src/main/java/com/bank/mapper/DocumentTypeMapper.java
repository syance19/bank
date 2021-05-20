package com.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.bank.domain.DocumentType;
import com.bank.dto.DocumentTypeDTO;

@Mapper
public interface DocumentTypeMapper {
	public DocumentTypeDTO documentTypeToDocumentTypeDTO(DocumentType documentType);

	public DocumentType documentTypeDTOToDocumentType(DocumentTypeDTO documentTypeDTO);

	public List<DocumentTypeDTO> listDocumentTypeToListDocumentTypeDTO(List<DocumentType> documentTypes);

	public List<DocumentType> listDocumentTypeDTOToListDocumentType(List<DocumentTypeDTO> documentTypeDTOs);
}
