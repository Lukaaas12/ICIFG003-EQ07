package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.ProtocoloEntity;
import com.example.demo.interfaces.IProtocoloService;
import com.example.demo.repository.ProtocoloRepository;

@Service
public class ProtocoloServiceImpl implements IProtocoloService {

	@Autowired
	private ProtocoloRepository repoProtocolo;
	
	@Override
	public List<ProtocoloEntity> findAll() {
		return (List<ProtocoloEntity>) repoProtocolo.findAll();
	}

	@Override
	public ProtocoloEntity findById(Long id) {
		return repoProtocolo.findById(id).orElse(null);
	}

	@Override
	public ProtocoloEntity save(ProtocoloEntity protocolo) {
		return repoProtocolo.save(protocolo);
	}

	@Override
	public void deleteById(long id) {
		repoProtocolo.deleteById(id);
	} 
}