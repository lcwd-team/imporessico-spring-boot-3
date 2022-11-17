package core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.w3c.dom.html.HTMLObjectElement;

import core.controllers.AboutController;
import core.controllers.HomeController;

public class Demo {

	@Autowired
	private Student student;

	public static void main(String[] args) {

		System.out.println("App started");
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
//		Student bean = context.getBean(Student.class);
//		System.out.println(bean);
//
//		Address address = bean.getAddress();
//		System.out.println(address.getStreet());
//		System.out.println(address.getPinCode());
//		
//		Address bean2 = context.getBean(Address.class);
//		System.out.println(bean2.getStreet());

		College bean = context.getBean(College.class);
		System.out.println(bean);
		Student student2 = bean.getStudent();
		System.out.println(student2);
		Address address = student2.getAddress();
		System.out.println(address.getStreet());

		Animal bean2 = context.getBean(Animal.class);
		System.out.println(bean2);

		HomeController homeController = context.getBean(HomeController.class);
		homeController.controlHome();

		AboutController aboutController = context.getBean(AboutController.class);
		aboutController.controlAbout();
		
		HomeController homeControllerName = context.getBean("home",HomeController.class);
		homeControllerName.controlHome();
		
	
		((AnnotationConfigApplicationContext)context).close();
		
		

		
	
		

	}
}
