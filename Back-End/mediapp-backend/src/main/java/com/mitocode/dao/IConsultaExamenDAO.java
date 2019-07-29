package com.mitocode.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.ConsultaExamen;

public interface IConsultaExamenDAO extends JpaRepository<ConsultaExamen, Integer> {
	
	//SQL
	//@Transactional // bloque trannsaccionoal solo del query realizado
	@Modifying  //clausula de modificacion
	//ya lo vimos en idat xd solo que en este caso 
	//como es nativo usamos nativeQuery = true
@Query(value = "INSERT INTO consulta_examen(id_consulta, id_examen) VALUES (:idConsulta, :idExamen",nativeQuery = true)
//de esta manera pasamos los parametros
Integer registrar(@Param("idConsulta")Integer idConsulta,@Param("idExamen")Integer idExamen) ;   //poblamios la tabla
}
