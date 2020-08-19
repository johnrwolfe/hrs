package hrsystem.hr.main;


import hrsystem.hr.main.EmployeeSet;

import io.ciera.runtime.summit.classes.IInstanceSet;
import io.ciera.runtime.summit.exceptions.XtumlException;


public interface PaymentSet extends IInstanceSet<PaymentSet,Payment> {

    // attributes
    public void setAmount( double m_Amount ) throws XtumlException;
    public void setDate( int m_Date ) throws XtumlException;
    public void setName( String m_Name ) throws XtumlException;


    // selections
    public EmployeeSet R3_Employee() throws XtumlException;


}
