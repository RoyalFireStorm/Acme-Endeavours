package acme.features.officer.duty;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.duties.Duty;
import acme.entities.roles.Officer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/officer/duty/")
public class OfficerTaskController extends AbstractController<Officer, Duty>{

	@Autowired
	private OfficerTaskCreateService createService;
	
	@Autowired
	private OfficerTaskListService listService;
	
	@Autowired
	private OfficerTaskShowService showService;
	
	@Autowired
	private OfficerTaskUpdateService updateService;
	
	@Autowired
	private OfficerTaskDeleteService deleteService;
	
	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
 
	}
	
}
