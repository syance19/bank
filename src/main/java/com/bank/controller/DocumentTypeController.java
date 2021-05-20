package com.bank.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bank.domain.DocumentType;
import com.bank.dto.DocumentTypeDTO;
import com.bank.mapper.DocumentTypeMapper;
import com.bank.service.DocumentTypeService;

@RestController
@RequestMapping("/api/v1/document-types")
@CrossOrigin("*")
public class DocumentTypeController {

	@Autowired
	DocumentTypeService documentTypeService;
	
	@Autowired
	DocumentTypeMapper documentTypeMapper;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid DocumentTypeDTO documentTypeDTO) throws Exception {
		DocumentType documentType= documentTypeMapper.documentTypeDTOToDocumentType(documentTypeDTO);
		documentType= documentTypeService.save(documentType);
		documentTypeDTO= documentTypeMapper.documentTypeToDocumentTypeDTO(documentType);
		return ResponseEntity.ok().body(documentTypeDTO);
	}
	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid DocumentTypeDTO documentTypeDTO) throws Exception {
		DocumentType documentType= documentTypeMapper.documentTypeDTOToDocumentType(documentTypeDTO);
		documentType= documentTypeService.update(documentType);
		documentTypeDTO= documentTypeMapper.documentTypeToDocumentTypeDTO(documentType);
		return ResponseEntity.ok().body(documentTypeDTO);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id")  Integer id) throws Exception{
		documentTypeService.deleteById(id);
		return ResponseEntity.ok().body(null);
	}
	
	@GetMapping
	public ResponseEntity<?> findAll() throws Exception{
	List<DocumentType> documentsType= documentTypeService.findAll();
	List<DocumentTypeDTO> documentTypeDTOs= documentTypeMapper.listDocumentTypeToListDocumentTypeDTO(documentsType);
	return ResponseEntity.ok().body(documentTypeDTOs);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id")  Integer id) throws Exception{
		DocumentType documentType=null;
		DocumentTypeDTO documentTypeDTO=null;
		Optional<DocumentType> documentTypeOptional= documentTypeService.findById(id);
		if(documentTypeOptional.isPresent()==false) {
			return ResponseEntity.ok().body(null);
		}
		documentType=documentTypeOptional.get();
		documentTypeDTO=documentTypeMapper.documentTypeToDocumentTypeDTO(documentType);
		return ResponseEntity.ok().body(documentTypeDTO);
		
	}
}
