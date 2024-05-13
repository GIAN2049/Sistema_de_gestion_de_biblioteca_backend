package com.centroinformacion.service;

import java.util.List;

import com.centroinformacion.entity.ReservaSala;

public interface ReservaSalaService {
	public abstract ReservaSala reservarSala(ReservaSala reservaSala);
	public abstract List<ReservaSala> listReservaSala();
}
