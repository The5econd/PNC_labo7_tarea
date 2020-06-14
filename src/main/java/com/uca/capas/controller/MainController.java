package com.uca.capas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.dao.EstudianteDAO;
import com.uca.capas.domain.Estudiante;
import com.uca.capas.repositories.EstudianteRepo;
import com.uca.capas.service.EstudianteService;

@Controller
public class MainController {
	@Autowired
	private EstudianteService estudianteS;
	
	@RequestMapping("/")
	public ModelAndView initMain() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("estudiante",new Estudiante());
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/listado")
	public ModelAndView listado(){
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		try{
			estudiantes = estudianteS.findAll();
		}catch(Exception e){
			e.printStackTrace();
		}
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("listado");
		return mav;
	}
	
	
	@RequestMapping("/modificar")
 	public ModelAndView modificar(@RequestParam(value = "ID")int ID) {
 		ModelAndView mav = new ModelAndView();
 		Estudiante es =  null;
        try {
            es = estudianteS.findOne(ID);
            mav.addObject("estudiante", es);
        }catch( Exception e1){
            e1.printStackTrace();
        }
        mav.setViewName("modificarEstudiante");
        return mav;
 	}
	
	@RequestMapping("/insertar")
	public ModelAndView insertEstudiante(@Valid @ModelAttribute Estudiante student, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		if(result.hasErrors() == false) {
			mav.addObject("estudiante", new Estudiante());
			try {
				estudianteS.insert(student);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mav;
	}
	
    @RequestMapping("/modificar2")
    public ModelAndView modificar2(@Valid @ModelAttribute Estudiante estudiante ,BindingResult result) {
        ModelAndView mav = new ModelAndView();
        List<Estudiante> estudiantes = null;
        if(!result.hasErrors()) {
            try {
                estudianteS.save(estudiante);
            }catch(Exception e) {
                e.printStackTrace();
            }
            try {
                estudiantes = estudianteS.findAll();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            mav.addObject("estudiantes", estudiantes);
            mav.setViewName("listado");
        }
        else {
        	mav.setViewName("modificarEstudiante");
        }
        return mav;

    }
}
