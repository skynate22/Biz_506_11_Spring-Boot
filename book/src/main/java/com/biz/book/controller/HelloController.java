package com.biz.book.controller;

import com.biz.book.domain.BookVO;
import com.biz.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.engine.AttributeName;

import java.util.List;

@Controller
public class HelloController {


		@Qualifier("bookServiceV1")
		private final BookService bService;

	public HelloController(BookService bService) {
		this.bService = bService;
	}

		@ResponseBody
		@RequestMapping(value = "", method = RequestMethod.GET)
		public String hello() {
			
			return "Hello Spring-boot Web Application";
		}
		
		@RequestMapping(value = "/home", method =RequestMethod.GET)
		public String home(Model model) {
			
			model.addAttribute("username","홍길동");
			model.addAttribute("tel","010-111-1111");
			return"home";
		}

		@ResponseBody
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public List<BookVO> getList(){
			List<BookVO> bookList = bService.selectAll();

			return bookList;
		}


	@RequestMapping(value="/car")
	public String car(Model model) {

		BookVO bookVO = BookVO.builder().title("자바야 놀자").author("홍길동").comp("우리동네 서점").build();
		model.addAttribute("BOOK",bookVO);
		return "book/list_ex";

	}

}
