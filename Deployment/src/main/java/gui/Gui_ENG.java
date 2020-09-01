package gui;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.view.Viewable;

import interfaces.IAuthentication;
import interfaces.IData;
import shared.IOps;
import io.ciera.runtime.summit.interfaces.IMessage;

@Path("/eng")
public class Gui_ENG extends Gui{
	
	
	List<String> data = new ArrayList<String>();
	List<String> type = new ArrayList<String>();
   

  
    @Context ServletContext servletContext;
	
    
    @GET
    @Path("/")
    @Produces({MediaType.TEXT_HTML})
    public InputStream web() {
    	
    	if(Gui.i == 0) {
       	 Gui.connHandler = new ConnectionHandler(this);
   		 Gui.connHandler.start();
   		 Gui.i++;
   		 
   		 try {
   			 //Runtime.getRuntime().exec(new String[]{"sh","/run.sh"});
			 Thread.sleep(5000);
   			 Gui.server.sendSignal(new IData.Initialize());
   			 
   		 }catch(Exception e) {
   			 Gui.i = 0;
   			 System.out.print(e.getMessage());
   		 }
    	}
    	
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/login.html");
    }
    
    
    
    @GET
    @Path("/base")
    @Produces({MediaType.TEXT_HTML})
    public InputStream base() {
    	return servletContext.getResourceAsStream("/WEB-INF/erp-theme/templates/erp_eng/base.html");
    }
    
    @GET
    @Path("/basejs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream basejs() {
    	return servletContext.getResourceAsStream("/WEB-INF/erp-theme/templates/erp_eng/base.js");
    }
    
    @GET
    @Path("/bootstrap")
    @Produces({MediaType.TEXT_HTML})
    public InputStream bootstrap() {
    	return servletContext.getResourceAsStream("/WEB-INF/erp-theme/static/erp/css/bootstrap.css");
    }
    
    @GET
    @Path("/headercss")
    @Produces({MediaType.TEXT_HTML})
    public InputStream headercss() {
    	return servletContext.getResourceAsStream("/WEB-INF/erp-theme/static/erp/css/header.css");
    }
    
    @GET
    @Path("/stylecss")
    @Produces({MediaType.TEXT_HTML})
    public InputStream stylecss() {
    	return servletContext.getResourceAsStream("/WEB-INF/erp-theme/static/erp/css/style.css");
    }
    
    @GET
    @Path("/hrs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream hrs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/home.html");
    }
    
    @GET
    @Path("/homejs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream homejs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/home.js");
    }
    
    @GET
    @Path("/hr_form")
    @Produces({MediaType.TEXT_HTML})
    public InputStream hr_form() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/hr_form.html");
    }
    
    @GET
    @Path("/staff_home")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/staff_home.html");
    }
    
    @GET
    @Path("/staff_list")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_list() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/staff_list.html");
    }
    
    @GET
    @Path("/staff_listjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_listjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/staff_list.js");
    }
    
    @GET
    @Path("/staff_detail")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_detail() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/staff_detail.html");
    }
    
    @GET
    @Path("/staff_detailjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_detailjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/staff_detail.js");
    }
    
    @GET
    @Path("/staff_delete")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_delete() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/staff_confirm_delete.html");
    }
    
    @GET
    @Path("/staff_form")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_form() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/staff_form.html");
    }
    
    @GET
    @Path("/staff_formjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_formjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/staff_form.js");
    }
    
    @GET
    @Path("/staff_pay")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_pay() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/staff_pay.html");
    }
    
    @GET
    @Path("/staff_leave")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_leave() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/staff_leave_form.html");
    }
    
    @GET
    @Path("/staff_leavejs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_leavejs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/staff_leave.js");
    }
    
    @GET
    @Path("/staff_commence")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_commence() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/staff_commence.html");
    }
    
    @GET
    @Path("/staff_commencejs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_commencejs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/staff_commence.js");
    }
   
    @GET
    @Path("/staff_bonus_list")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_bonus_list() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/staff_bonus_list.html");
    }
    
    @GET
    @Path("/staff_bonus_detail")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_bonus_detail() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/staff_bonus_detail.html");
    }
    
    @GET
    @Path("/staff_bonus_form")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_bonus_form() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/staff_bonus_form.html");
    }
    
    @GET
    @Path("/staff_bonus_formjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_bonus_formjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/staff_bonus_form.js");
    }
    
    @GET
    @Path("/staff_bonus_delete")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_bonus_delete() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/staff_bonus_confirm_delete.html");
    }
    
    @GET
    @Path("/manageLeave")
    @Produces({MediaType.TEXT_HTML})
    public InputStream manageLeave() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/manage_leave.html");
    }
    
    @GET
    @Path("/manageLeavejs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream manageLeavejs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/manage_leave.js");
    }
   
    @GET
    @Path("/register")
    @Produces({MediaType.TEXT_HTML})
    public InputStream register() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/register.html");
    }
    
    @GET
    @Path("/register_done")
    @Produces({MediaType.TEXT_HTML})
    public InputStream register_done() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/register_done.html");
    }
    
    
    @GET
    @Path("/leave_home")
    @Produces({MediaType.TEXT_HTML})
    public InputStream leave_home() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/leave_home.html");
    }
    
    @GET
    @Path("/leave_list")
    @Produces({MediaType.TEXT_HTML})
    public InputStream leave_list() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/leave_list.html");
    }
    
    @GET
    @Path("/leave_detail")
    @Produces({MediaType.TEXT_HTML})
    public InputStream leave_detail() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/leave_detail.html");
    }
    
    @GET
    @Path("/leave_form")
    @Produces({MediaType.TEXT_HTML})
    public InputStream leave_form() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/leave_form.html");
    }
    
    @GET
    @Path("/leave_formjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream leave_formjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/leave_form.js");
    }
    
    @GET
    @Path("/leave_delete")
    @Produces({MediaType.TEXT_HTML})
    public InputStream leave_delete() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/leave_confirm_delete.html");
    }
    
    @GET
    @Path("/leave_deletejs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream leave_deletejs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/leave_delete.js");
    }
    
    @GET
    @Path("/staff_return")
    @Produces({MediaType.TEXT_HTML})
    public InputStream leave_return() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/staff_return.html");
    }
    
    @GET
    @Path("/staff_returnjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream leave_returnjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/staff_return.js");
    }
    
    @GET
    @Path("/grade_home")
    @Produces({MediaType.TEXT_HTML})
    public InputStream grade_home() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/grade_home.html");
    }
    
    @GET
    @Path("/grade_list")
    @Produces({MediaType.TEXT_HTML})
    public InputStream grade_list() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/grade_list.html");
    }
    
    @GET
    @Path("/grade_detail")
    @Produces({MediaType.TEXT_HTML})
    public InputStream grade_detail() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/grade_detail.html");
    }
    
    @GET
    @Path("/grade_form")
    @Produces({MediaType.TEXT_HTML})
    public InputStream grade_form() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/grade_form.html");
    }
    
    @GET
    @Path("/grade_formjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream grade_formjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/grade_form.js");
    }
    
    @GET
    @Path("/grade_delete")
    @Produces({MediaType.TEXT_HTML})
    public InputStream grade_delete() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/grade_confirm_delete.html");
    }
    
    @GET
    @Path("/bonus_list")
    @Produces({MediaType.TEXT_HTML})
    public InputStream bonus_list() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/bonus_list.html");
    }
    
    @GET
    @Path("/bonus_detail")
    @Produces({MediaType.TEXT_HTML})
    public InputStream bonus_detail() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/bonus_detail.html");
    }
    
    @GET
    @Path("/bonus_form")
    @Produces({MediaType.TEXT_HTML})
    public InputStream bonus_form() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/bonus_form.html");
    }
    
    @GET
    @Path("/bonus_formjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream bonus_formjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/bonus_form.js");
    }
    
    @GET
    @Path("/bonus_delete")
    @Produces({MediaType.TEXT_HTML})
    public InputStream bonus_delete() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/bonus_confirm_delete.html");
    }
    
    @GET
    @Path("/step_form")
    @Produces({MediaType.TEXT_HTML})
    public InputStream step_form() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/step_form.html");
    }
    
    @GET
    @Path("/step_formjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream step_formjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/step_form.js");
    }
    
    @GET
    @Path("/job_home")
    @Produces({MediaType.TEXT_HTML})
    public InputStream job_home() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/job_home.html");
    }
    
    @GET
    @Path("/job_list")
    @Produces({MediaType.TEXT_HTML})
    public InputStream job_list() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/job_list.html");
    }
    
    @GET
    @Path("/job_detail")
    @Produces({MediaType.TEXT_HTML})
    public InputStream job_detail() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/job_detail.html");
    }
    
    @GET
    @Path("/job_form")
    @Produces({MediaType.TEXT_HTML})
    public InputStream job_form() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/job_form.html");
    }
    
    @GET
    @Path("/job_formjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream job_formjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/job_form.js");
    }
    
    @GET
    @Path("/job_delete")
    @Produces({MediaType.TEXT_HTML})
    public InputStream job_delete() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/job_confirm_delete.html");
    }
    
    @GET
    @Path("/job_to_staff_form")
    @Produces({MediaType.TEXT_HTML})
    public InputStream job_to_staff_form() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/job_to_staff_form.html");
    }
    
    @GET
    @Path("/job_to_staff_formjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream job_to_staff_formjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/job_to_staff_form.js");
    }
    
    @GET
    @Path("/pay_home")
    @Produces({MediaType.TEXT_HTML})
    public InputStream pay_home() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/pay_home.html");
    }
    
    @GET
    @Path("/pay_list")
    @Produces({MediaType.TEXT_HTML})
    public InputStream pay_list() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/pay_list.html");
    }
    
    @GET
    @Path("/payjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream payjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/pay.js");
    }
    
    @GET
    @Path("/login")
    @Produces({MediaType.TEXT_HTML})
    public InputStream login() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/login.html");
    }
    
    @GET
    @Path("/loginjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream loginjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/login.js");
    }
    
    @GET
    @Path("/new_account")
    @Produces({MediaType.TEXT_HTML})
    public InputStream new_account() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/new_account.html");
    }
    
    @GET
    @Path("/new_accountjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream new_accountjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/new_account.js");
    }
    
    @GET
    @Path("/overtime_home")
    @Produces({MediaType.TEXT_HTML})
    public InputStream overtime_home() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/overtime_home.html");
    }
    
    @GET
    @Path("/overtime_list")
    @Produces({MediaType.TEXT_HTML})
    public InputStream overtime_list() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/overtime_list.html");
    }
    
    @GET
    @Path("/overtime_detail")
    @Produces({MediaType.TEXT_HTML})
    public InputStream overtime_detail() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/overtime_detail.html");
    }
    
    @GET
    @Path("/overtime_form")
    @Produces({MediaType.TEXT_HTML})
    public InputStream overtime_form() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/overtime_form.html");
    }
    
    @GET
    @Path("/overtime_formjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream overtime_formjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/overtime_form.js");
    }
    
    @GET
    @Path("/overtime_delete")
    @Produces({MediaType.TEXT_HTML})
    public InputStream overtime_delete() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/overtime_confirm_delete.html");
    }
    
    @GET
    @Path("/department_home")
    @Produces({MediaType.TEXT_HTML})
    public InputStream department_home() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/department_home.html");
    }
    
    @GET
    @Path("/department_list")
    @Produces({MediaType.TEXT_HTML})
    public InputStream department_list() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/department_list.html");
    }
    
    @GET
    @Path("/department_detail")
    @Produces({MediaType.TEXT_HTML})
    public InputStream department_detail() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/department_detail.html");
    }
    
    @GET
    @Path("/department_form")
    @Produces({MediaType.TEXT_HTML})
    public InputStream department_form() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/department_form.html");
    }
    
    @GET
    @Path("/department_formjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream department_formjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/department_form.js");
    }
    
    @GET
    @Path("/department_delete")
    @Produces({MediaType.TEXT_HTML})
    public InputStream department_delete() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/department_confirm_delete.html");
    }
    
    
    @POST
	@Path("/saveEmployee")
	@Consumes(MediaType.APPLICATION_JSON)
	public InputStream saveEmploye(Employee employee) {
		
        Gui.server.sendSignal(new IData.CreateEmployee(employee.employeeID, employee.nationalID, employee.firstName, employee.middleName, employee.lastName, employee.dateOfBirth, employee.degree, employee.gender));
        data.add(employee.employeeID+"/"+employee.nationalID+"/"+employee.firstName+"/"+employee.middleName+"/"+employee.lastName+"/"+employee.dateOfBirth+"/"+employee.degree+"/"+employee.gender);
    	type.add("employee");
		return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/home.html");	
	} 
    
    
    @POST
  	@Path("/saveLeave")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public InputStream saveLeave(Leave leave) {
  		
    	Gui.server.sendSignal(new IData.CreateLeaveSpecification(leave.name, leave.maximumDays, leave.minimumDays));
        data.add(leave.name+"/"+leave.maximumDays+"/"+leave.minimumDays);
    	type.add("leave");
  		return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/home.html");	
  	} 
    
    @POST
  	@Path("/saveEmployeeCommence")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public InputStream saveEmployeeCommence(EmployeeCommence employeeCommence) {
    	
    	int x = employeeCommence.nid;
    	System.out.println(employeeCommence.nid);
    	System.out.println(x);
    	Gui.server.sendSignal(new IOps.CommenceEmployee(employeeCommence.nid));
        data.add(employeeCommence.nid+"");
    	type.add("employeeCommence");
  		return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/home.html");	
  	} 
   
    @POST
  	@Path("/saveLeaveRequest")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public InputStream saveLeaveRequest(LeaveRequest leaveRequest) {
    	
    	LocalDate base = LocalDate.parse("1970-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
    	LocalDate d1 = LocalDate.parse(leaveRequest.start, DateTimeFormatter.ISO_LOCAL_DATE);
    	LocalDate d2 = LocalDate.parse(leaveRequest.end, DateTimeFormatter.ISO_LOCAL_DATE);
    	Duration diff1 = Duration.between(base.atStartOfDay(), d1.atStartOfDay());
    	Duration diff2 = Duration.between(base.atStartOfDay(), d2.atStartOfDay());
    	int diffsecond1 = (int) diff1.getSeconds();
    	int diffsecond2 = (int) diff2.getSeconds();
    	System.out.println(diffsecond1);
    	System.out.println(diffsecond2);
    	Gui.server.sendSignal(new IOps.RequestEmployeeLeave(diffsecond1, diffsecond2, leaveRequest.nid, leaveRequest.leave));
        data.add(leaveRequest.start+"/"+leaveRequest.end+"/"+leaveRequest.nid+"/"+leaveRequest.leave);
    	type.add("leaveRequest");
  		return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/home.html");	
  	} 
    
    @POST
  	@Path("/saveEmployeeBonus")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public InputStream saveEmployeeBonus(BonusToEmployee bonus) {
    	
    	LocalDate base = LocalDate.parse("1970-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
    	LocalDate d1 = LocalDate.parse(bonus.start, DateTimeFormatter.ISO_LOCAL_DATE);
    	LocalDate d2 = LocalDate.parse(bonus.end, DateTimeFormatter.ISO_LOCAL_DATE);
    	Duration diff1 = Duration.between(base.atStartOfDay(), d1.atStartOfDay());
    	Duration diff2 = Duration.between(base.atStartOfDay(), d2.atStartOfDay());
    	int diffsecond1 = (int) diff1.getSeconds();
    	int diffsecond2 = (int) diff2.getSeconds();
    	System.out.println(diffsecond1);
    	System.out.println(diffsecond2);
        server.sendSignal(new IOps.AssignBonusToEmployee(bonus.nid, bonus.name, diffsecond1, diffsecond2, "NEW"));
        data.add(bonus.nid+"/"+bonus.name+"/"+bonus.start+"/"+bonus.end);
    	type.add("employeeBonus");
  		return servletContext.getResourceAsStream("/WEB-INF/home.html");	
  	} 
  	
  	@POST
  	@Path("/saveEmployeeJob")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public InputStream saveEmployeeJob(JobToEmployee job) {
        server.sendSignal(new IOps.AssignJobToEmployee(job.job, job.nid, "NEW", true) );
        data.add(job.job+"/"+job.nid);
    	type.add("employeeJob");
  		return servletContext.getResourceAsStream("/WEB-INF/home.html");	
  	}
  	
  	@GET
  	@Path("/listOfRequestedLeaves")
  	@Produces(MediaType.APPLICATION_JSON)
  	public Response allRequestedLeaves() {
  		return Response.ok(nidOfRequestedLeaves).build();
  	}
  	
  	@POST
  	@Path("/approvedLeave")
  	@Produces(MediaType.APPLICATION_JSON)
  	public InputStream approvedLeave(ApprovedLeave leave) {
  		int size = nidOfRequestedLeaves.size();
  		for(int j = 0; j < size; j++) {
  			if(nidOfRequestedLeaves.get(j).get(0).equals(leave.nid.get(0))) {
  				server.sendSignal(new IOps.ApproveEmployeeLeave(Integer.parseInt(leave.nid.get(0))));
  				nidOfRequestedLeaves.remove(j);
  				System.out.println(nidOfRequestedLeaves);
  			}
  		}
  		return servletContext.getResourceAsStream("/WEB-INF/home.html");	
  	}
  	
  	@POST
  	@Path("/rejectLeave")
  	@Produces(MediaType.APPLICATION_JSON)
  	public InputStream rejectLeave(ApprovedLeave leave) {
  		int size = nidOfRequestedLeaves.size();
  		for(int j = 0; j < size; j++) {
  			if(nidOfRequestedLeaves.get(j).get(0).equals(leave.nid.get(0))) {
  				server.sendSignal(new IOps.RejectEmployeeLeave(Integer.parseInt(leave.nid.get(0))));
  				nidOfRequestedLeaves.remove(j);
  				System.out.println(nidOfRequestedLeaves);
  			}
  		}
  		return servletContext.getResourceAsStream("/WEB-INF/home.html");	
  	}
    
    
    @POST
    @Path("/create_account")
    @Consumes(MediaType.APPLICATION_JSON)
    public InputStream Create_account(CreateAccount account) {
    	server.sendSignal(new IAuthentication.CreateNewAccount(account.fname, account.lname, account.employeeID));
    	return servletContext.getResourceAsStream("/WEB-INF/erp-theme/templates/erp/home.html");
    }
    
    
    
    
    
    
  	
  	
  	
  	
  	@POST
  	@Path("/check")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public InputStream use(Users user) {
  		
  		server.sendSignal(new IAuthentication.CheckUsernamePassword(user.user, user.password));
  		return servletContext.getResourceAsStream("/WEB-INF/erp-theme/templates/erp/home.html");
  	}
  	
  	
  	@GET
  	@Path("/checked")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public InputStream us() {
  		try {
  			Thread.sleep(1000);
  			if(reply.equals("0")) {
  				return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/login.html");
  			}
  		}catch(Exception e){
  			
  		}
  		return servletContext.getResourceAsStream("/WEB-INF/erp-theme/templates/erp_eng/home.html");
  	}
  	
  	@POST
  	@Path("/deleteLeave")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public InputStream delete_Leave(DeleteLeave leave) {
  		server.sendSignal(new IData.DeleteLeaveSpecification(leave.name));
  		return servletContext.getResourceAsStream("WEB-INF/templates/hrs_eng/home.html");
  	}
  	
  	
  	
  	
  	@POST
  	@Path("/getEmployeeID")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public void getEmployeeID(Employee employee) {
  		employeeID = employee.employeeID;
  		System.out.println(employeeID);
  	}
  	
  	@GET
  	@Path("/employeeInfo")
  	@Produces(MediaType.APPLICATION_JSON)
  	public Response employeeInfo() {
  		List<String> emp = new ArrayList<String>();
  		int size = employees.size();
  		for(int j = 0; j < size; j++) {
  			if(Integer.parseInt(employees.get(j).get(0)) == employeeID) {
  				int innerSize = employees.get(j).size();
  				for(int k = 0; k < innerSize; k++) {
  					emp.add(employees.get(j).get(k));
  				}
  				System.out.println(emp);
  				return Response.ok(emp).build();
  			}
  		}
  		return Response.ok().build();
  	}
  	
  	
  	
    
    
    
   
    
    @GET
  	@Path("/messages")
  	@Produces(MediaType.APPLICATION_JSON)
    public Response messages() {
    	List<List<String>> all = new ArrayList<List<String>>();
    	all.add(type);
    	all.add(data);
    	return Response.ok(all).build();
    	
    }
    
    
    
    
    
    @GET
    @Path("/do")
	@Produces(MediaType.TEXT_HTML)
    public InputStream d() {
    	try {
			 
			 
		
    	Gui.server.sendSignal(new IData.CreateEmployee(10, 1011, "Ahmed", "Saud", "Fahad", 1995, "Software", "Male"));
    	Thread.sleep(5000);
    	Gui.server.sendSignal(new IData.CreateEmployee(10, 1012, "Khalid", "Aziz", "Tariq", 1996, "Engineer", "Male"));
    	Thread.sleep(5000);
    	Gui.server.sendSignal(new IOps.CommenceEmployee(1011));
    	Thread.sleep(5000);
    	Gui.server.sendSignal(new IOps.CommenceEmployee(1012));
    	Thread.sleep(5000);
    	Gui.server.sendSignal(new IData.ReadEmployeeList());
    	Thread.sleep(5000);
    	Gui.all.clear();
    	System.out.println(Gui.all);
    	 }catch(Exception e) {
			 i = 0;
			 System.out.print(e.getMessage());
		 }
    	
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs_eng/home.html");
    }
    
    List<List<String>> employees = new ArrayList<>();

	@GET
	@Path("/listEmployees")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listEmployees() {
		employees.clear();
		try {
			Gui.server.sendSignal(new IData.ReadEmployeeList());
			Thread.sleep(100);
			int counter = Gui.all.size();
			for(int i = 0; i < counter; i++)
				employees.add(Gui.all.get(i));
			Gui.all.clear();
			System.out.println(employees);
			return Response.ok(employees).build();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return Response.ok().build();
	}
	
	
	@GET
	@Path("/listLeave")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listLeaves() {
		List<String> leav = new ArrayList<String>();
		try {
			server.sendSignal(new IData.ReadLeaveSpecification());
			Thread.sleep(100);
			int counter = leaves.size();
			for(int i = 0; i < counter; i++)
				leav.add(leaves.get(i).get(0));
			leaves.clear();
			System.out.println(leav);
			return Response.ok(leav).build();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return Response.ok().build();
		
	}
	
	
	@GET
	@Path("/listAllLeaves")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAllLeaves() {
		List<List<String>> leav = new ArrayList<>();
		try {
			server.sendSignal(new IData.ReadLeaveSpecification());
			Thread.sleep(100);
			int counter = leaves.size();
			for(int i = 0; i < counter; i++)
				leav.add(leaves.get(i));
			leaves.clear();
			System.out.println(leav);
			return Response.ok(leav).build();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return Response.ok().build();
		
	}
	
	
	

	
	private String reply;
	
	public void setReply(String m) {
		reply = m;
	}

	
	
	
	
	

}
