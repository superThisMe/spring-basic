package com.example.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = { "/demo/param.action" })
public class DemoController {

//	@RequestMapping(path = { "param.action" })
//	public String processRequest01(HttpServletRequest req) {
//		
//		String data1 = req.getParameter("data1");
//		System.out.println(data1);
//		
//		return "result"; // -> "/WEB-INF/views/" + "result" + ".jsp"
//	}

//	@RequestMapping(path = { "param.action" }, method = RequestMethod.GET)
	@GetMapping(path = { "param.action" })
	public String processRequest01(String data1, int data2) {

		System.out.println(data1);
		System.out.printf("data2=%d\n", data2);

		return "result"; // -> "/WEB-INF/views/" + "result" + ".jsp"
	}

////	@RequestMapping(path = { "param.action" }, method = RequestMethod.POST)
//	@PostMapping(path = { "param.action" })
//	public String processRequest02(String name, String phone, String email, int age) {
//		
//		System.out.printf("[%s][%s][%s][%d]\n", name, phone, email, age);
//		
//		return "result"; // -> "/WEB-INF/views/" + "result" + ".jsp"
//	}

//	@PostMapping(path = { "param.action" })
//	public String processRequest02(Person person) {
//		
//		System.out.printf("[%s][%s][%s][%d]\n", person.getName(), person.getPhone(), person.getEmail(), person.getAge());
//		
//		return "result";
//	}

//	// controller -> view 로 data 전달 1 : Use Model type
//	@PostMapping(path = { "param.action" })
//	public String processRequest02(Person person, Model model) {
//		
//		System.out.printf("[%s][%s][%s][%d]\n", person.getName(), person.getPhone(), person.getEmail(), person.getAge());
//		
//		// Model type parameter에 data를 저장하면 view로 전달
//		// ( == request 객체에 data를 저장하는 것과 같으 효과)
//		model.addAttribute("myPerson", person);
//		
//		return "result";
//	}

//	// controller -> view 로 data 전달 2 : Use VO parameter (@ModelAttribute) 
//	@PostMapping(path = { "param.action" })
//	public String processRequest02(Person person, // == @ModelAttribute Person person
//									Model model) { 
//		
//		System.out.printf("[%s][%s][%s][%d]\n", person.getName(), person.getPhone(), person.getEmail(), person.getAge());
//	
//		model.addAttribute("myPerson", person);
//		
//		return "result";
//	}

	// controller -> view 로 data 전달 3 : ModelAndView 객체 return
	@PostMapping(path = { "param.action" })
	public ModelAndView processRequest02(Person person, // == @ModelAttribute Person person
			Model model) {

		System.out.printf("[%s][%s][%s][%d]\n", person.getName(), person.getPhone(), person.getEmail(),
				person.getAge());

		model.addAttribute("myPerson", person);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		mav.addObject("myPerson2", person);

		return mav;
	}

	// redirect
	@GetMapping(path = { "redirect.action" })
	public String processRequest4_1() {

		return "redirect:target.action";
		
	}

	@GetMapping(path = { "target.action" })
	public String processRequest4_2() {

		return "target"; // /WEB-INF/views/ + target + .jsp
		
	}
	
	// forward
	@GetMapping(path = { "forward.action" })
	public String processRequest5() {
		
		return "forward:/resources/forward.html";
		
	}

}
