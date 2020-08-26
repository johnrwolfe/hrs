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

import interfaces.IData;
import shared.IOps;
import io.ciera.runtime.summit.interfaces.IMessage;

@Path("/page")
public class Gui {
	int i = 0;
	
	
	List<String> data = new ArrayList<String>();
	List<String> type = new ArrayList<String>();
	List<String> leaves = new ArrayList<String>();
   
    ApplicationConnection server = null;
    ConnectionHandler connHandler = null;
   
    public void setApplicationConnection(ApplicationConnection server) {
        this.server = server;
    }

    public void sendSignal(IMessage message) {
        server.sendSignal(message);
    }


    @Context ServletContext servletContext;
	
    
    @GET
    @Path("/")
    @Produces({MediaType.TEXT_HTML})
    public InputStream web() {
    	if(i == 0) {
    	 connHandler = new ConnectionHandler(this);
		 connHandler.start();
		 i++;
		 
		 try {
			 Runtime.getRuntime().exec(new String[]{"sh","/run.sh"});
			
			 
		 }catch(Exception e) {
			 i = 0;
			 System.out.print(e.getMessage());
		 }
    	}
    	return servletContext.getResourceAsStream("/WEB-INF/erp-theme/templates/erp/home.html");
    }
    
    
    
    @GET
    @Path("/base")
    @Produces({MediaType.TEXT_HTML})
    public InputStream base() {
    	return servletContext.getResourceAsStream("/WEB-INF/erp-theme/templates/erp/base.html");
    }
    
    @GET
    @Path("/basejs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream basejs() {
    	return servletContext.getResourceAsStream("/WEB-INF/base.js");
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
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/home.html");
    }
    
    @GET
    @Path("/homejs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream homejs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/home.js");
    }
    
    @GET
    @Path("/hr_form")
    @Produces({MediaType.TEXT_HTML})
    public InputStream hr_form() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/hr_form.html");
    }
    
    @GET
    @Path("/staff_home")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/staff_home.html");
    }
    
    @GET
    @Path("/staff_list")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_list() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/staff_list.html");
    }
    
    @GET
    @Path("/staff_detail")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_detail() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/staff_detail.html");
    }
    
    @GET
    @Path("/staff_delete")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_delete() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/staff_confirm_delete.html");
    }
    
    @GET
    @Path("/staff_form")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_form() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/staff_form.html");
    }
    
    @GET
    @Path("/staff_formjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_formjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/staff_form.js");
    }
    
    @GET
    @Path("/staff_pay")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_pay() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/staff_pay.html");
    }
    
    @GET
    @Path("/staff_leave")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_leave() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/staff_leave_form.html");
    }
    
    @GET
    @Path("/staff_leavejs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_leavejs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/staff_leave.js");
    }
    
    @GET
    @Path("/staff_commence")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_commence() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/staff_commence.html");
    }
    
    @GET
    @Path("/staff_commencejs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_commencejs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/staff_commence.js");
    }
   
    @GET
    @Path("/staff_bonus_list")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_bonus_list() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/staff_bonus_list.html");
    }
    
    @GET
    @Path("/staff_bonus_detail")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_bonus_detail() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/staff_bonus_detail.html");
    }
    
    @GET
    @Path("/staff_bonus_form")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_bonus_form() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/staff_bonus_form.html");
    }
    
    @GET
    @Path("/staff_bonus_formjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_bonus_formjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/staff_bonus_form.js");
    }
    
    @GET
    @Path("/staff_bonus_delete")
    @Produces({MediaType.TEXT_HTML})
    public InputStream staff_bonus_delete() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/staff_bonus_confirm_delete.html");
    }
   
    @GET
    @Path("/register")
    @Produces({MediaType.TEXT_HTML})
    public InputStream register() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/register.html");
    }
    
    @GET
    @Path("/register_done")
    @Produces({MediaType.TEXT_HTML})
    public InputStream register_done() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/register_done.html");
    }
    
    
    @GET
    @Path("/leave_home")
    @Produces({MediaType.TEXT_HTML})
    public InputStream leave_home() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/leave_home.html");
    }
    
    @GET
    @Path("/leave_list")
    @Produces({MediaType.TEXT_HTML})
    public InputStream leave_list() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/leave_list.html");
    }
    
    @GET
    @Path("/leave_detail")
    @Produces({MediaType.TEXT_HTML})
    public InputStream leave_detail() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/leave_detail.html");
    }
    
    @GET
    @Path("/leave_form")
    @Produces({MediaType.TEXT_HTML})
    public InputStream leave_form() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/leave_form.html");
    }
    
    @GET
    @Path("/leave_formjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream leave_formjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/leave_form.js");
    }
    
    @GET
    @Path("/leave_delete")
    @Produces({MediaType.TEXT_HTML})
    public InputStream leave_delete() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/leave_confirm_delete.html");
    }
    
    @GET
    @Path("/staff_return")
    @Produces({MediaType.TEXT_HTML})
    public InputStream leave_return() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/staff_return.html");
    }
    
    @GET
    @Path("/staff_returnjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream leave_returnjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/staff_return.js");
    }
    
    @GET
    @Path("/grade_home")
    @Produces({MediaType.TEXT_HTML})
    public InputStream grade_home() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/grade_home.html");
    }
    
    @GET
    @Path("/grade_list")
    @Produces({MediaType.TEXT_HTML})
    public InputStream grade_list() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/grade_list.html");
    }
    
    @GET
    @Path("/grade_detail")
    @Produces({MediaType.TEXT_HTML})
    public InputStream grade_detail() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/grade_detail.html");
    }
    
    @GET
    @Path("/grade_form")
    @Produces({MediaType.TEXT_HTML})
    public InputStream grade_form() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/grade_form.html");
    }
    
    @GET
    @Path("/grade_formjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream grade_formjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/grade_form.js");
    }
    
    @GET
    @Path("/grade_delete")
    @Produces({MediaType.TEXT_HTML})
    public InputStream grade_delete() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/grade_confirm_delete.html");
    }
    
    @GET
    @Path("/bonus_list")
    @Produces({MediaType.TEXT_HTML})
    public InputStream bonus_list() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/bonus_list.html");
    }
    
    @GET
    @Path("/bonus_detail")
    @Produces({MediaType.TEXT_HTML})
    public InputStream bonus_detail() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/bonus_detail.html");
    }
    
    @GET
    @Path("/bonus_form")
    @Produces({MediaType.TEXT_HTML})
    public InputStream bonus_form() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/bonus_form.html");
    }
    
    @GET
    @Path("/bonus_formjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream bonus_formjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/bonus_form.js");
    }
    
    @GET
    @Path("/bonus_delete")
    @Produces({MediaType.TEXT_HTML})
    public InputStream bonus_delete() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/bonus_confirm_delete.html");
    }
    
    @GET
    @Path("/step_form")
    @Produces({MediaType.TEXT_HTML})
    public InputStream step_form() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/step_form.html");
    }
    
    @GET
    @Path("/step_formjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream step_formjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/step_form.js");
    }
    
    @GET
    @Path("/job_home")
    @Produces({MediaType.TEXT_HTML})
    public InputStream job_home() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/job_home.html");
    }
    
    @GET
    @Path("/job_list")
    @Produces({MediaType.TEXT_HTML})
    public InputStream job_list() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/job_list.html");
    }
    
    @GET
    @Path("/job_detail")
    @Produces({MediaType.TEXT_HTML})
    public InputStream job_detail() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/job_detail.html");
    }
    
    @GET
    @Path("/job_form")
    @Produces({MediaType.TEXT_HTML})
    public InputStream job_form() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/job_form.html");
    }
    
    @GET
    @Path("/job_formjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream job_formjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/job_form.js");
    }
    
    @GET
    @Path("/job_delete")
    @Produces({MediaType.TEXT_HTML})
    public InputStream job_delete() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/job_confirm_delete.html");
    }
    
    @GET
    @Path("/job_to_staff_form")
    @Produces({MediaType.TEXT_HTML})
    public InputStream job_to_staff_form() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/job_to_staff_form.html");
    }
    
    @GET
    @Path("/job_to_staff_formjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream job_to_staff_formjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/job_to_staff_form.js");
    }
    
    @GET
    @Path("/pay_home")
    @Produces({MediaType.TEXT_HTML})
    public InputStream pay_home() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/pay_home.html");
    }
    
    @GET
    @Path("/pay_list")
    @Produces({MediaType.TEXT_HTML})
    public InputStream pay_list() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/pay_list.html");
    }
    
    @GET
    @Path("/payjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream payjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/pay.js");
    }
    
    @GET
    @Path("/login")
    @Produces({MediaType.TEXT_HTML})
    public InputStream login() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/login.html");
    }
    
    @GET
    @Path("/loginjs")
    @Produces({MediaType.TEXT_HTML})
    public InputStream loginjs() {
    	return servletContext.getResourceAsStream("/WEB-INF/templates/hrs/login.js");
    }
    
    
    @POST
	@Path("/saveEmployee")
	@Consumes(MediaType.APPLICATION_JSON)
	public InputStream saveEmploye(Employee employee) {
		
        server.sendSignal(new IData.CreateEmployee(employee.employeeID, employee.nationalID, employee.firstName, employee.middleName, employee.lastName, employee.dateOfBirth, employee.degree, employee.gender));
        data.add(employee.employeeID+"/"+employee.nationalID+"/"+employee.firstName+"/"+employee.middleName+"/"+employee.lastName+"/"+employee.dateOfBirth+"/"+employee.degree+"/"+employee.gender);
    	type.add("employee");
		return servletContext.getResourceAsStream("/WEB-INF/home.html");	
	} 
    
    
    @POST
  	@Path("/saveLeave")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public InputStream saveLeave(Leave leave) {
  		
        server.sendSignal(new IData.CreateLeaveSpecification(leave.name, leave.maximumDays, leave.minimumDays));
        data.add(leave.name+"/"+leave.maximumDays+"/"+leave.minimumDays);
    	type.add("leave");
    	leaves.add(leave.name);
  		return servletContext.getResourceAsStream("/WEB-INF/home.html");	
  	} 
    
    @POST
  	@Path("/saveEmployeeCommence")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public InputStream saveEmployeeCommence(EmployeeCommence employeeCommence) {
    	
    	int x = employeeCommence.nid;
    	System.out.println(employeeCommence.nid);
    	System.out.println(x);
        server.sendSignal(new IOps.CommenceEmployee(employeeCommence.nid));
        data.add(employeeCommence.nid+"");
    	type.add("employeeCommence");
  		return servletContext.getResourceAsStream("/WEB-INF/home.html");	
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
        server.sendSignal(new IOps.RequestEmployeeLeave(diffsecond1, diffsecond2, leaveRequest.nid, leaveRequest.leave));
        data.add(leaveRequest.start+"/"+leaveRequest.end+"/"+leaveRequest.nid+"/"+leaveRequest.leave);
    	type.add("leaveRequest");
  		return servletContext.getResourceAsStream("/WEB-INF/home.html");	
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
			 
			 
		
    	server.sendSignal(new IData.CreateEmployee(10, 1011, "Ahmed", "Saud", "Fahad", 1995, "Software", "Male"));
    	Thread.sleep(5000);
    	server.sendSignal(new IData.CreateEmployee(10, 1012, "Khalid", "Aziz", "Tariq", 1996, "Engineer", "Male"));
    	Thread.sleep(5000);
    	server.sendSignal(new IOps.CommenceEmployee(1011));
    	Thread.sleep(5000);
    	server.sendSignal(new IOps.CommenceEmployee(1012));
    	Thread.sleep(5000);
    	server.sendSignal(new IData.ReadEmployeeList());
    	Thread.sleep(5000);
    	all.clear();
    	System.out.println(all);
    	 }catch(Exception e) {
			 i = 0;
			 System.out.print(e.getMessage());
		 }
    	
    	return servletContext.getResourceAsStream("/WEB-INF/allEmployees.html");
    }
    
	

	@GET
	@Path("/listEmployees")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listEmployees() {
		List<List<String>> employees = new ArrayList<>();
		try {
			server.sendSignal(new IData.ReadEmployeeList());
			Thread.sleep(5000);
			int counter = all.size();
			for(int i = 0; i < counter; i++)
				employees.add(all.get(i));
			all.clear();
			System.out.println(employees);
			return Response.ok(employees).build();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return Response.ok().build();
	}
	
	private String reply;
	
	public void setReply(String m) {
		reply = m;
	}

	List<List<String>> all = new ArrayList<List<String>>();
	public void setReplyList(List<String> data) {
		all.add(data);
	}
	public String getReply() {
		return reply;
	}
	
	
	
	
	
	

}
