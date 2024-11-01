package org.springproject.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springproject.library.entity.ReserveEntity;
import org.springproject.library.entity.UserEntity;
import org.springproject.library.repository.ReserveRepository;
import org.springproject.library.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReserveService {
    @Autowired
    private ReserveRepository reserveRepository;

    public ReserveEntity addReserve(ReserveEntity reserve) {
        return reserveRepository.save(reserve);
    }

    public Optional<ReserveEntity> getReserveById(String id) {
        return reserveRepository.findById(id);
    }

    public List<ReserveEntity> getAllReserves() {
        return reserveRepository.findAll();
    }

    public ReserveEntity updateReserve(ReserveEntity reserve) {
        return reserveRepository.save(reserve);
    }

    public void deleteReserve(String id) {
        reserveRepository.deleteById(id);
    }
}
