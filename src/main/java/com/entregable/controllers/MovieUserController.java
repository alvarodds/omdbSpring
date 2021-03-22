package com.entregable.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.entregable.models.component.PeliculaComp;
import com.entregable.models.component.UsuarioComp;
import com.entregable.models.entity.Pelicula;
import com.entregable.models.entity.Usuario;
import com.entregable.models.service.IPeliculaService;
import com.entregable.models.service.IUsuarioService;
import com.entregable.models.service.PeticionGetExterna;
import com.google.gson.Gson;

@Controller
@RequestMapping("/omdb")
public class MovieUserController {

	@Autowired
	private Gson gson;

	@Autowired
	private PeticionGetExterna peticion;

	@Autowired 
	private IPeliculaService servicePelicula;

	@Autowired 
	private IUsuarioService serviceUsuario;

	@Autowired 
	private PeliculaComp peli;

	@Autowired 
	private UsuarioComp usuarioComp;



	//INICIO SESION
	@RequestMapping(value = "/formularioUsuario", method = RequestMethod.GET)
	public String crearFormularioUsuario(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		return "formusuario"; 
	}

	//COMPROBAMOS EL INICIO DE SESION
	@RequestMapping(value = "/comprobarusuario", method = RequestMethod.GET)
	public String comprobarUsuario(Model model, Usuario usuario) {
		Usuario usu= serviceUsuario.findOne(usuario.getEmail());
		Usuario usuar = new Usuario();
		if(usu != null) {
			usuarioComp.copiar(usu);
			if((usuario.getEmail().equals(usu.getEmail())) &&
					(usuario.getContrasenia().equals(usu.getContrasenia()))	) {
				return "redirect:buscarpelicula";
			}else {

				model.addAttribute("usuario", usuar);
				model.addAttribute("mensaje", "Usuario o contraseña invalidos");
				return "formusuario"; 
			}
		}
		model.addAttribute("usuario", usuar);
		model.addAttribute("mensaje", "Usuario o contraseña invalidos");
		return "formusuario"; 

	}

	//ALTA NUEVO USUARIO
	@RequestMapping(value = "/formularioAltaUsuario", method = RequestMethod.GET)
	public String crearFormularioAltaUsuario(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		return "formAltaUsuario"; 

	}

	//COMPROBAMOS EL ALTA DEL NUEVO USUARIO
	@RequestMapping(value = "/altaUsuario", method = RequestMethod.GET)
	public String altaUsuario(Model model,Usuario usuario) {
		Usuario usu= serviceUsuario.findOne(usuario.getEmail());
		if(usu==null) {
			serviceUsuario.save(usuario);
			return "redirect:formularioUsuario";
		}else {
			return "redirect:formularioAltaUsuario";
		}

	}
	
	//CERRAR SESION USUARIO
	@RequestMapping(value = "/cerrarsesion", method = RequestMethod.GET)
	public String cerrarSesion(Model model) {

		usuarioComp = new UsuarioComp();
		return "redirect:formularioUsuario";


	}

	//PELICULA
	@RequestMapping(value = "/buscarpelicula", method = RequestMethod.GET)
	public String crearFormulario(Model model) {
		model.addAttribute("pelicula", peli);
		return "verPeliUser"; 

	}

	@RequestMapping(value = "/verpelicula", method = RequestMethod.GET)
	public ModelAndView buscarOMDB(@RequestParam(value = "Title") String titulo) throws IOException {
		String busqueda="http://www.omdbapi.com/?apikey=dcdf1c79";
		busqueda+="&t=" + titulo;
		ModelAndView mav= new ModelAndView("verPeliUser");
		String texto= peticion.sendGET(busqueda);
		System.out.println(texto);
		//Una vez obtengo la respuesta en JSON lo transformo en un objeto de tipo MOVIE
		//GSON es una librería para transformar Json a objetos y al revés.
		//Para utilizarla hay que pasarle el texto(json) y el tipo de objeto al que queremos transformarlo

		Pelicula pelicula = gson.fromJson(texto, Pelicula.class);
		peli.copia(pelicula);
		servicePelicula.save(pelicula);
		mav.addObject("pelicula", peli);


		return mav;
	}

	@RequestMapping(value = "/guardarPeliUsuario", method = RequestMethod.GET)
	public String guardarUsuario(Model model) {

		Pelicula p = new Pelicula();
		Usuario u = new Usuario();
		p.copia(peli);
		u.copiar(usuarioComp);
		u.aniadirPelicula(p);
		serviceUsuario.save(u);
		return "redirect:buscarpelicula";


	}

	@RequestMapping(value = "/verPeliculasGuardadas", method = RequestMethod.GET)
	public String peliculasGuardadasUsuario(Model model) {
		Usuario u = new Usuario();
		u.copiar(usuarioComp);
		model.addAttribute("usuario", u);
		return "formpeliculasguardadas";


	}

	@RequestMapping(value = "/verpeliculaguardada", method = RequestMethod.GET)
	public ModelAndView buscarOMDBguardada(@RequestParam("titulo") String titulo) throws IOException {
		System.out.println(titulo);

		String busqueda="http://www.omdbapi.com/?apikey=dcdf1c79";
		busqueda+="&t=" + titulo;
		ModelAndView mav= new ModelAndView("verPeliUserGuardada");
		String texto= peticion.sendGET(busqueda);
		System.out.println(texto);
		//Una vez obtengo la respuesta en JSON lo transformo en un objeto de tipo MOVIE
		//GSON es una librería para transformar Json a objetos y al revés.
		//Para utilizarla hay que pasarle el texto(json) y el tipo de objeto al que queremos transformarlo

		Pelicula pelicula = gson.fromJson(texto, Pelicula.class);
		System.out.println();
		peli.copia(pelicula);
		mav.addObject("pelicula", peli);


		return mav;
	}






}
