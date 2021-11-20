package acme.features.manager.duty;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Manager;
import acme.entities.duties.Duty;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/manager/duty/")
public class ManagerTaskController extends AbstractController<Manager, Duty>{

	@Autowired
	private ManagerTaskCreateService createService;
	
	@Autowired
	private ManagerTaskListService listService;
	
	@Autowired
	private ManagerTaskShowService showService;
	
	@Autowired
	private ManagerTaskUpdateService updateService;
	
	@Autowired
	private ManagerTaskDeleteService deleteService;
	
	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
 
	}
	
}
