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

import shared.ICRUD;
import shared.IOps;
import io.ciera.runtime.summit.interfaces.IMessage;

@Path("/page")
public class Gui {
	int i = 0;
	
	
	List<String> data = new ArrayList<String>();
	List<String> type = new ArrayList<String>();
   
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
			 Runtime.getRuntime().exec(new String[]{"sh","/Users/feras/Desktop/untitled folder 5/hrm-master/run.sh"});
			
			 
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
    
    
    
    
    
    
    
    
    
    
    @POST
   	@Path("/saveBonus")
   	@Consumes(MediaType.APPLICATION_JSON)
    public InputStream saveBonus(Bonus bonus) {
    	//String name = Name;
    	//int amount = Integer.parseInt(Amount);
    	//String action =Action;
    	server.sendSignal(new ICRUD.Bonus( bonus.name, bonus.amount , "NEW"));
    	data.add(bonus.name+"/"+bonus.amount);
    	type.add("bonus");
    	return servletContext.getResourceAsStream("/WEB-INF/home.html");
    }
	
    @POST
	@Path("/saveEmployee")
	@Consumes(MediaType.APPLICATION_JSON)
	public InputStream saveEmploye(Employee employee) {
		//String FName = fName;
		//String LName = lName;
		//int nID = Integer.parseInt(NID);
		//String action = Action;
        server.sendSignal(new ICRUD.Employee( employee.fname, employee.lname , employee.nid, "NEW" ));
        data.add(employee.fname+"/"+employee.lname+"/"+employee.nid);
    	type.add("employee");
		return servletContext.getResourceAsStream("/WEB-INF/home.html");	
	} 
    
    @POST
  	@Path("/saveGrade")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public InputStream saveGrade(Grade grade) {
  		//int value = Integer.parseInt(Value);
  		//String action = Action;
        server.sendSignal(new ICRUD.Grade( grade.value, "NEW" ));
        data.add(grade.value + "");
    	type.add("grade");
  		return servletContext.getResourceAsStream("/WEB-INF/home.html");	
  	} 
      
    @POST
  	@Path("/saveJob")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public InputStream saveJob(Job job) {
  		//int step = Integer.parseInt(Step);
  		//int grade = Integer.parseInt(Grade);
    	//int amount = Integer.parseInt(Amount);
  		//String action = Action;
  		//String name = Name;
        server.sendSignal(new ICRUD.Job(job.step, job.grade, job.amount, job.name, "NEW"));
        data.add(job.step+"/"+job.grade+"/"+job.amount+"/"+job.name);
    	type.add("job");
  		return servletContext.getResourceAsStream("/WEB-INF/home.html");	
  	} 
      
    @POST
  	@Path("/saveLeave")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public InputStream saveLeave(Leave leave) {
  		//String name = Name;
    	//int maxDays = Integer.parseInt(MaxDays);
  		//String action = Action;
        server.sendSignal(new ICRUD.Leave(leave.name, leave.maximumDays, "NEW"));
        data.add(leave.name+"/"+leave.maximumDays);
    	type.add("leave");
  		return servletContext.getResourceAsStream("/WEB-INF/home.html");	
  	} 
   
   
    
    @POST
  	@Path("/saveStep")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public InputStream saveStep(Step step) {
    	//int value = Integer.parseInt(Value);
  		//String action = Action;
        server.sendSignal(new ICRUD.Step(step.value, "NEW"));
        data.add(step.value+"");
    	type.add("step");
  		return servletContext.getResourceAsStream("/WEB-INF/home.html");	
  	} 
    
    @POST
  	@Path("/saveEmployeeBonus")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public InputStream saveEmployeeBonus(EmployeeBonus employeeBonus) {
    	//int nid = Integer.parseInt(Nid);
  		//String bonusname = Bonusname;
    	LocalDate base = LocalDate.parse("1970-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
    	LocalDate d1 = LocalDate.parse(employeeBonus.start, DateTimeFormatter.ISO_LOCAL_DATE);
    	LocalDate d2 = LocalDate.parse(employeeBonus.end, DateTimeFormatter.ISO_LOCAL_DATE);
    	Duration diff1 = Duration.between(base.atStartOfDay(), d1.atStartOfDay());
    	Duration diff2 = Duration.between(base.atStartOfDay(), d2.atStartOfDay());
    	int diffsecond1 = (int) diff1.toSeconds();
    	int diffsecond2 = (int) diff2.toSeconds();
        server.sendSignal(new IOps.Employee_Bonus(employeeBonus.nid, employeeBonus.name, diffsecond1, diffsecond2, "NEW"));
        data.add(employeeBonus.nid+"/"+employeeBonus.name+"/"+employeeBonus.start+"/"+employeeBonus.end);
    	type.add("employeeBonus");
  		return servletContext.getResourceAsStream("/WEB-INF/home.html");	
  	} 
    
    @POST
  	@Path("/saveEmployeeCommence")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public InputStream saveEmployeeCommence(EmployeeCommence employeeCommence) {
    	//int nid = Integer.parseInt(Nid);
    	int x = employeeCommence.nid;
    	System.out.println(employeeCommence.nid);
    	System.out.println(x);
        server.sendSignal(new IOps.Employee_Commence(employeeCommence.nid));
        data.add(employeeCommence.nid+"");
    	type.add("employeeCommence");
  		return servletContext.getResourceAsStream("/WEB-INF/home.html");	
  	} 
    
    @POST
  	@Path("/saveEmployeeJob")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public InputStream saveEmployeeJob(EmployeeJob employeeJob) {
    	//int nid = Integer.parseInt(Nid);
        server.sendSignal(new IOps.Employee_Job(employeeJob.job, employeeJob.nid, "NEW", true));
        data.add(employeeJob.job+"/"+employeeJob.nid);
    	type.add("employeeJob");
  		return servletContext.getResourceAsStream("/WEB-INF/home.html");	
  	} 
    
    @POST
  	@Path("/saveLeaveRequest")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public InputStream saveLeaveRequest(LeaveRequest leaveRequest) {
    	//String start = Start;
    	//String end = End;
    	//int nid = Integer.parseInt(NID);
    	//String leave = Leave;
    	LocalDate base = LocalDate.parse("1970-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
    	LocalDate d1 = LocalDate.parse(leaveRequest.start, DateTimeFormatter.ISO_LOCAL_DATE);
    	LocalDate d2 = LocalDate.parse(leaveRequest.end, DateTimeFormatter.ISO_LOCAL_DATE);
    	Duration diff1 = Duration.between(base.atStartOfDay(), d1.atStartOfDay());
    	Duration diff2 = Duration.between(base.atStartOfDay(), d2.atStartOfDay());
    	int diffsecond1 = (int) diff1.toSeconds();
    	int diffsecond2 = (int) diff2.toSeconds();
    	System.out.println(diffsecond1);
    	System.out.println(diffsecond2);
        server.sendSignal(new IOps.Leave_Request(diffsecond1, diffsecond2, leaveRequest.nid, leaveRequest.leave));
        data.add(leaveRequest.start+"/"+leaveRequest.end+"/"+leaveRequest.nid+"/"+leaveRequest.leave);
    	type.add("leaveRequest");
  		return servletContext.getResourceAsStream("/WEB-INF/home.html");	
  	} 
    
    @POST
  	@Path("/saveLeaveReturn")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public InputStream saveLeaveReturn(EmployeeReturn employeeReturn) {
    	//int nid = Integer.parseInt(NID);
        server.sendSignal(new IOps.Leave_Return(employeeReturn.nid));
        data.add(employeeReturn.nid+"");
    	type.add("employeeReturn");
  		return servletContext.getResourceAsStream("/WEB-INF/home.html");	
  	} 
    
    @POST
  	@Path("/requestEmployeePayment")
  	@Consumes(MediaType.APPLICATION_JSON)
  	public InputStream saveLeaveReturn(EmployeePayment employeePayment) {
    	//int nid = Integer.parseInt(NID);
        server.sendSignal(new IOps.Request_Employee_Payment(employeePayment.nid));
        data.add(employeePayment.nid+"");
    	type.add("employeePayment");
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
			 
			 
		
    	server.sendSignal(new ICRUD.Employee("Ahmed", "Fahad", 1111, "NEW"));
    	Thread.sleep(5000);
    	server.sendSignal(new IOps.Employee_Commence(1111));
    	Thread.sleep(5000);
    	server.sendSignal(new ICRUD.Grade(1, "NEW"));
    	Thread.sleep(5000);
    	server.sendSignal(new ICRUD.Step(1000, "NEW"));
    	Thread.sleep(5000);
    	server.sendSignal(new ICRUD.Job(1000, 1, 500, "Developer", "NEW"));
    	Thread.sleep(5000);
    	server.sendSignal(new IOps.Employee_Job(1, 1111, "NEW", true));
    	Thread.sleep(5000);
    	server.sendSignal(new IOps.Request_Employee_Payment(1111));
    	 }catch(Exception e) {
			 i = 0;
			 System.out.print(e.getMessage());
		 }
    	
    	return servletContext.getResourceAsStream("/WEB-INF/allEmployees.html");
    }
    
	@GET
	@Path("/listLeave")
	@Produces(MediaType.APPLICATION_JSON)
	public Response allLeave() {
		ArrayList<String> name = new ArrayList<>();
		try {
			 Connection conn = null;
			 Statement stmt = null;
			 Class.forName("com.mysql.jdbc.Driver");
		     conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HR", "root", "Feras123h");
			 stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT * FROM LISTOFLEAVES ");
			 while(rs.next()) {
				 name.add(rs.getString("name"));
				 System.out.println(rs.getString("name"));
			 }
			 
			 //System.out.println(name);
			 //JSONObject list = new JSONObject();
			 //list.put("name", name);
			// System.out.println(list.toString());
			 return Response.ok(name).build();
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
		return Response.ok().build();
	}
	
	

	@GET
	@Path("/listEmployees")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listEmployees() {
		ArrayList<ArrayList> employees = new ArrayList<>();
		try {
			Connection conn = null;
			 Statement stmt = null;
			 Class.forName("com.mysql.jdbc.Driver");
		     conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HR", "root", "Feras123h");
			 stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEE ");
			 while(rs.next()) {
				 ArrayList<String> employee = new ArrayList<>();
				 employee.add(""+rs.getInt("id"));
				 employee.add(rs.getString("first"));
				 employee.add(rs.getString("last"));
				 employee.add(rs.getString("state"));
				 employees.add(employee);
				 //System.out.println(employee);
				 //System.out.println(employees);
			 }
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
	
	public String getReply() {
		return reply;
	}
	
	
	
	
	
	

}
