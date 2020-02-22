package com.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sun.media.sound.ModelAbstractChannelMixer;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xerces.internal.parsers.IntegratedParserConfiguration;

@SessionAttributes(types=Integer.class)
//@SessionAttributes(value="mapKey1")
@Controller
public class MapController {
	
	
	
	@RequestMapping("/mapAndModelAndModelMap")
	public String mapAndModelAndModelMap(Map<String, Object> map, Model model, ModelMap modelMap) {
		System.out.println(map);
		System.out.println(model);
		System.out.println(modelMap);
		System.out.println("_____________");
		
		System.out.println(map.getClass());
		System.out.println(model.getClass());
		System.out.println(modelMap.getClass());
		System.out.println("_____________");
		
		map.put("mapK1", "mapV1");
		System.out.println(map);
		System.out.println(model);
		System.out.println(modelMap);
		System.out.println("_____________");
		
		return "mapandmodelandmodelMap";
	}
	
	@RequestMapping("/map2RequestScope")
	public String map2RequestScope(Map<String, Object> map) {
		System.out.println("map2RequestScope() is used");

		map.put("mapKey1", "mapValue1");
		map.put("mapKey2", "mapValue2");
		map.put("mapKey3", new Integer(100));

		return "model";
	}
	
	@RequestMapping("/model2RequestScope")
	public ModelAndView model2RequestScope(Model model) {
		System.out.println("model2RequestScope() is used");
//		保存到Map中的数据,会自动的同步到Reqeust域中
		model.addAttribute("modelKey1", "modelValue1");
		model.addAttribute("modelKey2", "modelValue2");

		ModelAndView modelAndView = new ModelAndView("model");
		return modelAndView;
	}
	@RequestMapping("/modelMap2RequestScope")
	public ModelAndView modelMap2RequestScope(ModelMap modelMap) {
		System.out.println("modelMap2RequestScope() is used");
//		保存到Map中的数据,会自动的同步到Reqeust域中
		modelMap.addAttribute("modelMapKey1", "modelMapValue1");
		modelMap.addAttribute("modelMapKey2", "modelMapValue2");
	
		return new ModelAndView("model");
	}
	@RequestMapping("/modelAndView2RequestScope")
	public ModelAndView modelAndView2RequestScope() {
		System.out.println("modelAndView2RequestScope() is used");
//		保存到Map中的数据,会自动的同步到Reqeust域中
		ModelAndView modelAndView = new ModelAndView("model");
		modelAndView.addObject("modelAndViewKey1", "modelAndViewValue1");
		modelAndView.addObject("modelAndViewKey2", "modelAndViewValue2");

		return modelAndView;
	}
}
