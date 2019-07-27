package com.mitocode.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.dao.IConsultaDAO;
import com.mitocode.dao.IConsultaExamenDAO;
import com.mitocode.dto.ConsultaListaExamenDTO;
import com.mitocode.model.Consulta;
import com.mitocode.service.IConsultaService;
@Service
//@Transactional
public class ConsultaServiceImpl implements IConsultaService {
	@Autowired
	private IConsultaExamenDAO ceDAO;
	@Autowired
	private IConsultaDAO dao;
	@Override
	public Consulta registrar(Consulta consulta) {
		consulta.getDetalleConsulta().forEach(det -> det.setConsulta(consulta));//para cada detalle consulta pobla la referencia cada consulta el mismo objeto
		return dao.save(consulta);
	}

	@Override
	public Consulta modificar(Consulta t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Consulta listarId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Consulta> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional // se hace un rolbalk de forma automatica
	@Override
	public Consulta registrarTransaccional(ConsultaListaExamenDTO dto) {
		// TODO Auto-generated method stub
		//la inserccion a maestro detalle
		dto.getConsulta().getDetalleConsulta().forEach(det -> det.setConsulta(dto.getConsulta()));
		//iterando la lista de examen que viene en el json para insertar uno a uno en la tabla consulta examen
		dao.save(dto.getConsulta());
		dto.getLstExamen().forEach(e -> ceDAO.registrar(dto.getConsulta().getIdConsulta(), e.getIdExamen()));
		
		return dto.getConsulta();
	}
	

}
