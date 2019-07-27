package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Paciente;

public interface ICRUD<T>{//genericos t= type  kv=kivalue  //variantes de genericos ver
	T registrar(T t);
	T modificar(T t);
	void eliminar(int id);
	T listarId(int id);
	List<T>listar();
}
