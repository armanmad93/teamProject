package com.example.techthink.service.format.impl;

import com.example.techthink.persistence.entity.format.Format;
import com.example.techthink.persistence.repository.format.FormatRepository;
import com.example.techthink.service.format.FormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FormatServiceImpl implements FormatService {

    private final FormatRepository formatRepository;

    @Autowired
    public FormatServiceImpl(FormatRepository formatRepository) {
        this.formatRepository = formatRepository;
    }

    @Override
    public Format getFormatById(Integer id) {
        return formatRepository.getById(id);
    }
}
