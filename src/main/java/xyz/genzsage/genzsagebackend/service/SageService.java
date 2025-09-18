package xyz.genzsage.genzsagebackend.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.genzsage.genzsagebackend.model.Sage;
import xyz.genzsage.genzsagebackend.repository.SageRepository;

import java.util.Optional;


@Service
public class SageService {
private SageRepository sageRepository;
private PasswordEncoder encoder;

    @Autowired
    public SageService(SageRepository sageRepository, PasswordEncoder passwordEncoder) {
        this.sageRepository = sageRepository;
        this.encoder = passwordEncoder;
    }



}
