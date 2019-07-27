package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.service.IConsultaExamenService;
@Service
public class ConsultaExamenServiceImpl implements IConsultaExamenService {
@Autowired
private IConsultaExamenService dao;
}
