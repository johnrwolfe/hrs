package hrsystem;


import hrsystem.ui.UIApp;
import hrsystem.ui.UIAuthenticate;
import hrsystem.ui.UIAppOps;

import io.ciera.runtime.summit.application.IApplication;
import io.ciera.runtime.summit.application.IRunContext;
import io.ciera.runtime.summit.classes.IModelInstance;
import io.ciera.runtime.summit.components.Component;
import io.ciera.runtime.summit.exceptions.BadArgumentException;
import io.ciera.runtime.summit.exceptions.EmptyInstanceException;
import io.ciera.runtime.summit.exceptions.XtumlException;

import io.ciera.runtime.summit.application.tasks.GenericExecutionTask;
import io.ciera.runtime.summit.application.tasks.HaltExecutionTask;
import io.ciera.runtime.summit.classes.IModelInstance;
import io.ciera.runtime.summit.interfaces.IMessage;
import io.ciera.runtime.summit.interfaces.Message;
import io.ciera.runtime.summit.util.LOG;
import io.ciera.runtime.summit.util.impl.LOGImpl;


import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import shared.IOps;
import interfaces.IData;
import interfaces.IAuthentication;


public class UI extends Component<UI> {

	private static GuiConnection requester = null;
    private static final int SOCKET_ERROR = -1;
    private UIApp UIApp;
    private UIAppOps UIAppOps;
    private UIAuthenticate UIAuthenticate;


    public UI(IApplication app, IRunContext runContext, int populationId) {
        super(app, runContext, populationId);


		LOG = null;
        UIApp = null;
        UIAppOps =null;
        UIAuthenticate =null;

    }

    // domain functions
    public void Reply( final String p_msg,  final boolean p_state ) throws XtumlException {
    
       if (requester != null) {
            try {
                requester.sendMessage(new IData.Reply(p_msg, p_state));
            } catch ( IOException e ) {
                LOG().LogInfo("Connection lost.");
                requester.tearDown();
                requester = null;
            }
        }
        
    }

    public void ReplyUsernamePassword( final String p_Username,  final String p_Password,  final boolean p_state ) throws XtumlException {
    
        if (requester != null) {
             try {
                 requester.sendMessage(new IAuthentication.Reply(p_Username, p_Password, p_state));
             } catch ( IOException e ) {
                 LOG().LogInfo("Connection lost.");
                 requester.tearDown();
                 requester = null;
             }
         }
         
     }

    public void SendLeaveSpecification( final String p_Name,  final int p_MaximumDays,  final int p_MinimumDays,  final int p_Size ) throws XtumlException {
    
        if (requester != null) {
             try {
                 requester.sendMessage(new IData.SendLeaveSpecification( p_Name, p_MaximumDays, p_MinimumDays, p_Size ));
             } catch ( IOException e ) {
                 LOG().LogInfo("Connection lost.");
                 requester.tearDown();
                 requester = null;
             }
         }
         
     }


     public void SendEmployee( final int p_EmployeeID,  final int p_NationalID,  final String p_FirstName,  final String p_MiddleName,  final String p_LastName,  final int p_DateOfBirth,  final String p_Degree,  final String p_Gender,  final int p_StartDate,  final int p_LeaveBalance,  final int p_SickLeaveBalance,  final int p_Size ) throws XtumlException {
    
        if (requester != null) {
             try {
                 requester.sendMessage(new IData.SendEmployee(p_EmployeeID, p_NationalID, p_FirstName, p_MiddleName, p_LastName, p_DateOfBirth, p_Degree, p_Gender, p_StartDate, p_LeaveBalance, p_SickLeaveBalance, p_Size ));
             } catch ( IOException e ) {
                 LOG().LogInfo("Connection lost.");
                 requester.tearDown();
                 requester = null;
             }
         }
         
     }

     


    // relates and unrelates


    // instance selections


    // relationship selections


    // ports
    public UIApp App() {
        if ( null == UIApp ) UIApp = new UIApp( this, null );
        return UIApp;
    }

    public UIAppOps AppOps() {
        if ( null == UIAppOps ) UIAppOps = new UIAppOps( this, null );
        return UIAppOps;
    }

    public UIAuthenticate Authenticate() {
        if ( null == UIAuthenticate ) UIAuthenticate = new UIAuthenticate( this, null );
        return UIAuthenticate;
    }


    // utilities
    private LOG LOG;
    public LOG LOG() {
    	if ( null == LOG ) LOG = new LOGImpl<>(this);
    	return LOG;
    }


    // component initialization function
    @Override
    public void initialize() throws XtumlException {
    if ( SOCKET_ERROR != connect() ) {
            getRunContext().execute(new GenericExecutionTask() {
                @Override
                public void run() throws XtumlException {
                    listen();
                }
            });
		}
		else {
			getRunContext().execute(new HaltExecutionTask());
		}

    }

    @Override
    public String getVersion() {
        Properties prop = new Properties();
        try {
            prop.load(getClass().getResourceAsStream("UIProperties.properties"));
        } catch (IOException e) { /* do nothing */ }
        return prop.getProperty("version", "Unknown");
    }
    @Override
    public String getVersionDate() {
        Properties prop = new Properties();
        try {
            prop.load(getClass().getResourceAsStream("UIProperties.properties"));
        } catch (IOException e) { /* do nothing */ }
        return prop.getProperty("version_date", "Unknown");
    }

    @Override
    public boolean addInstance( IModelInstance<?,?> instance ) throws XtumlException {
        if ( null == instance ) throw new BadArgumentException( "Null instance passed." );
        if ( instance.isEmpty() ) throw new EmptyInstanceException( "Cannot add empty instance to population." );

        return false;
    }

    @Override
    public boolean removeInstance( IModelInstance<?,?> instance ) throws XtumlException {
        if ( null == instance ) throw new BadArgumentException( "Null instance passed." );
        if ( instance.isEmpty() ) throw new EmptyInstanceException( "Cannot remove empty instance from population." );

        return false;
    }

    @Override
    public UI context() {
        return this;
    }

 

 public void listen() throws XtumlException {
        String signal_no = poll();
        switch (signal_no) {
        case "SendLeaveSpecification":
        	App().SendLeaveSpecification( (String) requester.message.get(0), Integer.parseInt((String) requester.message.get(1)),  Integer.parseInt((String) requester.message.get(2)), Integer.parseInt((String) requester.message.get(3)));
            break;
        case "Reply":
            try {
                Authenticate().Reply((String) requester.message.get(0), (String) requester.message.get(1),Boolean.parseBoolean((String) requester.message.get(2)) );
            } catch(Exception e) {
                App().Reply( (String) requester.message.get(0), Boolean.parseBoolean((String) requester.message.get(1)) );
            }
            break;
        case "ReplyNewEmployee":
        	App().ReplyNewEmployee( (String) requester.message.get(0), (String) requester.message.get(1) );
            break;
        case "SendEmployee":
        	App().SendEmployee( Integer.parseInt((String) requester.message.get(0)),  Integer.parseInt((String) requester.message.get(1)),  (String) requester.message.get(2),  (String) requester.message.get(3),  (String) requester.message.get(4),  Integer.parseInt((String) requester.message.get(5)),  (String) requester.message.get(6),  (String) requester.message.get(7),  Integer.parseInt((String) requester.message.get(8)),  Integer.parseInt((String) requester.message.get(9)),  Integer.parseInt((String) requester.message.get(10)),  Integer.parseInt((String) requester.message.get(11)) );
            break;
        case "ReadEmployeeList":
        	App().ReadEmployeeList();
            break;
        case "Initialize":
        	App().Initialize();
            break;
        case "ReadLeaveSpecification":
        	App().ReadLeaveSpecification();
            break;
        case "CreateLeaveSpecification":
        	App().CreateLeaveSpecification( (String) requester.message.get(0),   Integer.parseInt((String) requester.message.get(1)),   Integer.parseInt((String) requester.message.get(2)));
            break;
        case "DeleteLeaveSpecification":
        	App().DeleteLeaveSpecification( (String) requester.message.get(0) );
            break;
        case "CreateEmployee":
        	App().CreateEmployee( Integer.parseInt((String) requester.message.get(0)),  Integer.parseInt((String) requester.message.get(1)), (String) requester.message.get(2),  (String) requester.message.get(3),  (String) requester.message.get(4),  Integer.parseInt((String) requester.message.get(5)), (String) requester.message.get(6),  (String) requester.message.get(7)  );
            break;
        case "Return_Employee_Payment":
        	AppOps().Return_Employee_Payment( Double.parseDouble((String) requester.message.get(0)),  (String) requester.message.get(1),  Integer.parseInt((String) requester.message.get(2)) );
            break;
        case "RequestEmployeeLeave":
        	AppOps().RequestEmployeeLeave( Integer.parseInt((String) requester.message.get(0)),  Integer.parseInt((String) requester.message.get(1)),  Integer.parseInt((String) requester.message.get(2)),  (String) requester.message.get(3));
            break;
        case "CommenceEmployee":
        	AppOps().CommenceEmployee( Integer.parseInt((String) requester.message.get(0)) );
            break;
        case "ApproveEmployeeLeave":
        	AppOps().ApproveEmployeeLeave( Integer.parseInt((String) requester.message.get(0)) );
            break;
        case "GenerateEmployeePayslip":
        	AppOps().GenerateEmployeePayslip(  Integer.parseInt((String) requester.message.get(0))  );
            break;
        case "AssignJobToEmployee":
        	AppOps().AssignJobToEmployee( Integer.parseInt((String) requester.message.get(0)),  Integer.parseInt((String) requester.message.get(1)),  (String) requester.message.get(2),  Boolean.parseBoolean((String) requester.message.get(3)) );
            break;
        case "ReturnFromLeave":
        	AppOps().ReturnFromLeave( Integer.parseInt((String) requester.message.get(0)) );
            break;
        case "RejectEmployeeLeave":
        	AppOps().RejectEmployeeLeave( Integer.parseInt((String) requester.message.get(0)) );
            break;
        case "AssignBonusToEmployee":
        	AppOps().AssignBonusToEmployee( Integer.parseInt((String) requester.message.get(0)),  (String) requester.message.get(1),  Integer.parseInt((String) requester.message.get(2)),  Integer.parseInt((String) requester.message.get(3)), (String) requester.message.get(4));
            break;
        case "AddToGroup":
            Authenticate().AddToGroup( Integer.parseInt((String) requester.message.get(0)),  (String) requester.message.get(1));
            break;
        case "ChangePassword":
            Authenticate().ChangePassword( (String) requester.message.get(0),  (String) requester.message.get(1), (String) requester.message.get(2));
            break;
        case "CreateNewAccount":
            Authenticate().CreateNewAccount( (String) requester.message.get(0),  (String) requester.message.get(1), Integer.parseInt((String) requester.message.get(2)));
            break;
        case "GetUsernamePassword":
            Authenticate().GetUsernamePassword( Integer.parseInt((String) requester.message.get(0)));
            break;
        case "SOCKET_ERROR":
            LOG().LogFailure("Socket listener shuting down.");
            getRunContext().execute(new HaltExecutionTask());
            return;
        default:
            break;
        }
        getRunContext().execute(new GenericExecutionTask() {
            @Override
            public void run() throws XtumlException {
                listen();
            }
        });
    }

    private int connect() {
        if ( null == requester ) requester = new GuiConnection(LOG());
        try {
            requester.connect();
        } catch (UnknownHostException unknownHost) {
            LOG().LogInfo("You are trying to connect to an unknown host.");
            requester.tearDown();
            requester = null;
            return SOCKET_ERROR;
        } catch (IOException ioException) {
            LOG().LogInfo("Connection lost.");
            requester.tearDown();
            requester = null;
            return SOCKET_ERROR;
        }
        return 1;
    }
    
    private String poll() {
        if ( null != requester ) {
            try {
                return requester.poll();
            } catch ( IOException e ) {
                LOG().LogInfo("Connection lost.");
                requester.tearDown();
                requester = null;
                return "SOCKET_ERROR";
            } catch ( XtumlException e ) {
                LOG().LogInfo(e.getMessage());
                return "NULL_SIGNAL";
            }
        }
        else return "SOCKET_ERROR";
    }


    private static class GuiConnection {
    	
    	private LOG LOG;
        Socket requestSocket;
        DataOutputStream out;
        BufferedReader in;
        IMessage message;
        
        private GuiConnection(LOG log) {
        	LOG = log;
        }

        private void connect() throws IOException {
            requestSocket = new Socket("localhost", 2003);
            requestSocket.setSoTimeout( 10 ); // set read timeout for 10 milliseconds
            LOG().LogInfo("Connected to localhost on port 2003");
            out = new DataOutputStream(requestSocket.getOutputStream());
            out.flush();
            in = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()));
        }
        
        private String poll() throws IOException, XtumlException {
            try {
                String data = in.readLine();
                if (data == null) {
                    LOG().LogInfo("Connection closed by server");
                    return "SOCKET_ERROR";
                }
                //LOG().LogFailure(data);
                message = Message.deserialize(data);
                return message.getName();
            } catch ( IOException e ) {
                if ( e instanceof SocketTimeoutException ) { /* do nothing */ }
                else throw e;
            }
            return "NULL_SIGNAL";
        }
        
        private void sendMessage(IMessage msg) throws IOException, XtumlException {
            out.write(msg.serialize().getBytes());
            out.write('\n');
            out.flush();
        }
        
        private void tearDown() {
            try {
                if ( null != requestSocket ) requestSocket.close();
                if ( null != out ) out.close();
                if ( null != in ) in.close();
            } catch ( IOException e ) {}
        }
        
        private LOG LOG() {
        	return LOG;
        }
    }

}
