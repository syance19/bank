package com.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.bank.domain.DocumentType;

import com.bank.repository.DocumentTypeRepository;


@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

	@Autowired
	DocumentTypeRepository documentTypeRepository;

	@Autowired
	Validator validator;

	@Override

	public void validate(DocumentType entity) throws Exception {
		Set<ConstraintViolation<DocumentType>> constrainsViolations = validator.validate(entity);
		if (!constrainsViolations.isEmpty()) {
			throw new ConstraintViolationException(constrainsViolations);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<DocumentType> findAll() {

		return documentTypeRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DocumentType save(DocumentType entity) throws Exception {
		if (entity == null) {
			throw new Exception("El customer es nulo");
		}
		validate(entity);
		if (documentTypeRepository.existsById(entity.getDotyId()) == true) {
			throw new Exception("El document type con id" + entity.getDotyId() + "ya existe");
		}
		return documentTypeRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DocumentType update(DocumentType entity) throws Exception {
		if (entity == null) {
			throw new Exception("El customer es nulo");
		}
		validate(entity);
		if (documentTypeRepository.existsById(entity.getDotyId()) == false) {
			throw new Exception("El customer con id" + entity.getDotyId() + "no existe");
		}
		return documentTypeRepository.save(entity);
	}

	@Override
	public void delete(DocumentType entity) throws Exception {
		if (entity == null) {
			throw new Exception("El campo es nulo");
		}

		if (entity.getDotyId() == null) {
			throw new Exception("El id es nulo");
		}

		if (documentTypeRepository.existsById(entity.getDotyId()) == false) {
			throw new Exception("El document type con id" + entity.getDotyId() + "no existe");
		}

	
		documentTypeRepository.deleteById(entity.getDotyId());

	}

	@Override
	public void deleteById(Integer id) throws Exception {
		if(id==null|| id<=0) {
			throw new Exception("Id no valido o es nulo");
		}
		if(documentTypeRepository.existsById(id)==false) {
			throw new Exception("El document type con id" + id + "no existe");
		}
		delete(documentTypeRepository.findById(id).get());
	}

	

	@Override
	public Optional<DocumentType> findById(Integer id) throws Exception {
		return documentTypeRepository.findById(id);
	}

	@Override
	public Long count() {
		return documentTypeRepository.count();
	}

}
