package com.centroinformacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centroinformacion.entity.ReservaSala;
import com.centroinformacion.repository.ReservaSalaRepository;

@Service
public class ReservaSalaServiceImpl implements ReservaSalaService{

	@Autowired
	private ReservaSalaRepository reservaSalaRepository;
	
	@Override
	public ReservaSala reservarSala(ReservaSala reservaSala) {
		// TODO Auto-generated method stub
		return reservaSalaRepository.save(reservaSala);
	}

	@Override
	public List<ReservaSala> listReservaSala() {
		// TODO Auto-generated method stub
		return reservaSalaRepository.findAll();
	}

}
