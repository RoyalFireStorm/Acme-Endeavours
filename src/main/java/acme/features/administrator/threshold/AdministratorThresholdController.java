package acme.features.administrator.threshold;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.spam.Threshold;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/spam/threshold")
public class AdministratorThresholdController extends AbstractController<Administrator, Threshold>{
	
	@Autowired
	protected AdministratorThresholdShowService showService;
	
	@Autowired
	protected AdministratorThresholdUpdateService updateService;
	
	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
 
	}

}
