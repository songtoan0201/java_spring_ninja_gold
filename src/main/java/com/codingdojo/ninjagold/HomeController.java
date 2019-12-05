package com.codingdojo.ninjagold;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String index(HttpSession session) {
		if (session.getAttribute("total_gold") == null)
			session.setAttribute("total_gold", 0);
		return "index.jsp";
	}

	@RequestMapping(value = "/process_money", method = RequestMethod.POST)
	public String processMoney(@RequestParam String which_form, HttpSession session, HttpServletRequest request) {
		ArrayList<String> strArr = null;
		if (session.getAttribute("activities") == null) {
			strArr = new ArrayList<String>();
		} else {
			strArr = (ArrayList<String>) session.getAttribute("activities");
		}
		
		Random r = new Random();
		DateFormat dateFormat = new SimpleDateFormat("MMMM dd, YYYY, hh:mm aa");
		Date date = new Date();
		session.setAttribute("dateFormat", dateFormat.format(date)); 
		if(request.getParameter("which_form").equals("farm")) {
			session.setAttribute("farm_gold", r.nextInt(11) + 10 );	
			session.setAttribute("total_gold", (int)session.getAttribute("total_gold") + (int)session.getAttribute("farm_gold"));		
			strArr.add(0,String.format("<option style='color:green'>Earn %d from farming (%s)</option>", session.getAttribute("farm_gold"), session.getAttribute("dateFormat") ));
			session.setAttribute("activities", strArr);	
		} else if(request.getParameter("which_form").equals("cave")) {
			session.setAttribute("cave_gold", r.nextInt(6) + 5 );	
			session.setAttribute("total_gold", (int)session.getAttribute("total_gold") + (int)session.getAttribute("cave_gold"));		
			strArr.add(0,String.format("<option style='color:green'>Earn %d from digging cave (%s)</option>", session.getAttribute("cave_gold"), session.getAttribute("dateFormat") ));
			session.setAttribute("activities", strArr);		
		} else if(request.getParameter("which_form").equals("house")) {
			session.setAttribute("house_gold", r.nextInt(6) + 5 );	
			session.setAttribute("total_gold", (int)session.getAttribute("total_gold") + (int)session.getAttribute("house_gold"));		
			strArr.add(0,String.format("<option style='color:green'>Earn %d from searching houses (%s)</option>", session.getAttribute("house_gold"), session.getAttribute("dateFormat") ));
			session.setAttribute("activities", strArr);		
		} else if(request.getParameter("which_form").equals("casino")) {
			if ((int)session.getAttribute("total_gold") > 0) {
				session.setAttribute("casino_gold", r.nextInt(101) - 50);	
				session.setAttribute("total_gold", (int)session.getAttribute("total_gold") + (int)session.getAttribute("casino_gold"));		
				if ((int)session.getAttribute("casino_gold") < 0) {
					strArr.add(0,String.format("<option style='color:red'>Lose %d from gambling (%s)</option>", -(int)session.getAttribute("casino_gold"), session.getAttribute("dateFormat") ));
					session.setAttribute("activities", strArr);		
				}
				else {
					strArr.add(0,String.format("<option style='color:green'>Earn %d from gambling (%s)</option>", session.getAttribute("casino_gold"), session.getAttribute("dateFormat") ));
					session.setAttribute("activities", strArr);	
				}
			} else {
				strArr.add(0,String.format("<option style='color:red'>You have no money to enter casino (%s)</option>", session.getAttribute("dateFormat") ));
				session.setAttribute("activities", strArr);	
			}			
		}
//		System.out.println("------------------");
//		System.out.println(session.getAttribute("activities"));
		
//		//print all session
//		Enumeration<String> attributes = request.getSession().getAttributeNames();
//		while (attributes.hasMoreElements()) {
//		    String attribute = (String) attributes.nextElement();
//		    System.out.println(attribute+" : "+request.getSession().getAttribute(attribute));
//		}

		return "redirect:/";
	}
	
	@RequestMapping("/reset")
	public String reset(HttpSession session) {
		if (session != null)
			session.invalidate();
		return "redirect:/";
	}

}
