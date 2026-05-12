package com.example.demo.interfaces;

import java.util.List;
import com.example.demo.entity.ProtocoloEntity;

public interface IProtocoloService {
	public List<ProtocoloEntity> findAll();
	public ProtocoloEntity findById(Long id);
	public ProtocoloEntity save(ProtocoloEntity protocolo);
	void deleteById(long id);
}