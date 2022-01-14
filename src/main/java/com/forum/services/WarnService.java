package com.forum.services;

import com.forum.model.Warn;
import com.forum.repositories.WarnRepository;
import org.springframework.stereotype.Service;

@Service
public class WarnService {
    private final WarnRepository warnRepository;

    public WarnService(WarnRepository warnRepository) {
        this.warnRepository = warnRepository;
    }

    public Warn save(Warn warn) {
        return warnRepository.save(warn);
    }
}
