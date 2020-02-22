package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.Person;

@Controller
public class JsonController {
	@ResponseBody
	@RequestMapping("/getPerson")
	public Person getPerson() {
		Person person = new Person(100, "1010");
		return person;
	}
	@ResponseBody
	@RequestMapping("/getPersonList")
	public List<Person> getPersonList() {
		List<Person> list = new ArrayList<>();
		list.add(new Person(100, "1010"));
		list.add(new Person(101, "1010"));
		list.add(new Person(102, "1010"));
		return list;
	}
	@ResponseBody
	@RequestMapping("/getPersonMap")
	public Map<String, Object> getPersonMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("1", new Person(100, "1010"));
		map.put("2", new Person(101, "1010"));
		map.put("3", new Person(102, "1010"));
		return map;
	}
	@ResponseBody
	@RequestMapping("/addPerson")
	public Map<String, Object> addPerson(@RequestBody Person person) {
		Map<String, Object> hashMap = new HashMap<>();
		System.out.println("接受到的数据：" + person);
		hashMap.put("result", true);
		return hashMap;
	}
}
